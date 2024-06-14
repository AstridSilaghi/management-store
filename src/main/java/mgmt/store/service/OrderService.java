package mgmt.store.service;

import java.util.List;
import java.util.Optional;

import mgmt.store.dto.OrderDto;
import mgmt.store.model.Order;

public interface OrderService {

	List<Order> getAllOrders();
	
	Optional<Order> getOrderById(Long id);
	
	Optional<Order> getOrderByNumber(String number);
	
	Order addOrder(OrderDto product);
	
	void deleteOrderById(Long id);
	
	void deleteAllOrders();
	
	Order updateOrder(Long id, OrderDto productDto);
	
	Order addProductToOrder(Long orderId, Long prodId);
}
