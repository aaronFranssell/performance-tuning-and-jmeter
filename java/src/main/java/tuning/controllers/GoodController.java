package tuning.controllers;

import java.time.OffsetDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import tuning.entities.Customer;
import tuning.entities.CustomerOrderProduct;
import tuning.entities.Order;
import tuning.entities.OrderMini;
import tuning.entities.Product;
import tuning.repositories.CustomerRepositoryGood;
import tuning.repositories.GoodRepository;
import tuning.repositories.OrderMiniRepositoryGood;
import tuning.repositories.ProductRepository;

@RestController
@RequestMapping("/good")
@Log4j2
public class GoodController {
	@Autowired
	GoodRepository goodRepository;
	
	@Autowired
	CustomerRepositoryGood customerRepositoryGood;
	
	@Autowired
	OrderMiniRepositoryGood orderMiniRepositoryGood;
	
	@Autowired
	ProductRepository productRepository;
	
    @PostMapping("/writeCustomerProducts/{customerId}")
    public ResponseEntity<String> writeCustomerProducts(@PathVariable("customerId") UUID customerId) {
    	Customer customer = customerRepositoryGood.findByIdWithProducts(customerId);
    	String result = "";
    	for(Order order : customer.getOrders()) {
    		result += order.getProduct().getId() + "," + order.getProduct().getDescription() + "\n";
    	}
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @GetMapping("/getOrdersAfter")
    public ResponseEntity<List<OrderMini>> getOrdersAfter(@RequestParam("orderDate") OffsetDateTime orderDate) {
    	List<OrderMini> result = orderMiniRepositoryGood.findByOrderDateAfter(orderDate);
		return new ResponseEntity<>(result, HttpStatus.OK);
    }
    
    @PostMapping("/insertProducts")
    public ResponseEntity<String> saveProducts(@RequestParam("numberOfProducts") int numberOfProducts) {
    	List<Product> toInsert = new LinkedList<Product>();
    	for (int i = 0; i < numberOfProducts; i++) {
    		toInsert.add(new Product("Widget " + i));
    	}
    	productRepository.saveAllAndFlush(toInsert);
		return new ResponseEntity<>("OK", HttpStatus.OK);
    }
    
	
	@GetMapping("getCustomerProducts/{customerId}")
	public ResponseEntity<List<CustomerOrderProduct>> getCustomerProducts(@PathVariable("customerId") UUID customerId) {
		List<CustomerOrderProduct> result = goodRepository.findCustomerProductsByCustomerId(customerId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
}
