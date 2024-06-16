package mgmt.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mgmt.store.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("Select p From Product p where p.price > :min")
	List<Product> findProductPriceGreater(Long min);

	@Query("Select p From Product p where p.price < :max ")
	List<Product> findProductPriceLess(Long max);

	@Query("Select p From Product p where p.price > :min and p.price < :max ")
	List<Product> findProductWithinPriceRange(Long min, Long max);

	@Query("Select p From Product p where p.isAvailable = 'Y'")
	List<Product> findProductsAvailable();

	@Query(value = "SELECT * FROM products INNER JOIN order_product ON order_product.order_id = :orderId AND products.id = order_product.product_id", nativeQuery = true)
	List<Product> getProductsPartOfOrder(Long orderId);

}
