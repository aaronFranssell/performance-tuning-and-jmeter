package tuning.models;

import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "orders")
@Entity
public class Order {
	@Id
	@GeneratedValue
	private UUID id;
	
    @Column(name = "order_date", nullable = false)
    private OffsetDateTime orderDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID_WITH_INDEX")
    private Customer customer;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRODUCT_ID_WITH_INDEX")
    private Product product;
    
    @Column(name = "customer_id", nullable = false)
    private UUID customerId;
    
    @Column(name = "product_id", nullable = false)
    private UUID productId;
}
