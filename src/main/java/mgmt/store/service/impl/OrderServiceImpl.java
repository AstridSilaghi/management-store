package mgmt.store.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mgmt.store.constants.Constants;
import mgmt.store.dto.OrderDto;
import mgmt.store.dto.converter.ConvertOrderDtoToEntity;
import mgmt.store.exceptions.OrderNotFoundException;
import mgmt.store.exceptions.ProductNotFoundException;
import mgmt.store.exceptions.ProductOutOfStock;
import mgmt.store.model.Order;
import mgmt.store.model.Product;
import mgmt.store.repository.OrderRepository;
import mgmt.store.repository.ProductRepository;
import mgmt.store.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

	private OrderRepository orderRepo;
	
	private ProductRepository productRepo;

	private ConvertOrderDtoToEntity converter;
	
	@Autowired
	public OrderServiceImpl(OrderRepository orderRepo, ProductRepository productRepo, ConvertOrderDtoToEntity converter) {
		this.orderRepo = orderRepo;
		this.productRepo = productRepo;
		this.converter = converter;
	}

	@Override
	public List<Order> getAllOrders() {
		return orderRepo.findAll();
	}

	@Override
	public Optional<Order> getOrderById(Long id) {
		return orderRepo.findById(id);
	}

	@Override
	public Order addOrder(OrderDto orderDto) {
		Order order = converter.convertOrderEntityToDto(orderDto);
		return orderRepo.save(order);
	}

	@Override
	public void deleteOrderById(Long id) {
		Optional<Order> order = orderRepo.findById(id);
		if (!order.isEmpty()) {
			orderRepo.deleteById(id);
		}
	}
	
	@Override
	public void deleteAllOrders() {
		orderRepo.deleteAll();
	}

	@Override
	public Order updateOrder(Long id, OrderDto newOrderDto) {
		Order newOrder = converter.convertOrderEntityToDto(newOrderDto);
		return orderRepo.findById(id).map(existingOrder -> {
			log.info("Order with id : {} exists and is updated", id);
			existingOrder.setOrderNumber(newOrderDto.getOrderNumber());
			return orderRepo.save(existingOrder);
		}).orElseGet(() -> {
			log.info("Order with id: %f does not exist and is created", id);
			newOrder.setId(id);
			return orderRepo.save(newOrder);
		});
	}

	@Override
	public Optional<Order> getOrderByNumber(String number) {
		return orderRepo.findByOrderNumber(number);
	}

	@Override
	public Order addProductToOrder(Long orderId, Long prodId) {
		Optional<Product> addedProduct = productRepo.findById(prodId);
		Optional<Order> existingOrder = orderRepo.findById(orderId);
		if(addedProduct.isEmpty()) {
			throw new ProductNotFoundException(prodId);
		}
		if(existingOrder.isEmpty()) {
			throw new OrderNotFoundException(orderId);
		}
		if(!productIsAvailable(addedProduct.get())) {
			throw new ProductOutOfStock(prodId);
		}
		Order order = existingOrder.get();
		order.getProducts().add(addedProduct.get());
		return orderRepo.save(order);
	}
	
	private boolean productIsAvailable(Product product) {
		return product.getIsAvailable().equals(Constants.YES);
	}
}
