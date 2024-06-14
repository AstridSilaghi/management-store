package mgmt.store.dto.converter;

import org.springframework.stereotype.Component;

import mgmt.store.dto.ProductDto;import mgmt.store.model.AVAILABILITY;
import mgmt.store.model.Product;

@Component
public class ConvertProductDtoToEntity {

	
	public Product convertProductEntityToDto(ProductDto productDto) {
		Product product = new Product();
		product.setName(productDto.getName());
		product.setDescription(productDto.getDescription());
		product.setPrice(productDto.getPrice());
		product.setIsAvailable(convertAvailability(productDto.getIsAvailable()));
		return product;
	}

	private String convertAvailability(AVAILABILITY availabilityDto) {
		return availabilityDto.equals(AVAILABILITY.YES) ? "Y" : "N";
	}
	 
}
