package dk.magnusjensen.spacetraders_sdk;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import dk.magnusjensen.spacetraders_sdk.api.ApiCaller;
import dk.magnusjensen.spacetraders_sdk.entities.AccountEntity;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SpaceTraders {

	private static ObjectMapper MAPPER = new ObjectMapper();
	private String username;
	private String token;
	private AccountEntity account;

	protected SpaceTraders(String username, String token, boolean claimUsername) throws Exception {
		if (claimUsername) {
			this.claimUsername(username);
		} else {
			this.init(token);
		}
	}

	private void claimUsername(String username) throws Exception {
		JsonNode response = ApiCaller.GET("users/:username/claim", null, new ArrayList<>(List.of(username)));
		this.username = username;
		this.token = response.get("token").asText();
		this.account = AccountEntity.getInfo(this.token);
	}

	private void init(String token) throws Exception {
		this.token = token;
		this.account = AccountEntity.getInfo(this.token);
		this.username = this.account.getUsername();
	}

	public static String getGameStatus() throws Exception {
		JsonNode response = ApiCaller.GET("game/status", null, null);
		return response.get("status").asText();
	}

	public String getUsername() {
		return username;
	}

	public String getToken() {
		return token;
	}

	@Nullable
	public AccountEntity getAccount() {
		return account;
	}

	public static ObjectMapper getMapper() {
		return MAPPER;
	}




	public static class Builder {
		private String username;
		private String token;
		private boolean claim;

		public Builder setUsername(String username) {
			this.username = username;
			this.claim = true;
			return this;
		}

		public Builder setToken(String token) {
			this.token = token;
			this.claim = false;
			return this;
		}

		public SpaceTraders build() throws Exception {
			return new SpaceTraders(username, token, claim);
		}
	}
}
