package tuning.services;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.extern.log4j.Log4j2;
import tuning.entities.Customer;
import tuning.entities.Order;
import tuning.entities.Product;
import tuning.repositories.CustomerRepository;
import tuning.repositories.OrderRepository;
import tuning.repositories.ProductRepository;

@Service
@Log4j2
public class DataGeneratorService {
	public static final int ORDER_STEP = 100000;
	public static final int PARENT_RECORD_FACTOR = 100;
	public static final int PARENT_STEP = ORDER_STEP/PARENT_RECORD_FACTOR; 
	public static final OffsetDateTime START_DATE = OffsetDateTime.parse("2025-01-01T00:00:00+00:00");
	public static final OffsetDateTime END_DATE = OffsetDateTime.parse("2025-03-31T00:00:00+00:00");
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
    @PersistenceContext
    private EntityManager entityManager;
	
	private SecureRandom secureRandom = new SecureRandom();
	
	public void generateData(int numberOfOrders) {
		for(int i = 0; i < numberOfOrders; i += ORDER_STEP) {
			ArrayList<Customer> customers = new ArrayList<Customer>();
			ArrayList<Product> products = new ArrayList<Product>();
			log.info("Generating " + PARENT_STEP + " customers and products. Generated " + i/PARENT_RECORD_FACTOR + " of each so far. Will generate " + numberOfOrders/PARENT_RECORD_FACTOR + " total.");
			for(int j = 0; j < ORDER_STEP/PARENT_RECORD_FACTOR; j++) {
				customers.add(generateCustomer(j));
				products.add(generateProduct(j));
			}
			customerRepository.saveAllAndFlush(customers);
			productRepository.saveAllAndFlush(products);
			log.info("Generated parent records. Generating " + ORDER_STEP + " orders. Generated " + i + " of " + numberOfOrders + " total.");
			ArrayList<Order> orders = new ArrayList<Order>();
			for (int j = 0; j < ORDER_STEP; j++) {
				orders.add(generateOrder(customers, products));
			}
			orderRepository.saveAllAndFlush(orders);
			entityManager.clear();
			log.info("Generated step.");
		}
	}
	
	private Customer generateCustomer(int j) {
		return new Customer("First " + j, "Last " + j);
	}
	
	private Product generateProduct(int j) {
		return new Product("Widget " + j);
	}
	
	private Order generateOrder(ArrayList<Customer> customers, ArrayList<Product> products) {
		Order result = new Order();
		result.setOrderDate(generateRandomOffsetDateTime());
		Customer customer = customers.get(secureRandom.nextInt(customers.size()));
		Product product = products.get(secureRandom.nextInt(customers.size()));
		result.setCustomer(customer);
		result.setProduct(product);
		result.setCustomerId(customer.getId());
		result.setProductId(product.getId());
		return result;
	}
	
    private OffsetDateTime generateRandomOffsetDateTime() {
        long startEpochSecond = START_DATE.toEpochSecond();
        long endEpochSecond = END_DATE.toEpochSecond();
        long randomEpochSecond = startEpochSecond + secureRandom.nextLong(endEpochSecond - startEpochSecond + 1);
        return Instant.ofEpochSecond(randomEpochSecond).atOffset(START_DATE.getOffset());
    }
}
