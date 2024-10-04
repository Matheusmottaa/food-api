package com.food.api.domain.repository;

import java.util.List;

import com.food.api.domain.model.Permission;

public interface PermissionRepository {
	List<Permission> listAll(); 
	Permission findById(Long id); 
	Permission add(Permission perm); 
	void delete(Permission perm); 
}
