package com.food.api.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.food.api.domain.model.Restaurant;

public interface RestaurantRepositoryQueries {
	List<Restaurant> find(String name, BigDecimal beginDeliveryTax, BigDecimal endDeliveryTax);

	List<Restaurant> findWithFreeDeliveryTax(String name);
}