package com.food.api.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.food.api.domain.model.Kitchen;

@Repository
public interface KitchenRepository extends JpaRepository<Kitchen, Long> {
	List<Kitchen> findByName(String name);

	List<Kitchen> findAllByNameContaining(String name);
}
