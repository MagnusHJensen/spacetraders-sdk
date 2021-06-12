package dk.magnusjensen.spacetraders.entities;

import com.fasterxml.jackson.databind.JsonNode;
import dk.magnusjensen.spacetraders.SpaceTraders;
import dk.magnusjensen.spacetraders.api.ApiCaller;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.util.ArrayList;
import java.util.List;

public class CargoEntity {
	private String good;
	private int quantity;

	public CargoEntity() {
	}

	public String getGood() {
		return good;
	}

	public void setGood(String good) {
		this.good = good;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public CargoEntity jettison(String token, int quantity, String shipid) throws Exception {
		JsonNode response = ApiCaller.POST("my/ships/:shipId/jettison", token,
				new ArrayList<>(List.of(shipid)),
				RequestBody.create("{\"good\":\"" + this.getGood() + "\", \"quantity\": " + quantity + "}", MediaType.parse("application/json")));

		this.setQuantity(response.get("quantityRemaining").asInt());
		return this;
	}

	public CargoEntity jettison(String token, int quantity, ShipEntity ship) throws Exception {
		JsonNode response = ApiCaller.POST("my/ships/:shipId/jettison", token,
				new ArrayList<>(List.of(ship.getId())),
				RequestBody.create("{\"good\":\"" + this.getGood() + "\", \"quantity\": " + quantity + "}", MediaType.parse("application/json")));

		this.setQuantity(response.get("quantityRemaining").asInt());
		return this;
	}

	/**
	 * @param token String containing your game token.
	 * @param quantity The quantity to transfer.
	 * @param fromShipId The ship id of the ship where you transfer cargo from.
	 * @param toShipId The ship id of the ship where you transfer cargo to.
	 * @return Returns an arraylist consisting of the fromShip (index 0) and the toShip (index 1)
	 * @throws Exception If the API call fails.
	 */
	public ArrayList<ShipEntity> transfer(String token, int quantity, String fromShipId, String toShipId) throws Exception {
		JsonNode response = ApiCaller.POST("my/ships/:fromShipId/transfer", token,
				new ArrayList<>(List.of(fromShipId)),
				RequestBody.create("{\"toShipId\": \"" + toShipId + "\",\"good\":\"" + this.getGood() + "\", \"quantity\": " + quantity + "}", MediaType.parse("application/json")));

		ArrayList<ShipEntity> ships = new ArrayList<>();

		ships.add(SpaceTraders.getMapper().treeToValue(response.get("fromShip"), ShipEntity.class));
		ships.add(SpaceTraders.getMapper().treeToValue(response.get("toShip"), ShipEntity.class));

		return ships;
	}

	public ArrayList<ShipEntity> transfer(String token, int quantity, ShipEntity fromShip, ShipEntity toShip) throws Exception {
		JsonNode response = ApiCaller.POST("my/ships/:fromShipId/transfer", token,
				new ArrayList<>(List.of(fromShip.getId())),
				RequestBody.create("{\"toShipId\": \"" + toShip.getId() + "\",\"good\":\"" + this.getGood() + "\", \"quantity\": " + quantity + "}", MediaType.parse("application/json")));

		ArrayList<ShipEntity> ships = new ArrayList<>();

		ships.add(SpaceTraders.getMapper().treeToValue(response.get("fromShip"), ShipEntity.class));
		ships.add(SpaceTraders.getMapper().treeToValue(response.get("toShip"), ShipEntity.class));

		return ships;
	}
}
