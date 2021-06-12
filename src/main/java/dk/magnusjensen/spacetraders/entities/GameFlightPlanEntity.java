package dk.magnusjensen.spacetraders.entities;

public class GameFlightPlanEntity {
	private String arrivesAt;
	private String createdAt;
	private String departure;
	private String destination;
	private String id;
	private String shipId;
	private String username;

	public GameFlightPlanEntity() {
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
