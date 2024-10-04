package com.food.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.api.domain.exception.ResourceNotFoundException;
import com.food.api.domain.model.Kitchen;
import com.food.api.domain.model.Restaurant;
import com.food.api.domain.repository.KitchenRepository;
import com.food.api.domain.repository.RestaurantRepository;

@Service
public class RegisterRestaurantService {

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	@Autowired
	private KitchenRepository kitchenRepository; 
	
	public Restaurant save(Restaurant restaurant) throws ResourceNotFoundException{
		long kitchenId = restaurant.getKitchen().getId(); 
		Kitchen kitchen = kitchenRepository.findById(kitchenId)
				.orElseThrow(()-> new ResourceNotFoundException(
				  String.format("There is no Kitchen registrantion with code %d\n", kitchenId)
				)); 
		restaurant.setKitchen(kitchen);
		return restaurantRepository.save(restaurant); 
	}
}
