package com.food.api.application.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food.api.domain.exception.ResourceNotFoundException;
import com.food.api.domain.model.City;
import com.food.api.domain.repository.CityRepository;
import com.food.api.domain.service.RegisterCityService;

@RestController
@RequestMapping("/cities")
public class CityController {

	@Autowired
	private CityRepository cityRepository;

	@Autowired
	private RegisterCityService registerCity;

	@GetMapping
	public ResponseEntity<List<City>> list() {
		return ResponseEntity.ok(cityRepository.findAll());
	}

	@GetMapping("/{cityId}")
	public ResponseEntity<City> get(@PathVariable Long cityId) {
		City city = cityRepository.findById(cityId).orElse(null);
		if (city == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(city);
	}

	@PostMapping
	public ResponseEntity<?> create(@RequestBody City city) {
		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(registerCity.save(city));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@PutMapping("/{cityId}")
	public ResponseEntity<?> update(@PathVariable Long cityId, @RequestBody City city) {
		try {
			City currentCity = cityRepository.findById(cityId).orElse(null);
			if (currentCity == null) {
				return ResponseEntity.notFound().build();
			}
			BeanUtils.copyProperties(city, currentCity, "id");
			return ResponseEntity.ok(registerCity.save(currentCity));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
	}

	@DeleteMapping("/{cityId}")
	public ResponseEntity<?> remove(@PathVariable Long cityId) {
		try {
			registerCity.remove(cityId);
			return ResponseEntity.noContent().build();
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
