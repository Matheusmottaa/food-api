package com.food.api.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.food.api.domain.model.State;

@Repository
public interface StateRepository extends JpaRepository<State, Long>{
}
