package com.in28minutes.microservices.currency_exchange_service.resilience;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;

@RestController
public class CircuitBreakerController {

	private Logger logger = LoggerFactory.getLogger(CircuitBreakerController.class);
	
	// Retry --> se realizan 3 intentos de acceder al endpoint de la API REST antes de devolver error
	@GetMapping("/sample-api")
	//@Retry(name="sample-api",fallbackMethod="harcodedResponse")
	@CircuitBreaker(name="default",fallbackMethod="harcodedResponse")
	@RateLimiter(name="default")
	@Bulkhead(name="default")
	public String sampleApi() {
		
		logger.info("Sample Api Call received");
		
		//String url_dummy = "http://localhost:8080/some-dummy-url"; 
		//ResponseEntity<String> responseEntity = new RestTemplate().getForEntity(url_dummy, String.class);
		
		
		return "Sample API";
	}
	
	// Metodo de excepcion invocado por Retry
	public String harcodedResponse(Exception e) {
		
		String str = "fallback-response: " + e.getMessage();
		
		return str;
		
	}
	
	
}
