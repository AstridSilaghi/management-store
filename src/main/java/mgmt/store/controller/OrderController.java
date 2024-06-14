package mgmt.store.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import mgmt.store.dto.OrderDto;
import mgmt.store.exceptions.OrderNotFoundException;
import mgmt.store.model.Order;
import mgmt.store.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	private static final Logger log = LoggerFactory.getLogger(OrderController.class);

	private OrderService orderService;

	@Autowired
	public OrderController(OrderService orderService) {
		this.orderService = orderService;
	}

	@GetMapping()
	public List<Order> list() {
		log.info("Listing orders...");
		List<Order> orders = orderService.getAllOrders();
		log.info(orders.toString());
		return orders;
	}

	@GetMapping("/find-order{id}")
	public Order list(@PathVariable("id") Long id) {
		log.info("Get order with id : {}", id);
		return orderService.getOrderById(id).orElseThrow(() -> new OrderNotFoundException(id));
	}

	@GetMapping("/find-order{number}")
	public Order list(@PathVariable("number") String number) {
		log.info("Get order with number {} ", number);
		return orderService.getOrderByNumber(number).orElseThrow(() -> new OrderNotFoundException(number));
	}

	@PostMapping("/add-order")
	public Order create(@RequestBody OrderDto orderDto) {
		log.info("Create a new order: {}", orderDto);
		return orderService.addOrder(orderDto);
	}

	@PutMapping("/update-order{id}")
	public Order update(@PathVariable("id") Long id, @RequestBody OrderDto orderDto) {
		log.info("Update the order: {}", orderDto);
		return orderService.updateOrder(id, orderDto);
	}

	@DeleteMapping("/remove-order{id}")
	public void delete(@PathVariable Long id) {
		log.info("Delete order with id : {}", id);
		orderService.deleteOrderById(id);
	}

	@DeleteMapping("/remove-all")
	public void delete() {
		log.info("Delete all orders");
		orderService.deleteAllOrders();
	}

	@PutMapping("/update-order{order_id}/product{prod_id}")
	public Order update(@PathVariable("order_id") Long orderId, @PathVariable("prod_id") Long prodId) {
		log.info("Add product with id {} to order {} ", prodId, orderId);
		return orderService.addProductToOrder(orderId, prodId);
	}
}
