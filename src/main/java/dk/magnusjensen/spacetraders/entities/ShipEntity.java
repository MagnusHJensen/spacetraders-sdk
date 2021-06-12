package dk.magnusjensen.spacetraders.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.JsonNode;
import dk.magnusjensen.SpaceTraders;
import dk.magnusjensen.spacetraders.api.ApiCaller;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.util.ArrayList;
import java.util.List;

public class ShipEntity {
	private String id;
	private ArrayList<CargoEntity> cargo;

	@JsonAlias(value = "class")
	private String className;
	private String location;
	private String manufacturer;
	private int maxCargo;
	private int platign;
	private int spaceAvailable;
	private String type;
	private int weapons;
	private int x;
	private int y;

	public ShipEntity() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ArrayList<CargoEntity> getCargo() {
		return cargo;
	}

	public void setCargo(ArrayList<CargoEntity> cargo) {
		this.cargo = cargo;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getMaxCargo() {
		return maxCargo;
	}

	public void setMaxCargo(int maxCargo) {
		this.maxCargo = maxCargo;
	}

	public int getPlatign() {
		return platign;
	}

	public void setPlatign(int platign) {
		this.platign = platign;
	}

	public int getSpaceAvailable() {
		return spaceAvailable;
	}

	public void setSpaceAvailable(int spaceAvailable) {
		this.spaceAvailable = spaceAvailable;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getWeapons() {
		return weapons;
	}

	public void setWeapons(int weapons) {
		this.weapons = weapons;
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

	public static ArrayList<ShipEntity> getMyShips(String token) throws Exception {
		JsonNode shipsNode = ApiCaller.GET("my/ships", token, null).get("ships");

		ArrayList<ShipEntity> ships = new ArrayList<>();

		if (shipsNode.isArray()) {
			for (JsonNode shipNode : shipsNode) {
				ships.add(SpaceTraders.getMapper().treeToValue(shipNode, ShipEntity.class));
			}
		}

		return ships;
	}

	public static ShipEntity getShip(String token, String shipId) throws Exception {
		JsonNode shipNode = ApiCaller.GET("my/ships/:shipid/", token, new ArrayList<>(List.of(shipId))).get("ship");
		return SpaceTraders.getMapper().treeToValue(shipNode, ShipEntity.class);
	}

	public String scrap(String token) throws Exception {
		JsonNode messageNode = ApiCaller.DELETE("my/ships/:shipId/", token, new ArrayList<>(List.of(this.getId())), RequestBody.create("", MediaType.parse("application/json"))).get("success");
		return messageNode.asText();
	}


}
