package mgmt.store.service;

import java.util.List;
import java.util.Optional;

import mgmt.store.dto.ProductDto;
import mgmt.store.model.Product;

public interface ProductService {

	List<Product> getAllProducts();
	
	Optional<Product> getProductById(Long id);
	
	Product addProduct(ProductDto product);
	
	void deleteProductById(Long id);
	
	void deleteAllProducts();
	
	Product updateProduct(Long id, Product product);
	
	Optional<Product> updateProductPrice(Long id, Float price);
	
	List<Product> getProductsWithinPriceRange(Long minPrice, Long maxPrice);

	
	
}
