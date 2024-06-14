package mgmt.store.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import mgmt.store.model.AVAILABILITY;
import mgmt.store.model.Product;
import mgmt.store.service.ProductService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
		List<Product> expectedProducts = new ArrayList<>(
				Arrays.asList(new Product(Long.valueOf(1), "Products1", 100.0f, "Description for Product1", AVAILABILITY.NO),
						new Product(Long.valueOf(2), "Products2", 430.0f, "Description for Product2", AVAILABILITY.YES)));
		when(service.getAllProducts()).thenReturn(expectedProducts);
		MvcResult result = this.mockMvc.perform(get("/product")).andExpect(status().isOk()).andReturn();

		String json = result.getResponse().getContentAsString();
		List<Product> products = objectMapper.readValue(json, new TypeReference<>() {
		});
		assertNotNull(products);
		assertEquals(2, products.size());
	}
}
