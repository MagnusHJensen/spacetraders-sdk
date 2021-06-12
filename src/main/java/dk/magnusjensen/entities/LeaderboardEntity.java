package dk.magnusjensen.entities;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import dk.magnusjensen.SpaceTraders;
import dk.magnusjensen.api.ApiCaller;

import java.util.ArrayList;

public class LeaderboardEntity {
	private LeaderboardUser self;
	private ArrayList<LeaderboardUser> topUsers;

	public LeaderboardEntity() {
	}

	public LeaderboardUser getSelf() {
		return self;
	}

	public void setSelf(LeaderboardUser self) {
		this.self = self;
	}

	public ArrayList<LeaderboardUser> getTopUsers() {
		return topUsers;
	}

	public void setTopUsers(ArrayList<LeaderboardUser> topUsers) {
		this.topUsers = topUsers;
	}

	public static LeaderboardEntity getNetWorth(String token) throws Exception {
		JsonNode response = ApiCaller.GET("game/leaderboard/net-worth", token, null);

		LeaderboardEntity leaderboard = new LeaderboardEntity();
		leaderboard.setSelf(SpaceTraders.getMapper().treeToValue(response.get("userNetWorth"), LeaderboardUser.class));

		ArrayList<LeaderboardUser> topUsers = new ArrayList<>();

		if (response.get("netWorth").isArray()) {
			for (JsonNode topUser : response.get("netWorth")) {
				topUsers.add(SpaceTraders.getMapper().treeToValue(topUser, LeaderboardUser.class));
			}
		}

		leaderboard.setTopUsers(topUsers);

		return leaderboard;
	}

	public static class LeaderboardUser {
		private int netWorth;
		private int rank;
		private String username;

		public LeaderboardUser() {
		}

		public int getNetWorth() {
			return netWorth;
		}

		public void setNetWorth(int netWorth) {
			this.netWorth = netWorth;
		}

		public int getRank() {
			return rank;
		}

		public void setRank(int rank) {
			this.rank = rank;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		@Override
		public String toString() {
			return "Username: " + this.getUsername() + " - Rank: " + this.getRank() + " - Net worth: " + this.getNetWorth();
		}
	}
}
