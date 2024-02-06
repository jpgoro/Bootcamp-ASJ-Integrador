package com.bootcamp.backendSgc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.backendSgc.models.CategoryModel;

public interface CategoryRepository extends JpaRepository<CategoryModel, Integer>{
	List<CategoryModel> findByActiveTrue();
	List<CategoryModel> findByActiveFalse();
}
