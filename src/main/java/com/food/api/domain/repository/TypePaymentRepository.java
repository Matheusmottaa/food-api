package com.food.api.domain.repository;

import java.util.List;

import com.food.api.domain.model.TypePayment;

public interface TypePaymentRepository {
	List<TypePayment> listAll(); 
	TypePayment findById(Long id); 
	TypePayment add(TypePayment payment); 
	void delete(TypePayment payment); 
}
