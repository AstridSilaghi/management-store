package mgmt.store.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import mgmt.store.model.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	Optional<Order> findByOrderNumber(String number);
	
}
