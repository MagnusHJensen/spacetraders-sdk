package dk.magnusjensen.spacetraders.entities;

import com.fasterxml.jackson.databind.JsonNode;
import dk.magnusjensen.SpaceTraders;
import dk.magnusjensen.spacetraders.api.ApiCaller;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class StructureEntity {
	private String id;
	private String type;
	private String location;
	@Nullable
	private String status;
	@Nullable
	private Boolean active;
	private StructureOwner ownedBy;
	@Nullable
	private ArrayList<CargoEntity> inventory;
	@Nullable
	private ArrayList<String> consumes;
	@Nullable
	private ArrayList<String> produces;

	public StructureEntity() {
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public StructureOwner getOwnedBy() {
		return ownedBy;
	}

	public void setOwnedBy(StructureOwner ownedBy) {
		this.ownedBy = ownedBy;
	}

	public ArrayList<CargoEntity> getInventory() {
		return inventory;
	}

	public void setInventory(ArrayList<CargoEntity> inventory) {
		this.inventory = inventory;
	}

	public ArrayList<String> getConsumes() {
		return consumes;
	}

	public void setConsumes(ArrayList<String> consumes) {
		this.consumes = consumes;
	}

	public ArrayList<String> getProduces() {
		return produces;
	}

	public void setProduces(ArrayList<String> produces) {
		this.produces = produces;
	}

	public static ArrayList<StructureEntity> getMyStructures(String token) throws Exception {
		JsonNode structuresNode = ApiCaller.GET("my/structures", token, null).get("structures");

		ArrayList<StructureEntity> structures = new ArrayList<>();

		if (structuresNode.isArray()) {
			for (JsonNode structureNode : structuresNode) {
				structures.add(SpaceTraders.getMapper().treeToValue(structureNode, StructureEntity.class));
			}
		}
		return structures;
	}

	public static StructureEntity getStructureInfo(String token, String structureId) throws Exception {
		JsonNode structureNode = ApiCaller.GET("my/structures/:structureId/", token, new ArrayList<>(List.of(structureId))).get("structure");

		return SpaceTraders.getMapper().treeToValue(structureNode, StructureEntity.class);
	}

	/**
	 * @param token Game token.
	 * @param shipid The ship id of the ship you transfer cargo from.
	 * @param good The name of the good, you want to deposit.
	 * @param quantity The amount of good to deposit.
	 * @return Arraylist containg 3 arraylists. The first holding 1 entry being the cargoEntity, representing the transfer. The second one being the ship's cargo, after the transfer, and the third being the Structures inventory after the transfer.
	 * @throws Exception If the API call fails.
	 */
	public ArrayList<ArrayList<CargoEntity>> depositCargo(String token, String shipid, String good, int quantity) throws Exception {
		ArrayList<CargoEntity> transfer = new ArrayList<>();
		ArrayList<CargoEntity> shipCargo = new ArrayList<>();
		ArrayList<CargoEntity> structureInventory = new ArrayList<>();

		JsonNode response = ApiCaller.POST("my/structures/:structureId/deposit", token,
				new ArrayList<>(List.of(this.getId())),
				RequestBody.create("{\"shipId\": \"" + shipid + "\", \"good\": \"" + good + "\", \"quantity\": " + quantity + "}", MediaType.parse("application/json")));

		transfer.add(SpaceTraders.getMapper().treeToValue(response.get("transfer"), CargoEntity.class));

		if (response.get("ship").get("cargo").isArray()) {
			for (JsonNode cargo : response.get("ship").get("cargo")) {
				shipCargo.add(SpaceTraders.getMapper().treeToValue(cargo, CargoEntity.class));
			}
		}

		if (response.get("structure").get("inventory").isArray()) {
			for (JsonNode cargo : response.get("structure").get("inventory")) {
				structureInventory.add(SpaceTraders.getMapper().treeToValue(cargo, CargoEntity.class));
			}
		}

		return new ArrayList<>(List.of(transfer, shipCargo, structureInventory));

	}

	public class StructureOwner {
		private String username;

		public StructureOwner() {
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}
	}
}
