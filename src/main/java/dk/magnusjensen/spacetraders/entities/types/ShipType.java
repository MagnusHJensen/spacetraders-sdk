package dk.magnusjensen.spacetraders_sdk.entities.types;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.JsonNode;
import dk.magnusjensen.SpaceTraders;
import dk.magnusjensen.spacetraders_sdk.api.ApiCaller;
import dk.magnusjensen.spacetraders_sdk.entities.ShipEntity;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class ShipType {


	@JsonAlias(value = "class")
	private String className;
	private String type;
	private int maxCargo;
	private int loadingSpeed;
	private int speed;
	private String manufacturer;
	private int plating;
	private int weapons;

	@Nullable
	private ArrayList<String> restrictedGoods;

	@Nullable
	private ArrayList<PurchaseLocation> purchaseLocations;

	public ShipType() {
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getMaxCargo() {
		return maxCargo;
	}

	public void setMaxCargo(int maxCargo) {
		this.maxCargo = maxCargo;
	}

	public int getLoadingSpeed() {
		return loadingSpeed;
	}

	public void setLoadingSpeed(int loadingSpeed) {
		this.loadingSpeed = loadingSpeed;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public int getPlating() {
		return plating;
	}

	public void setPlating(int plating) {
		this.plating = plating;
	}

	public int getWeapons() {
		return weapons;
	}

	public void setWeapons(int weapons) {
		this.weapons = weapons;
	}

	@Nullable
	public ArrayList<String> getRestrictedGoods() {
		return restrictedGoods;
	}

	public void setRestrictedGoods(@Nullable ArrayList<String> restrictedGoods) {
		this.restrictedGoods = restrictedGoods;
	}

	@Nullable
	public ArrayList<PurchaseLocation> getPurchaseLocations() {
		return purchaseLocations;
	}

	public void setPurchaseLocations(@Nullable ArrayList<PurchaseLocation> purchaseLocations) {
		this.purchaseLocations = purchaseLocations;
	}

	public static ArrayList<ShipType> getShips(String token) throws Exception {
		ArrayList<ShipType> ships = new ArrayList<>();

		JsonNode shipsNode = ApiCaller.GET("types/ships", token, null).get("ships");

		if (shipsNode.isArray()) {
			for (JsonNode shipNode : shipsNode) {
				ships.add(SpaceTraders.getMapper().treeToValue(shipNode, ShipType.class));
			}
		}

		return ships;
	}

	public ShipEntity buyShip(String token, String location) throws Exception {
		JsonNode shipNode = ApiCaller.POST("my/ships", token, null, RequestBody.create("{\"type\": \"" + this.getType() + "\", \"location\": \"" + location + "\"}", MediaType.parse("application/json"))).get("ship");
		return SpaceTraders.getMapper().treeToValue(shipNode, ShipEntity.class);
	}

	@Override
	public String toString() {
		return this.getType();
	}

	public static class PurchaseLocation {
		private String system;
		private String location;
		private int price;

		public PurchaseLocation() {
		}

		public String getSystem() {
			return system;
		}

		public void setSystem(String system) {
			this.system = system;
		}

		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public int getPrice() {
			return price;
		}

		public void setPrice(int price) {
			this.price = price;
		}

		@Override
		public String toString() {
			return "[" + this.getSystem() + " - " + this.getLocation() + "] Price: " + this.getPrice();
		}
	}
}
