package mgmt.store.dto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;


@Component
public class OrderDto {

	@JsonProperty("number")
	private String orderNumber;
	
	
	public OrderDto() {
		super();
		this.orderNumber = "";
	}

	public OrderDto(String orderNumber) {
		super();
		this.orderNumber = orderNumber;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
}
