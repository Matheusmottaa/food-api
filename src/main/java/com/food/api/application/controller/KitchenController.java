package com.food.api.application.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.food.api.domain.exception.ResourceInUseException;
import com.food.api.domain.exception.ResourceNotFoundException;
import com.food.api.domain.model.Kitchen;
import com.food.api.domain.repository.KitchenRepository;
import com.food.api.domain.service.RegisterKitchenService;
import com.food.api.model.KitchenXmlWrapper;

@RestController
@RequestMapping("/kitchens")
public class KitchenController {

	@Autowired
	private KitchenRepository kitchenRepo;

	@Autowired
	private RegisterKitchenService registerKitchen;

	// Collection Resource
	@GetMapping
	public ResponseEntity<List<Kitchen>> list() {
		return ResponseEntity.ok(kitchenRepo.findAll());
	}

	// Singleton Resource
	@GetMapping("/{kitchenId}")
	public ResponseEntity<Kitchen> get(@PathVariable Long kitchenId) {
		Optional<Kitchen> kitchen = kitchenRepo.findById(kitchenId);
		if (kitchen == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(kitchen.get());
	}

	@GetMapping(produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<KitchenXmlWrapper> listXml() {
		return ResponseEntity.ok(new KitchenXmlWrapper(kitchenRepo.findAll()));
	}

	@PostMapping
	public ResponseEntity<Kitchen> create(@RequestBody Kitchen kitchen) {
		return ResponseEntity.status(HttpStatus.CREATED).body(registerKitchen.save(kitchen));
	}

	@PutMapping("/{kitchenId}")
	public ResponseEntity<Kitchen> updateCook(@PathVariable Long kitchenId, @RequestBody Kitchen kitchen) {
		try {
			return ResponseEntity.status(HttpStatus.OK)
					.body(kitchenRepo.save(registerKitchen.update(kitchenId, kitchen)));
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}

	@DeleteMapping("/{kitchenId}")
	public ResponseEntity<Kitchen> removeCook(@PathVariable Long kitchenId) {
		try {
			registerKitchen.delete(kitchenId);
			return ResponseEntity.noContent().build();
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.notFound().build();
		} catch (ResourceInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	@GetMapping(value = "/teste")
	public ResponseEntity<List<Kitchen>> teste(@RequestParam(required = false) String name) {
		return ResponseEntity.ok(kitchenRepo.findAllByNameContaining(name));
	}

}
