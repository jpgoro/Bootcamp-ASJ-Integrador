package com.bootcamp.backendSgc.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.backendSgc.models.CategoryModel;
import com.bootcamp.backendSgc.repositories.CategoryRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoryService {
	@Autowired
	CategoryRepository categoryRepository;
	
	public List<CategoryModel> getCategories() {
        return categoryRepository.findAll();
    }

	public Optional<CategoryModel> getCategoryById(Integer id) {
    	Optional<CategoryModel> optionalCategory = categoryRepository.findById(id);
    	if(optionalCategory.isPresent()) {
    		return optionalCategory;
    	} else {
    		throw new EntityNotFoundException("Category  " + id + " was not found");
    	}
    }
	
	public List<CategoryModel> getActiveCategories() {
        return categoryRepository.findByActiveTrue();
    }
    
    public List<CategoryModel> getDeletedCategories() {
        return categoryRepository.findByActiveFalse();
    }

    public CategoryModel createCategory(CategoryModel category) {
    	List<CategoryModel> categories = getCategories();
    	for (CategoryModel auxcategory : categories) {
			if(auxcategory.getName().equalsIgnoreCase(category.getName())) {
				throw new EntityNotFoundException("Category already exists");
			}
		}
        category.setCreatedAt(LocalDateTime.now());
        return categoryRepository.save(category);
    }
    
    public CategoryModel updateCategory(Integer id, CategoryModel modCategory) {
    	Optional<CategoryModel> optionalCategory = categoryRepository.findById(id);
    	
    	List<CategoryModel> categories = getCategories();
    	for (CategoryModel auxCategory : categories) {
			if(auxCategory.getName().equalsIgnoreCase(modCategory.getName())) {
				throw new EntityNotFoundException("Category already exists");
			}
		}
    	if (optionalCategory.isPresent()) {
            CategoryModel category = optionalCategory.get();
            category.setName(modCategory.getName());
            return categoryRepository.save(category);
        } else {	
        	throw new EntityNotFoundException("Error: category don't updated");
        }
    	
    }
    public CategoryModel undeleteCategoryById(Integer id) {
    	Optional<CategoryModel> optionalCategory = categoryRepository.findById(id);

        if (optionalCategory.isPresent()) {
            CategoryModel category = optionalCategory.get();
            category.setActive(true);
            return categoryRepository.save(category);
        } else {
        	throw new EntityNotFoundException("Error: category don't undeleted");
        }
    }

    public CategoryModel deleteCategory(Integer id) {
        Optional<CategoryModel> optionalCategory = categoryRepository.findById(id);

        if (optionalCategory.isPresent()) {
            CategoryModel category = optionalCategory.get();
            category.setActive(false);
            return categoryRepository.save(category);
        }else {
        	throw new EntityNotFoundException("Category don't deleted");
        }
    }
    
    

}
