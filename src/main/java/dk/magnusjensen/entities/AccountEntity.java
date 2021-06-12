package dk.magnusjensen.entities;

import com.fasterxml.jackson.databind.JsonNode;
import dk.magnusjensen.SpaceTraders;
import dk.magnusjensen.api.ApiCaller;
import dk.magnusjensen.util.TimeUtil;

import java.time.ZonedDateTime;

public class AccountEntity {
	private int credits;
	private String joinedAt;
	private int shipCount;
	private int structureCount;
	private String username;

	public AccountEntity() {
	}

	public int getCredits() {
		return credits;
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	public ZonedDateTime getJoinedAt() {
		return TimeUtil.parseDateTimeString(this.joinedAt);
	}

	public void setJoinedAt(String joinedAt) {
		this.joinedAt = joinedAt;
	}

	public int getShipCount() {
		return shipCount;
	}

	public void setShipCount(int shipCount) {
		this.shipCount = shipCount;
	}

	public int getStructureCount() {
		return structureCount;
	}

	public void setStructureCount(int structureCount) {
		this.structureCount = structureCount;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public static AccountEntity getInfo(String token) throws Exception {
		JsonNode accountNode = ApiCaller.GET("my/account", token, null);
		return SpaceTraders.getMapper().treeToValue(accountNode.get("user"), AccountEntity.class);
	}
}
