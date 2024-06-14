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
import org.springframework.web.bind.annotation.RestController;

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
	
	@PostMapping("/add-product")
	public Product create(@RequestBody Product product) {
		log.info("Create a new product: " + product);
		return productService.addProduct(product);
	}
	
	@PutMapping("/update-product{id}")
	public Product update(@PathVariable("id") Long id, @RequestBody Product product) {
		log.info("Update the product: " + product);
		return productService.updateProduct(id, product);
	}
	
	
	@DeleteMapping("/remove-product{id}")
	public void delete(@PathVariable Long id) {
		log.info("Delete product with id : " + id);
		productService.deleteProductById(id);
	}
}