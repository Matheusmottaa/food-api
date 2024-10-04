package com.food.api.domain.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.food.api.domain.exception.ResourceInUseException;
import com.food.api.domain.exception.ResourceNotFoundException;
import com.food.api.domain.model.Kitchen;
import com.food.api.domain.repository.KitchenRepository;

@Service
public class RegisterKitchenService {
	
	@Autowired
	private KitchenRepository kitchenRepository; 
	
	public Kitchen save(Kitchen kitchen) { 
		return kitchenRepository.save(kitchen); 
	}
	
	public Kitchen update(Long kitchenId, Kitchen kitchen) throws ResourceNotFoundException { 
		Kitchen currentKitchen = kitchenRepository
				.findById(kitchenId)
				.orElseThrow(()-> new ResourceNotFoundException("There is no kitchen registration with code %d\n"));
		BeanUtils.copyProperties(kitchen, currentKitchen, "id");
		return currentKitchen; 
	}
	
	public void delete(Long kitchenId) throws ResourceInUseException, ResourceNotFoundException{ 
		try { 
			if(!kitchenRepository.existsById(kitchenId)) { 
				throw new ResourceNotFoundException(
						String.format("There is no kitchen registration with code %d\n", kitchenId)
					);
			}
			kitchenRepository.deleteById(kitchenId);	
		}catch(DataIntegrityViolationException e) { 
			throw new ResourceInUseException( 
						String.format("The kitchen with ID %d cannot be removed because it is currently in use.\n", kitchenId)
					); 
		}		
	}
}
