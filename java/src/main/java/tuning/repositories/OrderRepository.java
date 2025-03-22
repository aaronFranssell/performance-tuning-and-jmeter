package tuning.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import tuning.models.Order;

public interface OrderRepository extends JpaRepository<Order, UUID> {}
