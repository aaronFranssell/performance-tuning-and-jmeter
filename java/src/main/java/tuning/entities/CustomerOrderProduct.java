package tuning.entities;

import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CustomerOrderProduct {
	@Id
	@Column(name = "id", nullable = false)
	private UUID orderId;
	
    @Column(name = "order_date", nullable = false)
    private OffsetDateTime orderDate;
	
	@Column(name = "first_name", nullable = false)
	private String firstName;
	
	@Column(name = "last_name", nullable = false)
	private String lastName;
	
    @Column(name = "customer_id", nullable = false)
    private UUID customerId;
    
    @Column(name = "product_id", nullable = false)
    private UUID productId;
    
	@Column(name = "description", nullable = false)
	private String description;
}
