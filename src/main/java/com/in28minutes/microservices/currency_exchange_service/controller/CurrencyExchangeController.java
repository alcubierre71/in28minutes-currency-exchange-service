package com.in28minutes.microservices.currency_exchange_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.in28minutes.microservices.currency_exchange_service.entity.CurrencyExchange;
import com.in28minutes.microservices.currency_exchange_service.repository.CurrencyExchangeRepository;

// http://localhost:8000/currency-exchange/from/USD/to/INR
@RestController
@RequestMapping("/currency-exchange")
public class CurrencyExchangeController {

	@Autowired
	private Environment environment;
	
	@Autowired
	private CurrencyExchangeRepository repo;
	
	@GetMapping("/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
		
		//CurrencyExchange exchange = new CurrencyExchange(1000L, from, to, BigDecimal.valueOf(50), "");
		CurrencyExchange exchange = repo.findByFromAndTo(from, to);
		
		if (exchange == null) {
			throw new RuntimeException("Unable to find data for " + from + " to " + to);
		}
		
		
		// Recuperamos el puerto en el que se esta ejecutando la instancia actual del microservicio
		String port = environment.getProperty("local.server.port");
		
		exchange.setEnvironment(port);
		
		return exchange;
		
	}
	
}
