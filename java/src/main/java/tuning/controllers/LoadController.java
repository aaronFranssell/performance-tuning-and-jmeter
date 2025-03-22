package tuning.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;
import tuning.services.DataGeneratorService;

@RestController
@RequestMapping("/load")
@Log4j2
public class LoadController {
	@Autowired
	private DataGeneratorService dataGeneratorService;
	
	@PostMapping()
	public ResponseEntity<String> load(@RequestParam("numberOfOrders") int numberOfOrders) {
		dataGeneratorService.generateData(numberOfOrders);
		return new ResponseEntity<>("Data seeded", HttpStatus.OK);
	}
}
