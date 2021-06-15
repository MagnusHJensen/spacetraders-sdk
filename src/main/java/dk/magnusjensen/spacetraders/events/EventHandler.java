package dk.magnusjensen.spacetraders.events;

import dk.magnusjensen.spacetraders.events.interfaces.PurchaseListener;

import java.util.ArrayList;

public class EventHandler {
	private ArrayList<PurchaseListener> purchaseListeners = new ArrayList<>();

	public void addPurchaseListener(PurchaseListener listener) {
		purchaseListeners.add(listener);
	}

	public void purchaseEventFired(PurchaseOrderEvent event) {
		for (PurchaseListener listener : purchaseListeners) {
			listener.purchaseOrder(event);
		}
	}
}
