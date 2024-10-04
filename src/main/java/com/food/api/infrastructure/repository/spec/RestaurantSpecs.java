package com.food.api.infrastructure.repository.spec;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.food.api.domain.model.Restaurant;

// Utilizando o padrão factory
public class RestaurantSpecs {
	public static Specification<Restaurant> withFreeDeliveryTax() { 
		return (root, query, builder) -> 
				builder.equal(root.get("deliveryTax"), BigDecimal.ZERO); 
	}
	
	public static Specification<Restaurant> likeName(String name) { 
		return (root, query, builder) -> 
					builder.like(root.get("name"), "%" + name + "%"); 
	}
}
