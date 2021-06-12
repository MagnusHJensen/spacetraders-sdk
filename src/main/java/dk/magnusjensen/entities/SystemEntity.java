package dk.magnusjensen.entities;

import com.fasterxml.jackson.databind.JsonNode;
import dk.magnusjensen.SpaceTraders;
import dk.magnusjensen.api.ApiCaller;

import java.util.ArrayList;

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
}
