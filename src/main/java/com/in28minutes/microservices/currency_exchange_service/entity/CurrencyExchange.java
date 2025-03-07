package com.in28minutes.microservices.currency_exchange_service.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// /h2-console
// jdbc:h2:mem:tsetdb
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyExchange {

	@Id
	private Long id;
	@Column(name="currency_from")
	private String from;
	@Column(name="currency_to")
	private String to;
	private BigDecimal conversionMultiple;
	
	private String environment;
	
}
