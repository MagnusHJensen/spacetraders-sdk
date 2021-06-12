package dk.magnusjensen.entities;

import com.fasterxml.jackson.databind.JsonNode;
import dk.magnusjensen.SpaceTraders;
import dk.magnusjensen.api.ApiCaller;
import dk.magnusjensen.entities.types.ShipType;

import java.util.ArrayList;
import java.util.List;

public class SystemEntity {
	private String symbol;
	private String name;
	private ArrayList<LocationEntity> locations;

	public SystemEntity() {
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<LocationEntity> getLocations() {
		return locations;
	}

	public void setLocations(ArrayList<LocationEntity> locations) {
		this.locations = locations;
	}

	public static ArrayList<SystemEntity> getSystems(String token) throws Exception {
		JsonNode systemsNode = ApiCaller.GET("game/systems", token, null).get("systems");

		ArrayList<SystemEntity> systems = new ArrayList<>();

		if (systemsNode.isArray()) {
			for (JsonNode systemNode : systemsNode) {
				systems.add(SpaceTraders.getMapper().treeToValue(systemNode, SystemEntity.class));
			}
		}

		return systems;
	}

	public ArrayList<ShipType> getShipListings(String token) throws Exception {
		ArrayList<ShipType> ships = new ArrayList<>();

		JsonNode shipsNode = ApiCaller.GET("systems/:symbol/ship-listings", token, new ArrayList<>(List.of(this.getSymbol()))).get("shipListings");

		if (shipsNode.isArray()) {
			for (JsonNode shipNode : shipsNode) {
				ships.add(SpaceTraders.getMapper().treeToValue(shipNode, ShipType.class));
			}
		}

		return ships;
	}

	public ArrayList<LocationShip> getShipsInSystem(String token) throws Exception {
		ArrayList<LocationShip> ships = new ArrayList<>();

		JsonNode shipsNode = ApiCaller.GET("systems/:symbol/ships", token, new ArrayList<>(List.of(this.getSymbol()))).get("ships");

		if (shipsNode.isArray()) {
			for (JsonNode shipNode : shipsNode) {
				ships.add(SpaceTraders.getMapper().treeToValue(shipNode, LocationShip.class));
			}
		}

		return ships;
	}

	public ArrayList<LocationEntity> getSystemLocations(String token) throws Exception {
		ArrayList<LocationEntity> locations = new ArrayList<>();

		JsonNode locationsNode = ApiCaller.GET("systems/:symbol/locations", token, new ArrayList<>(List.of(this.getSymbol()))).get("locations");

		if (locationsNode.isArray()) {
			for (JsonNode locationNode : locationsNode) {
				locations.add(SpaceTraders.getMapper().treeToValue(locationNode, LocationEntity.class));
			}
		}

		return locations;
	}

	public static class LocationShip {
		private String shipId;
		private String username;
		private String shipType;

		public LocationShip() {
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

		public String getShipType() {
			return shipType;
		}

		public void setShipType(String shipType) {
			this.shipType = shipType;
		}
	}
}
