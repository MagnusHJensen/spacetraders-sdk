package dk.magnusjensen.entities;

import com.fasterxml.jackson.databind.JsonNode;
import dk.magnusjensen.SpaceTraders;
import dk.magnusjensen.api.ApiCaller;
import dk.magnusjensen.entities.SystemEntity.LocationShip;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class LocationEntity {
	private boolean allowsConstruction;
	private String symbol;
	private String type;
	private String name;
	private int x;
	private int y;
	private ArrayList<String> traits;
	private int dockedShips;
	private ArrayList<StructureEntity> structures;
	@Nullable
	private ArrayList<String> messages;

	public LocationEntity() {
	}

	public boolean isAllowsConstruction() {
		return allowsConstruction;
	}

	public void setAllowsConstruction(boolean allowsConstruction) {
		this.allowsConstruction = allowsConstruction;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public ArrayList<String> getTraits() {
		return traits;
	}

	public void setTraits(ArrayList<String> traits) {
		this.traits = traits;
	}

	public int getDockedShips() {
		return dockedShips;
	}

	public void setDockedShips(int dockedShips) {
		this.dockedShips = dockedShips;
	}

	public ArrayList<StructureEntity> getStructures() {
		return structures;
	}

	public void setStructures(ArrayList<StructureEntity> structures) {
		this.structures = structures;
	}

	@Nullable
	public ArrayList<String> getMessages() {
		return messages;
	}

	public void setMessages(@Nullable ArrayList<String> messages) {
		this.messages = messages;
	}

	public static LocationEntity getLocationInfo(String token, String symbol) throws Exception {
		JsonNode locationNode = ApiCaller.GET("locations/:locationSymbol/", token, new ArrayList<>(List.of(symbol))).get("location");
		return SpaceTraders.getMapper().treeToValue(locationNode, LocationEntity.class);
	}

	public ArrayList<LocationShip> getShipsAtLocation(String token) throws Exception {
		ArrayList<LocationShip> ships = new ArrayList<>();

		JsonNode shipsNode = ApiCaller.GET("locations/:symbol/ships", token, new ArrayList<>(List.of(this.getSymbol()))).get("ships");

		if (shipsNode.isArray()) {
			for (JsonNode shipNode : shipsNode) {
				ships.add(SpaceTraders.getMapper().treeToValue(shipNode, LocationShip.class));
			}
		}

		return ships;
	}
}
