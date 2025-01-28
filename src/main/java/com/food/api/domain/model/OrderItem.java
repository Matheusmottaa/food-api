package com.food.api.domain.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class OrderItem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id; 
	
	private Integer quantity;
	
	private BigDecimal unitPrice; 
	
	private BigDecimal totalAmount; 
	
	private String orderNote; 
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Order order; 
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Product product; 
}
