package tuning.entities;

import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderMini {
	public OrderMini(UUID id, OffsetDateTime orderDate) {
		this.id = id;
		this.orderDate = orderDate;
	}
	
	@Id
	@GeneratedValue
	private UUID id;
	
    @Column(name = "order_date", nullable = false)
    private OffsetDateTime orderDate;
}
