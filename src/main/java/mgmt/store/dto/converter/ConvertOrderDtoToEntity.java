package mgmt.store.dto.converter;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import mgmt.store.dto.OrderDto;
import mgmt.store.model.Order;

@Component
public class ConvertOrderDtoToEntity {

	public Order convertOrderEntityToDto(OrderDto orderDto) {
		Order order = new Order();
		order.setOrderNumber(orderDto.getOrderNumber());
		order.setOrderDate(LocalDate.now());
		return order;
	}
}
