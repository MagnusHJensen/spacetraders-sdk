package dk.magnusjensen;

import com.fasterxml.jackson.databind.ObjectMapper;
import dk.magnusjensen.api.ApiCaller;
import okhttp3.OkHttpClient;

public class SpaceTraders {

	private ApiCaller API = new ApiCaller();


	private String username;
	private String token;

	public SpaceTraders(String username, String token) {
		this.username = username;
		this.token = token;
	}

	public String getUsername() {
		return username;
	}

	public String getToken() {
		return token;
	}




	public class Builder {
		private String username;
		private String token;

		public Builder setUsername(String username) {
			this.username = username;
			return this;
		}

		public Builder setToken(String token) {
			this.token = token;
			return this;
		}

		public SpaceTraders build() {
			return new SpaceTraders(username, token);
		}
	}
}
