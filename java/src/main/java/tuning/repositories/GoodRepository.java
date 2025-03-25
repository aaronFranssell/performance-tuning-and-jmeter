package tuning.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tuning.entities.CustomerOrderProduct;

public interface GoodRepository extends JpaRepository<CustomerOrderProduct, UUID> {
	@Query(value = """
			select
			c.first_name, c.last_name, o.id, o.customer_id, o.product_id, o.order_date, p.description
			from
			customers c inner join
			orders o on o.CUSTOMER_ID_WITH_INDEX = c.id inner join
			products p on o.PRODUCT_ID_WITH_INDEX = p.id
			where c.id = :id
			""",
			nativeQuery = true)
	List<CustomerOrderProduct> findCustomerProductsByCustomerId(@Param("id") UUID id);
}
