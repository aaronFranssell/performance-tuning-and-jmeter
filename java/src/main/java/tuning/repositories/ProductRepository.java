package tuning.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import tuning.entities.Product;

public interface ProductRepository extends JpaRepository<Product, UUID> {}
