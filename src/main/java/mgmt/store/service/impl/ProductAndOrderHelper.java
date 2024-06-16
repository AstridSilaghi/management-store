package mgmt.store.service.impl;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;

import mgmt.store.model.Order;
import mgmt.store.model.Product;

@Component
public class ProductAndOrderHelper {
	public static void removeProductFromOrder(Product productToRemove, List<Order> relatedOrders) {
		for (Order order : relatedOrders) {
			removeElements(productToRemove, order.getProducts());
		}
	}

	public static void removeOrderFromProduct(Order orderToRemove, List<Product> relatedProducts) {
		for (Product product : relatedProducts) {
			removeElements(orderToRemove, product.getOrders());
		}
	}

	public static <E, F> void removeElements(E elementToRemove, Collection<E> removeFrom) {
		for (Iterator<E> it = removeFrom.iterator(); it.hasNext();) {
			E next = it.next();
			if (next.equals(elementToRemove)) {
				it.remove();
			}
		}
	}
}
