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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import mgmt.store.dto.ProductDto;
import mgmt.store.exceptions.ProductNotFoundException;
import mgmt.store.model.Product;
import mgmt.store.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	private static final Logger log = LoggerFactory.getLogger(ProductController.class);

	private ProductService productService;

	@Autowired
	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping()
	public List<Product> list() {
		log.info("Listing products...");
		List<Product> products = productService.getAllProducts();
		log.info(products.toString());
		return products;
	}

	@GetMapping("/find-product{id}")
	public Product list(@PathVariable("id") Long id) {
		log.info("Get product with id : " + id);
		return productService.getProductById(id).orElseThrow(() -> new ProductNotFoundException(id));
	}
	
	@GetMapping("/product-price-min")
	public List<Product> listProductsPriceGreaterThan(@RequestParam ("min") Long minPrice) {
		log.info("Get product with price greater than {}", minPrice);
		return productService.getProductsPriceGreaterThan(minPrice);
	}
	
	@GetMapping("/product-price-max")
	public List<Product> listProductsPriceLessThan(@RequestParam ("max") Long maxPrice) {
		log.info("Get product with price less than  {} ", maxPrice);
		return productService.getProductsPriceLessThan(maxPrice);
	}
	
	@GetMapping("/product-price-range")
	public List<Product> listProductsWithPriceRange(@RequestParam ("min") Long minPrice, @RequestParam ("max") Long maxPrice) {
		log.info("Get product with price between : {} and {} ", minPrice, maxPrice);
		return productService.getProductsWithinPriceRange(minPrice, maxPrice);
	}

	@GetMapping("/on-stock")
	public List<Product> listProductsOnStock() {
		log.info("Get products on stock");
		return productService.getProductsAvailable();
	}
	
	@PostMapping("/add-product")
	public Product create(@RequestBody ProductDto productDto) {
		log.info("Create a new product: " + productDto);
		return productService.addProduct(productDto);
	}

	@PutMapping("/update-product{id}")
	public Product update(@PathVariable("id") Long id, @RequestBody ProductDto productDto) {
		log.info("Update the product: " + productDto);
		return productService.updateProduct(id, productDto);
	}

	@PutMapping("/change-price{id}")
	public Product update(@PathVariable("id") Long id, @RequestBody JsonNode jsonNode) {
		log.info("Update the price for product with id: " + id);
		float price = 0.0f;
		try {
			price  = Float.valueOf(jsonNode.get("price").asText());
		} catch (NumberFormatException e) {
			throw new NumberFormatException("Wrong value for price: \n" + jsonNode.toPrettyString() + ".\nA number is expected.");
		}
		return productService.updateProductPrice(id, price).orElseThrow(() -> new ProductNotFoundException(id));
	}

	@DeleteMapping("/remove-product{id}")
	public void delete(@PathVariable Long id) {
		log.info("Delete product with id : " + id);
		productService.deleteProductById(id);
	}
	
	@DeleteMapping("/remove-all")
	public void delete() {
		log.info("Delete all products");
		productService.deleteAllProducts();
	}
}
