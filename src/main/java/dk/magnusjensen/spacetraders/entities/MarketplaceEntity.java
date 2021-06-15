package dk.magnusjensen.spacetraders.entities;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.JsonNode;
import dk.magnusjensen.spacetraders.SpaceTraders;
import dk.magnusjensen.spacetraders.api.ApiCaller;
import dk.magnusjensen.spacetraders.events.PurchaseOrderEvent;
import dk.magnusjensen.spacetraders.events.SellOrderEvent;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import java.util.ArrayList;

public class MarketplaceEntity {

	private ArrayList<MarketplaceGood> goods;

	public MarketplaceEntity() {
	}

	public ArrayList<MarketplaceGood> getGoods() {
		return goods;
	}

	public void setGoods(ArrayList<MarketplaceGood> goods) {
		this.goods = goods;
	}

	public void buy(String token, String shipid, String good, int quantity) throws Exception {
		JsonNode response = ApiCaller.POST("my/purchase-orders", token, null,
				RequestBody.create("{\"shipId\": \"" + shipid + "\", \"good\": \"" + good +"\", \"quantity\": " + quantity + "}", MediaType.parse("application/json")));

		int credits = response.get("credits").asInt();
		MarketplaceOrder order = SpaceTraders.getMapper().treeToValue(response.get("order"), MarketplaceOrder.class);
		ShipEntity ship = SpaceTraders.getMapper().treeToValue(response.get("ship"), ShipEntity.class);

		SpaceTraders.getEventHandler().purchaseEventFired(new PurchaseOrderEvent(credits, order, ship));

	}

	public void sell(String token, String shipid, String good, int quantity) throws Exception {
		JsonNode response = ApiCaller.POST("my/sell-orders", token, null,
				RequestBody.create("{\"shipId\": \"" + shipid + "\", \"good\": \"" + good +"\", \"quantity\": " + quantity + "}", MediaType.parse("application/json")));

		int credits = response.get("credits").asInt();
		MarketplaceOrder order = SpaceTraders.getMapper().treeToValue(response.get("order"), MarketplaceOrder.class);
		ShipEntity ship = SpaceTraders.getMapper().treeToValue(response.get("ship"), ShipEntity.class);

		SpaceTraders.getEventHandler().sellEventFired(new SellOrderEvent(credits, order, ship));
	}

	public static class MarketplaceGood {
		@JsonAlias(value = "symbol")
		private String good;
		private int volumePerUnit;
		private int quantityAvailable;
		private int pricePerUnit;
		private int purchasePricePerUnit;
		private int spread;
		private int sellPricePerUnit;

		public MarketplaceGood() {
		}

		public String getGood() {
			return good;
		}

		public void setGood(String good) {
			this.good = good;
		}

		public int getVolumePerUnit() {
			return volumePerUnit;
		}

		public void setVolumePerUnit(int volumePerUnit) {
			this.volumePerUnit = volumePerUnit;
		}

		public int getQuantityAvailable() {
			return quantityAvailable;
		}

		public void setQuantityAvailable(int quantityAvailable) {
			this.quantityAvailable = quantityAvailable;
		}

		public int getPricePerUnit() {
			return pricePerUnit;
		}

		public void setPricePerUnit(int pricePerUnit) {
			this.pricePerUnit = pricePerUnit;
		}

		public int getPurchasePricePerUnit() {
			return purchasePricePerUnit;
		}

		public void setPurchasePricePerUnit(int purchasePricePerUnit) {
			this.purchasePricePerUnit = purchasePricePerUnit;
		}

		public int getSpread() {
			return spread;
		}

		public void setSpread(int spread) {
			this.spread = spread;
		}

		public int getSellPricePerUnit() {
			return sellPricePerUnit;
		}

		public void setSellPricePerUnit(int sellPricePerUnit) {
			this.sellPricePerUnit = sellPricePerUnit;
		}
	}

	public static class MarketplaceOrder {
		private String good;
		private int quantity;
		private int pricePerUnit;
		private int total;

		public MarketplaceOrder() {
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

		public int getPricePerUnit() {
			return pricePerUnit;
		}

		public void setPricePerUnit(int pricePerUnit) {
			this.pricePerUnit = pricePerUnit;
		}

		public int getTotal() {
			return total;
		}

		public void setTotal(int total) {
			this.total = total;
		}
	}
}
