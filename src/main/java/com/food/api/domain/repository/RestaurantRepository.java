package com.food.api.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.food.api.domain.model.Restaurant;
import com.food.api.domain.model.TypePayment;

@Repository
public interface RestaurantRepository extends CustomJpaRepository<Restaurant, Long>, RestaurantRepositoryQueries,
		JpaSpecificationExecutor<Restaurant> {

	@Query("FROM Restaurant where kitchen.name LIKE %:name%")
	List<Restaurant> findByKitchen(String name);

	List<Restaurant> findTop2ByNameContaining(String name);

	@Query("FROM Restaurant r join fetch r.kitchen lect join fetch r.typesPayments")
	List<Restaurant> findAll(); 
	
	
	int countByKitchenId(Long kitchenId);
}
