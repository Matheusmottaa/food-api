package com.food.api.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity 
@Table(name = "orders")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id; 
	
	private BigDecimal deliveryTax; 
	private BigDecimal totalValue; 
	private BigDecimal subtotal; 
	
	@CreationTimestamp
	private LocalDateTime createdAt; 
	
	private LocalDateTime confirmedAt; 
	private LocalDateTime cancelledAt; 
	private LocalDateTime deliveryAt; 
	
	@Embedded
	private Address deliveryAddress; 
	
	@ManyToOne
	private Restaurant restaurant; 
	
	private OrderStatus status; 
	
	@ManyToOne
	private TypePayment paymentType;

	
	@ManyToOne
	@JoinColumn(nullable = false)
	private User customer; 
	
	private OrderStatus orderStatus; 
	
	@OneToMany(mappedBy = "order")
	private List<OrderItem> itens = new ArrayList<>(); 

}
