package tuning.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import tuning.entities.Order;

public interface OrderRepository extends JpaRepository<Order, UUID> {

}
