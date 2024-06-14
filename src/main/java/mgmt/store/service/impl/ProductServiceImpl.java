package mgmt.store.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mgmt.store.controller.ProductController;
import mgmt.store.exceptions.ProductNotFoundException;
import mgmt.store.model.Product;
import mgmt.store.repository.ProductRepository;
import mgmt.store.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

	private ProductRepository productRepo;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepo) {
		this.productRepo = productRepo;
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

	@Override
	public Optional<Product> getProductById(Long id) {
		return productRepo.findById(id);// .orElseThrow(() -> new ProductNotFoundException(id));
	}

	@Override
	public Product addProduct(Product product) {
		return productRepo.save(product);
	}

	@Override
	public void deleteProductById(Long id) {
		Optional<Product> product = productRepo.findById(id);
		if (!product.isEmpty()) {
			productRepo.deleteById(id);
		}
	}

	@Override
	public Product updateProduct(Long id, Product newProduct) {
		return productRepo.findById(id).map(existingProduct -> {
			log.info("Product with id : %f exists and is updated", id);
			existingProduct.setName(newProduct.getName());
			existingProduct.setDescription(newProduct.getDescription());
			existingProduct.setPrice(newProduct.getPrice());
			existingProduct.setAvailable(newProduct.isAvailable());
			return productRepo.save(existingProduct);
		}).orElseGet(() -> {
			log.info("Product with id: %f does not exist and is created", id);
			newProduct.setId(id);
			return productRepo.save(newProduct);
		});
	}
}
