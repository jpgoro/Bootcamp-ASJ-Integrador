package com.bootcamp.backendSgc.controllers;

import com.bootcamp.backendSgc.extra.ErrorHandler;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.backendSgc.models.CategoryModel;
import com.bootcamp.backendSgc.services.CategoryService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/categories")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@GetMapping
	public ResponseEntity<List<CategoryModel>> getCategory() {
		try {
			return ResponseEntity.ok(categoryService.getCategories());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<CategoryModel> getCategoryById(@PathVariable Integer id) {
		try {
			Optional<CategoryModel> opCategory = categoryService.getCategoryById(id);
			if (opCategory.isPresent()) {
				return ResponseEntity.ok(opCategory.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}
	
	 @GetMapping("/deleted")
	    public ResponseEntity<?> getDeletedCategories() {
	    	try {
	    		return ResponseEntity.ok(categoryService.getDeletedCategories());
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: categories not fetched");
			}
	    }
	
    @GetMapping("/active")
    public ResponseEntity<?> getActiveCategories() {
    	try {
    		return ResponseEntity.ok(categoryService.getActiveCategories());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error:categories not fetched");
		}
    }
    
   

	@PostMapping
	public ResponseEntity<?> postCategory(@Valid @RequestBody CategoryModel category, BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = ErrorHandler.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}
			CategoryModel newCategory = categoryService.createCategory(category);
			return ResponseEntity.status(HttpStatus.CREATED).body(newCategory);
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<?> putCategory(@PathVariable Integer id, @Valid @RequestBody CategoryModel category,
			BindingResult bindingResult) {
		try {
			if (bindingResult.hasErrors()) {
				Map<String, String> errors = ErrorHandler.handleErrors(bindingResult);
				return ResponseEntity.badRequest().body(errors);
			}
			CategoryModel updatedCategory = categoryService.updateCategory(id, category);
			return ResponseEntity.status(HttpStatus.CREATED).body(updatedCategory);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : " + e.getMessage());
		}
	}
	
    @PatchMapping("/undelete/{id}")
    public ResponseEntity<?> undeleteCategoryById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(categoryService.undeleteCategoryById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : category don't undeleted");
        }
    }

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategory(@PathVariable Integer id) {
		try {
			categoryService.deleteCategory(id);
			return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting category");
		}
	}

}
