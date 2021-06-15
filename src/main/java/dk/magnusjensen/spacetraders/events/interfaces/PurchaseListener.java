package dk.magnusjensen.spacetraders.events.interfaces;

import dk.magnusjensen.spacetraders.events.PurchaseOrderEvent;

public interface PurchaseListener {
	void purchaseOrder(PurchaseOrderEvent event);
}
