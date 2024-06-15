package mgmt.store.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import mgmt.store.constants.Constants;
import mgmt.store.dto.ProductDto;
import mgmt.store.model.AVAILABILITY;
import mgmt.store.model.Product;
import mgmt.store.service.ProductService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProductService service;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	void getProductsShouldListProducts() throws Exception {
		List<Product> expectedProducts = new ArrayList<>(Arrays.asList(
				new Product(Long.valueOf(1), "Products1", 100.0f, "Description for Product1", Constants.NO),
				new Product(Long.valueOf(2), "Products2", 430.0f, "Description for Product2", Constants.YES)));
		when(service.getAllProducts()).thenReturn(expectedProducts);
		MvcResult result = this.mockMvc.perform(get("/product")).andExpect(status().isOk()).andReturn();

		String json = result.getResponse().getContentAsString();
		List<Product> products = objectMapper.readValue(json, new TypeReference<>() {
		});
		assertNotNull(products);
		assertEquals(2, products.size());
	}

	@Test
	void getFindProductIdShouldReturnProduct() throws Exception {
		Long id = Long.valueOf(2);
		Optional<Product> expectedProduct = Optional
				.of(new Product(id, "Products2", 430.0f, "Description for Product2", Constants.YES));

		when(service.getProductById(id)).thenReturn(expectedProduct);
		MvcResult result = this.mockMvc.perform(get("/product/find-product" + id)).andExpect(status().isOk())
				.andReturn();

		String json = result.getResponse().getContentAsString();
		Product product = objectMapper.readValue(json, new TypeReference<>() {
		});
		assertNotNull(product);
		assertEquals(id, product.getId());
	}

	@Test
	void getProductPriceMinShouldReturnProduct() throws Exception {
		Long min = Long.valueOf(100);
		List<Product> expectedProducts = new ArrayList<>(Arrays.asList(
				new Product(Long.valueOf(1), "Products1", 101.0f, "Description for Product1", Constants.NO),
				new Product(Long.valueOf(2), "Products2", 430.0f, "Description for Product2", Constants.YES)));

		when(service.getProductsPriceGreaterThan(min)).thenReturn(expectedProducts);
		MvcResult result = this.mockMvc
				.perform(get("/product/product-price-min").queryParam("min", String.valueOf(min)))
				.andExpect(status().isOk()).andReturn();

		String json = result.getResponse().getContentAsString();
		List<Product> products = objectMapper.readValue(json, new TypeReference<>() {
		});
		assertNotNull(products);
		assertEquals(2, products.size());
	}

	@Test
	void postAddProductShouldReturnProduct() throws Exception {
		ProductDto productDto = new ProductDto("Products1", 101.0f, "Description for Product1", AVAILABILITY.NO);
		Product newProduct = new Product(Long.valueOf(1), "Products1", 101.0f, "Description for Product1",
				Constants.NO);
		ObjectMapper mapper = new ObjectMapper();
		String json = mapper.writeValueAsString(productDto);
		when(service.addProduct(productDto)).thenReturn(newProduct);
		MvcResult result = this.mockMvc
				.perform(post("/product/add-product").contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk()).andReturn();

		String responseJson = result.getResponse().getContentAsString();
		Product product = objectMapper.readValue(responseJson, new TypeReference<>() {
		});
		assertNotNull(product);
		assertNotNull(product.getId());
		assertEquals(productDto.getDescription(), product.getDescription());
		assertEquals(productDto.getPrice(), product.getPrice());
		assertEquals(Constants.NO, product.getIsAvailable());
	}
}
