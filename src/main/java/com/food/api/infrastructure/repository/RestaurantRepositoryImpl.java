package com.food.api.infrastructure.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.food.api.domain.model.Restaurant;
import com.food.api.domain.repository.RestaurantRepository;
import com.food.api.domain.repository.RestaurantRepositoryQueries;
import com.food.api.infrastructure.repository.spec.RestaurantSpecs;

@Repository
public class RestaurantRepositoryImpl implements RestaurantRepositoryQueries{

	@PersistenceContext
	private EntityManager manager; 
	
	@Autowired @Lazy
	private RestaurantRepository restaurantRepository; 

	@Override
	public List<Restaurant> find(String name, BigDecimal beginDeliveryTax, BigDecimal endDeliveryTax) { 
		CriteriaBuilder builder = manager.getCriteriaBuilder(); // Constrói uma instância de criteria Builder. (CriteriaBuilder é um construtor de clausulas). 
		CriteriaQuery<Restaurant> criteria = builder.createQuery(Restaurant.class); // Criteria Query é um construtor de clásulas
		Root<Restaurant> rootRestaurant = criteria.from(Restaurant.class); 
		List<Predicate> predicates = new ArrayList<>(); 
		if(StringUtils.hasText(name)) { 
			predicates.add(builder.like(rootRestaurant.get("name"), "%"+name+"%"));  
		}  
		if(beginDeliveryTax != null) { 
			predicates.add(  
						builder.greaterThanOrEqualTo(rootRestaurant.get("deliveryTax"), beginDeliveryTax));   
		}
		if(endDeliveryTax != null) { 
			predicates.add(
					builder
					.lessThanOrEqualTo(rootRestaurant.get("deliveryTax"), endDeliveryTax));
		}
		criteria.where(predicates.toArray(new Predicate[0])); 
		return manager.createQuery(criteria)
				.getResultList(); 
	}
	
	@Override
	public List<Restaurant> findWithFreeDeliveryTax(String name) {
		return restaurantRepository
				.findAll(RestaurantSpecs.withFreeDeliveryTax().and(RestaurantSpecs.likeName(name)));
	}
} 