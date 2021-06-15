package dk.magnusjensen.spacetraders.events;

import dk.magnusjensen.spacetraders.events.interfaces.PurchaseListener;
import dk.magnusjensen.spacetraders.events.interfaces.SellListener;

import java.util.ArrayList;

public class EventHandler {
	private ArrayList<PurchaseListener> purchaseListeners = new ArrayList<>();
	private ArrayList<SellListener> sellListeners = new ArrayList<>();

	public void addPurchaseListener(PurchaseListener listener) {
		purchaseListeners.add(listener);
	}

	public void purchaseEventFired(PurchaseOrderEvent event) {
		for (PurchaseListener listener : purchaseListeners) {
			listener.purchaseOrder(event);
		}
	}

	public void addSellListener(SellListener listener) {
		sellListeners.add(listener);
	}

	public void sellEventFired(SellOrderEvent event) {
		for (SellListener listener : sellListeners) {
			listener.sellOrder(event);
		}
	}
}
