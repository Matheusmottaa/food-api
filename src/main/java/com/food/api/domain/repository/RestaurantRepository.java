package com.food.api.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.food.api.domain.model.Restaurant;

@Repository
public interface RestaurantRepository 
	extends CustomJpaRepository<Restaurant, Long>, RestaurantRepositoryQueries, 
	JpaSpecificationExecutor<Restaurant>{
	
	@Query("FROM Restaurant where kitchen.name LIKE %:name%")
	List<Restaurant> findByKitchen(String name); 
	List<Restaurant> findTop2ByNameContaining(String name);
	int countByKitchenId(Long kitchenId); 
}
