package tuning.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import tuning.models.Customer;

public interface CustomerRepository extends JpaRepository<Customer, UUID> {}
