package com.bootcamp.backendSgc.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.backendSgc.models.CategoryModel;
import com.bootcamp.backendSgc.repositories.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;
	
	public List<CategoryModel> getAllCategories(){
		return categoryRepository.findAll();
	}
	
	public Optional<CategoryModel> getCategoryById(Integer id) {
		return categoryRepository.findById(id);
	}
	
	public CategoryModel postCategory(CategoryModel category) {
		category.setCreatedAt(LocalDateTime.now());
		category.setDeleted(false);
		 return categoryRepository.save(category);
	}
	
	
}
