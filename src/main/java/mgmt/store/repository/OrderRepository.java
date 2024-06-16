package mgmt.store.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mgmt.store.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	Optional<Order> findByOrderNumber(String number);

	@Query(value = "SELECT id FROM orders INNER JOIN order_product ON order_product.product_id = :productId AND orders.id = order_product.order_id", nativeQuery = true)
	List<Long> getOrdersIdHavingProduct(Long productId);
	
	@Query(value = "SELECT * FROM orders INNER JOIN order_product ON order_product.product_id = :productId AND orders.id = order_product.order_id", nativeQuery = true)
	List<Order> getOrdersHavingProduct(Long productId);

}
