package com.food.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.food.api.domain.exception.ResourceInUseException;
import com.food.api.domain.exception.ResourceNotFoundException;
import com.food.api.domain.model.State;
import com.food.api.domain.repository.StateRepository;

@Service
public class RegisterStateService {

	@Autowired
	private StateRepository stateRepository;

	public State save(State state) {
		return stateRepository.save(state);
	}

	public void delete(Long stateId) throws ResourceNotFoundException, ResourceInUseException {
		try {
			if (!stateRepository.existsById(stateId)) {
				throw new ResourceNotFoundException(String.format("There is no state with id %d\n", stateId));
			}
			stateRepository.deleteById(stateId);
		} catch (DataIntegrityViolationException e) {
			throw new ResourceInUseException(
					String.format("The State with ID %d cannot be removed because it is currently in use.\n", stateId));
		}
	}

}
