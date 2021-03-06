package dk.magnusjensen.spacetraders.entities.types;

import com.fasterxml.jackson.databind.JsonNode;
import dk.magnusjensen.spacetraders.SpaceTraders;
import dk.magnusjensen.spacetraders.api.ApiCaller;

import java.util.ArrayList;

public class GoodType {
	private String name;
	private String symbol;
	private int volumePerUnit;

	public GoodType() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public int getVolumePerUnit() {
		return volumePerUnit;
	}

	public void setVolumePerUnit(int volumePerUnit) {
		this.volumePerUnit = volumePerUnit;
	}

	public static ArrayList<GoodType> getGoods(String token) throws Exception {
		ArrayList<GoodType> goods = new ArrayList<>();

		JsonNode goodsNode = ApiCaller.GET("types/goods", token, null).get("goods");

		if (goodsNode.isArray()) {
			for (JsonNode goodNode : goodsNode) {
				goods.add(SpaceTraders.getMapper().treeToValue(goodNode, GoodType.class));
			}
		}

		return goods;
	}
}
