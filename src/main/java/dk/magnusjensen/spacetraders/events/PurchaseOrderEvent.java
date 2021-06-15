package dk.magnusjensen.spacetraders.events;

import dk.magnusjensen.spacetraders.entities.MarketplaceEntity;
import dk.magnusjensen.spacetraders.entities.ShipEntity;

public class PurchaseOrderEvent {
	private int credits;
	private MarketplaceEntity.MarketplaceOrder order;
	private ShipEntity ship;

	public PurchaseOrderEvent(int credits, MarketplaceEntity.MarketplaceOrder order, ShipEntity ship) {
		this.credits = credits;
		this.order = order;
		this.ship = ship;
	}

	public int getCredits() {
		return credits;
	}

	public MarketplaceEntity.MarketplaceOrder getOrder() {
		return order;
	}

	public ShipEntity getShip() {
		return ship;
	}
}
