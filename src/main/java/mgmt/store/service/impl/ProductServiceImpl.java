package mgmt.store.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mgmt.store.dto.ProductDto;
import mgmt.store.dto.converter.ConvertProductDtoToEntity;
import mgmt.store.model.Product;
import mgmt.store.repository.ProductRepository;
import mgmt.store.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	private static final Logger log = LoggerFactory.getLogger(ProductServiceImpl.class);

	private ProductRepository productRepo;

	private ConvertProductDtoToEntity converter;
	
	@Autowired
	public ProductServiceImpl(ProductRepository productRepo, ConvertProductDtoToEntity converter) {
		this.productRepo = productRepo;
		this.converter = converter;
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

	@Override
	public Optional<Product> getProductById(Long id) {
		return productRepo.findById(id);
	}

	@Override
	public Product addProduct(ProductDto productDto) {
		Product product = converter.convertProductEntityToDto(productDto);
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
	public void deleteAllProducts() {
		productRepo.deleteAll();
	}

	@Override
	public Product updateProduct(Long id, Product newProduct) {
		return productRepo.findById(id).map(existingProduct -> {
			log.info("Product with id : %f exists and is updated", id);
			existingProduct.setName(newProduct.getName());
			existingProduct.setDescription(newProduct.getDescription());
			existingProduct.setPrice(newProduct.getPrice());
			existingProduct.setIsAvailable(newProduct.getIsAvailable());
			return productRepo.save(existingProduct);
		}).orElseGet(() -> {
			log.info("Product with id: %f does not exist and is created", id);
			newProduct.setId(id);
			return productRepo.save(newProduct);
		});
	}

	@Override
	public Optional<Product> updateProductPrice(Long id, Float price) {
		return productRepo.findById(id);
	}

	@Override
	public List<Product> getProductsWithinPriceRange(Long minPrice, Long maxPrice) {
		log.info("Get the products with price in this range {} - {}", minPrice, maxPrice);
		return productRepo.findProductWithinPriceRange(minPrice, maxPrice);
	}
}
