package tuning.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import tuning.models.Customer;
import tuning.models.Order;
import tuning.repositories.CustomerRepository;

@RestController
@RequestMapping("/bad")
@Log4j2
public class BadController {
	@Autowired
	CustomerRepository customerRepository;
	
    @GetMapping("/getCustomerById/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable("customerId") UUID customerId) {
    	Customer result = customerRepository.findById(customerId).get();
    	log.info("result: " + result.getFirstName());
    	for(Order order : result.getOrders()) {
    		log.info(order.getProduct().getDescription());
    	}
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
