package com.food.api.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id; 
	
	@Column(nullable = false)
	private String name; 
	
	@Column(nullable = false)
	private BigDecimal deliveryTax; 
	
	private Boolean active; 
	
	private Boolean open; 

	@JsonIgnore
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime registerDate; 

	@JsonIgnore
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime updatedDate;  
	
	@ManyToOne
	private Kitchen kitchen; 
	
	@JsonIgnore
	@Embedded
	private Address address; 
	
	@JsonIgnore
	@ManyToMany
	@JoinTable(name = "restaurant_type_payments", 
	joinColumns = @JoinColumn(name = "restaurant_id"), inverseJoinColumns = @JoinColumn(name = "type_payment_id"))
	private List<TypePayment> typesPayments = new ArrayList<>();
	
	@JsonIgnore
	@OneToMany(mappedBy = "restaurant")
	private List<Product> products = new ArrayList<>(); 
		
	public Restaurant(String name, BigDecimal deliveryTax, Boolean active, Boolean open, 
				LocalDateTime registerDate, LocalDateTime updatedDate){ 
		this.name = name; 
		this.deliveryTax = deliveryTax; 
		this.active = active; 
		this.open = open; 
		this.registerDate = registerDate; 
		this.updatedDate = updatedDate; 
	}
}