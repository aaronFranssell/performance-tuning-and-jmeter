package tuning.models;

import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "products")
@Entity
public class Product {
	public Product() {}
	
	public Product(String description) {
		this.description = description;
	}
	
	@Id
	@GeneratedValue
	private UUID id;
	
	@Column(name = "description", nullable = false)
	private String description;
	
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private Set<Order> products;
}
