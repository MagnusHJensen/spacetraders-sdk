package dk.magnusjensen.spacetraders.api;

import com.fasterxml.jackson.databind.JsonNode;
import dk.magnusjensen.spacetraders.SpaceTraders;
import dk.magnusjensen.spacetraders.errors.ErrorHandler;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class ApiCaller {

	private static OkHttpClient CLIENT = new OkHttpClient();
	private static String BASE_URL = "https://api.spacetraders.io/";
	private static Pattern URL_PATTERN = Pattern.compile(":.+?(?=/)");


	public static JsonNode GET(String endpoint, String token, ArrayList<String> params) throws Exception {
		endpoint = replaceEndpoint(endpoint, params);
		Request request = new Request.Builder()
				.url(BASE_URL + endpoint)
				.get()
				.build();

		request = setTokenHeader(request, token);

		return makeCall(request);
	}

	public static JsonNode POST(String endpoint, String token, ArrayList<String> params, RequestBody body) throws Exception {
		endpoint = replaceEndpoint(endpoint, params);
		Request request = new Request.Builder()
				.url(BASE_URL + endpoint)
				.post(body)
				.build();

		request = setTokenHeader(request, token);

		return makeCall(request);
	}

	public static JsonNode PUT(String endpoint, String token, ArrayList<String> params, RequestBody body) throws Exception {
		endpoint = replaceEndpoint(endpoint, params);
		Request request = new Request.Builder()
				.url(BASE_URL + endpoint)
				.put(body)
				.build();

		request = setTokenHeader(request, token);

		return makeCall(request);
	}

	public static JsonNode DELETE(String endpoint, String token, ArrayList<String> params, RequestBody body) throws Exception {
		endpoint = replaceEndpoint(endpoint, params);
		Request request = new Request.Builder()
				.url(BASE_URL + endpoint)
				.delete(body)
				.build();

		request = setTokenHeader(request, token);

		return makeCall(request);
	}

	private static JsonNode makeCall(Request request) throws Exception {
		Call call = CLIENT.newCall(request);

		try {
			Response response = call.execute();
			JsonNode node =  SpaceTraders.getMapper().readValue(response.body().string(), JsonNode.class);

			if (node.has("error")) {
				JsonNode errorNode = node.get("error");
				System.out.println(errorNode.get("message").asText());
				ErrorHandler.handleError(errorNode);
			}

			return node;
		} catch (IOException exception) {
			exception.printStackTrace();
		}

		throw new Exception("API " + request.method() + " request has failed!");
	}

	private static String replaceEndpoint(String endpoint, ArrayList<String> params) {
		int i = 0;

		Matcher matcher = URL_PATTERN.matcher(endpoint);
		StringBuffer sb = new StringBuffer();
		while(matcher.find()) {
			matcher.appendReplacement(sb, params.get(i));
			i++;
		}
		matcher.appendTail(sb);

		return sb.toString();

	}

	private static Request setTokenHeader(Request request, String token) {
		if (token != null) {
			if (!token.equals("")) {
				request = request.newBuilder()
						.addHeader("Authorization", "Bearer " + token)
						.build();
			}
		}

		return request;
	}
}
