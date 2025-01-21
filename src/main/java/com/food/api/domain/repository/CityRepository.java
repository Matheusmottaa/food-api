package com.food.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.food.api.domain.model.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
