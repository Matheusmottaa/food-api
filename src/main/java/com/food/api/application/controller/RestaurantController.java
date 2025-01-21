package com.food.api.application.controller;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.food.api.domain.exception.ResourceNotFoundException;
import com.food.api.domain.model.Restaurant;
import com.food.api.domain.repository.RestaurantRepository;
import com.food.api.domain.service.RegisterRestaurantService;

@RestController
@RequestMapping("/restaurants")
public class RestaurantController {

	@Autowired
	private RestaurantRepository restaurantRepository;

	@Autowired
	private RegisterRestaurantService registerRestaurant;

	@GetMapping
	public ResponseEntity<List<Restaurant>> list() {
		return ResponseEntity.ok(restaurantRepository.findAll());
	}

	@GetMapping("/{restaurantId}")
	public ResponseEntity<Restaurant> get(@PathVariable Long restaurantId) {
		Restaurant restaurant = restaurantRepository.findById(restaurantId).orElse(null);
		if (restaurant == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(restaurant);
	}

	@PostMapping
	public ResponseEntity<?> add(@RequestBody Restaurant restaurant) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(registerRestaurant.save(restaurant));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{restaurantId}")
	public ResponseEntity<?> update(@PathVariable Long restaurantId, @RequestBody Restaurant restaurant) {
		try {
			Restaurant currentRestaurant = restaurantRepository.findById(restaurantId).orElse(null);
			if (currentRestaurant == null) {
				return ResponseEntity.notFound().build();
			}
			BeanUtils.copyProperties(restaurant, currentRestaurant, "id", "typesPayments", "registerDate", "products");
			return ResponseEntity.ok(registerRestaurant.save(currentRestaurant));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PatchMapping("/{restaurantId}")
	public ResponseEntity<?> parcialUpdate(@PathVariable Long restaurantId, @RequestBody Map<String, Object> fields) {
		Restaurant currentRestaurant = restaurantRepository.findById(restaurantId).orElse(null);
		if (currentRestaurant == null) {
			return ResponseEntity.notFound().build();
		}
		merge(fields, currentRestaurant);
		return update(restaurantId, currentRestaurant);
	}

	private void merge(Map<String, Object> sourceFields, Restaurant destRestaurant) {
		ObjectMapper objectMapper = new ObjectMapper();
		Restaurant sourceRestaurantPatch = objectMapper.convertValue(sourceFields, Restaurant.class);
		sourceFields.forEach((keyProp, valueProp) -> {
			Field field = ReflectionUtils.findField(Restaurant.class, keyProp);
			field.setAccessible(true);
			Object newValue = ReflectionUtils.getField(field, sourceRestaurantPatch);
			ReflectionUtils.setField(field, destRestaurant, newValue);
		});
	}
}