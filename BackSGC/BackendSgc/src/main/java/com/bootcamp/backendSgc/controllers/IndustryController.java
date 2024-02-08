package com.bootcamp.backendSgc.controllers;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.backendSgc.extra.ErrorHandler;
import com.bootcamp.backendSgc.models.IndustryModel;
import com.bootcamp.backendSgc.services.IndustryService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/industries")
public class IndustryController {
	
	@Autowired
	IndustryService industryService;
	
	@GetMapping
	public ResponseEntity<?> getAllIndustries() {
        try {
            return new ResponseEntity<>(industryService.getIndustries(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Industries not fetched");
        }
    }
	
    @GetMapping("/active")
    public ResponseEntity<?> getActiveIndustries() {
        try {
            return new ResponseEntity<>(industryService.getActiveIndustries(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : industries not fetched ");
        }
    }
    
    @GetMapping("/deleted")
    public ResponseEntity<?> getDeletedIndustries() {
        try {
            return new ResponseEntity<>(industryService.getDeletedIndustries(), HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: industries not fetched");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getIndustryById(@PathVariable Integer id) {
        try {
            Optional<IndustryModel> industry = industryService.getIndustryById(id);
            return new ResponseEntity<>(industry, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error, Industry not fetched by ID: " + e.getMessage());
        }
    }

    @PostMapping()
    public ResponseEntity<?> postIndustry(@Valid @RequestBody IndustryModel industry, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> errors = ErrorHandler.handleErrors(bindingResult);
                return ResponseEntity.badRequest().body(errors);
            }
            IndustryModel createdIndustry = industryService.createIndustry(industry);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdIndustry);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error, Industry not created: " + e.getMessage());
        }
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> putIndustry (@PathVariable Integer id, @Valid @RequestBody IndustryModel industry, BindingResult bindingResult) {
    	try {
            if (bindingResult.hasErrors()) {
                Map<String, String> errors = ErrorHandler.handleErrors(bindingResult);
                return ResponseEntity.badRequest().body(errors);
            }
            IndustryModel modIndustry = industryService.updateIndustry(id,industry);
            return ResponseEntity.status(HttpStatus.CREATED).body(modIndustry);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Industry not created: " + e.getMessage());
        }
    }
    
    @PatchMapping("/undelete/{id}")
    public ResponseEntity<?> undeleteIndustryById(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(industryService.undeleteIndustryById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: industry don't undeleted");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIndustry(@PathVariable Integer id) {
        try {
            industryService.deleteIndustry(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Industry not deleted");
        }
    }
}
