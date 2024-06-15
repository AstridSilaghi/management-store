package mgmt.store.dto;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

import mgmt.store.model.AVAILABILITY;

@Component
public class ProductDto {

	@JsonProperty("name")
	private String name;
	
	@JsonProperty("price")
	private Float price;
	
	@JsonProperty("description")
	private String description;
	
	@JsonProperty("onStock")
	private AVAILABILITY isAvailable;
	
	public ProductDto() {
		super();
		this.name = "";
		this.price = 0.0f;
		this.description = "";
		this.isAvailable = AVAILABILITY.NO;
	}

	public ProductDto(String name, Float price, String description, AVAILABILITY isAvailable) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		this.isAvailable = isAvailable;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public AVAILABILITY getIsAvailable() {
		return isAvailable;
	}

	public void setIsAvailable(AVAILABILITY isAvailable) {
		this.isAvailable = isAvailable;
	}

	@Override
	public String toString() {
		return "ProductDto [name=" + name + ", price=" + price + ", description=" + description + ", isAvailable="
				+ isAvailable + "]";
	}
}
