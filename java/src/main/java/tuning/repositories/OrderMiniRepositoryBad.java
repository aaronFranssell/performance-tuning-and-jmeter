package tuning.repositories;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;

import jakarta.persistence.QueryHint;
import jakarta.transaction.Transactional;
import tuning.entities.Order;
import tuning.entities.OrderMini;

public interface OrderMiniRepositoryBad extends JpaRepository<Order, UUID> {
	@Query("SELECT new tuning.entities.OrderMini(o.id, o.orderDate) FROM Order o where o.orderDate > :orderDate")
	@QueryHints({
	    @QueryHint(name = "org.hibernate.fetchSize", value = "1000")
	})
	@Transactional
	List<OrderMini> findByOrderDateAfter(@Param("orderDate") OffsetDateTime orderDate);
}
