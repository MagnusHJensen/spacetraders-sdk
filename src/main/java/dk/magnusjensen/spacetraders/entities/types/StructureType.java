package dk.magnusjensen.spacetraders_sdk.entities.types;

import com.fasterxml.jackson.databind.JsonNode;
import dk.magnusjensen.SpaceTraders;
import dk.magnusjensen.spacetraders_sdk.api.ApiCaller;
import dk.magnusjensen.spacetraders_sdk.entities.StructureEntity;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.util.ArrayList;

public class StructureType {
	private ArrayList<String> allowedLocationTypes;
	private ArrayList<String> allowedPlanetTraits;
	private ArrayList<String> consumes;
	private String name;
	private int price;
	private ArrayList<String> produces;
	private String type;

	public StructureType() {
	}

	public ArrayList<String> getAllowedLocationTypes() {
		return allowedLocationTypes;
	}

	public void setAllowedLocationTypes(ArrayList<String> allowedLocationTypes) {
		this.allowedLocationTypes = allowedLocationTypes;
	}

	public ArrayList<String> getAllowedPlanetTraits() {
		return allowedPlanetTraits;
	}

	public void setAllowedPlanetTraits(ArrayList<String> allowedPlanetTraits) {
		this.allowedPlanetTraits = allowedPlanetTraits;
	}

	public ArrayList<String> getConsumes() {
		return consumes;
	}

	public void setConsumes(ArrayList<String> consumes) {
		this.consumes = consumes;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public ArrayList<String> getProduces() {
		return produces;
	}

	public void setProduces(ArrayList<String> produces) {
		this.produces = produces;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public static ArrayList<StructureType> getStructures(String token) throws Exception {
		ArrayList<StructureType> structures = new ArrayList<>();

		JsonNode structuresNode = ApiCaller.GET("types/structures", token, null).get("structures");

		if (structuresNode.isArray()) {
			for (JsonNode structureNode : structuresNode) {
				structures.add(SpaceTraders.getMapper().treeToValue(structureNode, StructureType.class));
			}
		}

		return structures;
	}

	public StructureEntity buildStructure(String token, String location) throws Exception {
		JsonNode structureNode = ApiCaller.POST("my/structures", token, null, RequestBody.create("{\"location\": \"" + location + "\", \"type\": \"" + this.getType() + "\"}", MediaType.parse("application/json"))).get("structure");
		return SpaceTraders.getMapper().treeToValue(structureNode, StructureEntity.class);
	}

	@Override
	public String toString() {
		return this.getName();
	}
}
