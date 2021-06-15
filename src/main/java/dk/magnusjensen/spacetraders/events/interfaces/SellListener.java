package dk.magnusjensen.spacetraders.events.interfaces;

import dk.magnusjensen.spacetraders.events.PurchaseOrderEvent;
import dk.magnusjensen.spacetraders.events.SellOrderEvent;

public interface SellListener {
	void sellOrder(SellOrderEvent event);
}
