package com.food.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.food.api.domain.exception.ResourceNotFoundException;
import com.food.api.domain.model.City;
import com.food.api.domain.model.State;
import com.food.api.domain.repository.CityRepository;
import com.food.api.domain.repository.StateRepository;

@Service
public class RegisterCityService {

	@Autowired
	private CityRepository cityRepository; 
	
	@Autowired 
	private StateRepository stateRepository; 
	
	public City save(City city) throws ResourceNotFoundException { 
		Long stateId = city.getState().getId(); 
		State state = stateRepository.findById(stateId).orElse(null);
		if(state == null) { 
			throw new ResourceNotFoundException(
						String.format("There's no state register with id %d\n", stateId)
					);
		}
		city.setState(state);
		return cityRepository.save(city); 
	}
	
	public void remove(Long cityId) throws ResourceNotFoundException { 
		if(!cityRepository.existsById(cityId)) { 
			throw new ResourceNotFoundException(
						String.format("There's no city register with id %d\n", cityId)
					);
		}
		cityRepository.deleteById(cityId); 
	}
	
}
