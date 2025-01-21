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

import com.food.api.domain.exception.ResourceInUseException;
import com.food.api.domain.exception.ResourceNotFoundException;
import com.food.api.domain.model.State;
import com.food.api.domain.repository.StateRepository;
import com.food.api.domain.service.RegisterStateService;

@RestController
@RequestMapping("/states")
public class StateController {

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private RegisterStateService registerState;

	@GetMapping
	public ResponseEntity<List<State>> list() {
		return ResponseEntity.ok(stateRepository.findAll());
	}

	@GetMapping("/{stateId}")
	public ResponseEntity<State> get(@PathVariable Long stateId) {
		State state = stateRepository.findById(stateId).orElse(null);
		if (state == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(state);
	}

	@PostMapping
	public ResponseEntity<State> add(@RequestBody State state) {
		return ResponseEntity.status(HttpStatus.CREATED).body(registerState.save(state));
	}

	@PutMapping("/{stateId}")
	public ResponseEntity<State> update(@PathVariable Long stateId, @RequestBody State state) {
		State currentState = stateRepository.findById(stateId).orElse(null);
		if (currentState == null) {
			return ResponseEntity.notFound().build();
		}
		BeanUtils.copyProperties(state, currentState, "id");
		return ResponseEntity.ok(stateRepository.save(currentState));
	}

	@DeleteMapping("/{stateId}")
	public ResponseEntity<?> remove(@PathVariable Long stateId) {
		try {
			registerState.delete(stateId);
			return ResponseEntity.noContent().build();
		} catch (ResourceNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (ResourceInUseException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
		}
	}

}
