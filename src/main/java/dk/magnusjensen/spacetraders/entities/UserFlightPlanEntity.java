package dk.magnusjensen.spacetraders.entities;

import com.fasterxml.jackson.databind.JsonNode;
import dk.magnusjensen.spacetraders.SpaceTraders;
import dk.magnusjensen.spacetraders.api.ApiCaller;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class UserFlightPlanEntity {
	private String arrivesAt;
	private String createdAt;
	private String departure;
	private String destination;
	private int distance;
	private int fuelConsumed;
	@Nullable
	private Integer fuelRemaining;
	private String id;
	private String shipId;
	@Nullable
	private String terminatedAt;
	private int timeRemainingInSeconds;

	public UserFlightPlanEntity() {
	}

	public String getArrivesAt() {
		return arrivesAt;
	}

	public void setArrivesAt(String arrivesAt) {
		this.arrivesAt = arrivesAt;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}

	public int getFuelConsumed() {
		return fuelConsumed;
	}

	public void setFuelConsumed(int fuelConsumed) {
		this.fuelConsumed = fuelConsumed;
	}

	@Nullable
	public Integer getFuelRemaining() {
		return fuelRemaining;
	}

	public void setFuelRemaining(@Nullable Integer fuelRemaining) {
		this.fuelRemaining = fuelRemaining;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getShipId() {
		return shipId;
	}

	public void setShipId(String shipId) {
		this.shipId = shipId;
	}

	@Nullable
	public String getTerminatedAt() {
		return terminatedAt;
	}

	public void setTerminatedAt(@Nullable String terminatedAt) {
		this.terminatedAt = terminatedAt;
	}

	public int getTimeRemainingInSeconds() {
		return timeRemainingInSeconds;
	}

	public void setTimeRemainingInSeconds(int timeRemainingInSeconds) {
		this.timeRemainingInSeconds = timeRemainingInSeconds;
	}

	public static UserFlightPlanEntity getFlightPlan(String token, String flightPlanId) throws Exception {
		JsonNode flightPlanNode = ApiCaller.GET("my/flight-plans/:flightPlanId/", token, new ArrayList<>(List.of(flightPlanId))).get("flightPlan");
		return SpaceTraders.getMapper().treeToValue(flightPlanNode, UserFlightPlanEntity.class);
	}


}
