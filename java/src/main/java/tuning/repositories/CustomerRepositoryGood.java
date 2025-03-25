package tuning.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tuning.entities.Customer;

public interface CustomerRepositoryGood extends JpaRepository<Customer, UUID> {
    @EntityGraph(attributePaths = {"orders", "orders.product"})
    @Query("select c from Customer c where c.id = :id")
	Customer findByIdWithProducts(@Param("id") UUID id);
}
