package com.bootcamp.backendSgc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.backendSgc.models.CategoryModel;

public interface CategoryRepository extends JpaRepository<CategoryModel, Integer>{

}
