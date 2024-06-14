package mgmt.store.service;

import java.util.List;
import java.util.Optional;

import mgmt.store.model.Product;

public interface ProductService {

	List<Product> getAllProducts();
	
	Optional<Product> getProductById(Long id);
	
	Product addProduct(Product product);
	
	void deleteProductById(Long id);
	
	Product updateProduct(Long id, Product product);
	
}
