package mgmt.store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import mgmt.store.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query("Select p From Product p where p.price > :min and p.price < :max ")
	List<Product> findProductWithinPriceRange(Long min, Long max);
}
