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
	
	public CategoryModel createCategory(CategoryModel category) {
		category.setCreatedAt(LocalDateTime.now());
		 return categoryRepository.save(category);
	}
	
	public CategoryModel updateCategory(Integer id, CategoryModel category) {
		Optional<CategoryModel> opCategory = categoryRepository.findById(id);
		if(opCategory.isPresent()) {
			CategoryModel categoryAux = opCategory.get();
			categoryAux.setName(category.getName());
			return categoryRepository.save(categoryAux);
		}
		return null;
	}
	
	public CategoryModel deleteCategory(Integer id) {
		Optional<CategoryModel> opCategory = categoryRepository.findById(id);
		if(opCategory.isPresent()) {
			CategoryModel category = opCategory.get();
			category.setActive(false);
			return categoryRepository.save(category);
		}
		return null;
	}
	
}
