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
	
	public List<CategoryModel> getCategories() {
        return categoryRepository.findAll();
    }

    public Optional<CategoryModel> getCategoryById(Integer category) {
        return categoryRepository.findById(category);
    }

    public CategoryModel createCategory(CategoryModel category) {
        category.setCreatedAt(LocalDateTime.now());
        return categoryRepository.save(category);
    }
    
    public CategoryModel updateCategory(Integer id, CategoryModel modCategory) {
    	Optional<CategoryModel> optionalCategory = categoryRepository.findById(id);
    	
    	if (optionalCategory.isPresent()) {
            CategoryModel category = optionalCategory.get();
            category.setName(modCategory.getName());
            return categoryRepository.save(category);
        }

        return null;
    }

    public CategoryModel deleteCategory(Integer id) {
        Optional<CategoryModel> optionalCategory = categoryRepository.findById(id);

        if (optionalCategory.isPresent()) {
            CategoryModel category = optionalCategory.get();
            category.setActive(false);
            return categoryRepository.save(category);
        }

        return null;
    }

}
