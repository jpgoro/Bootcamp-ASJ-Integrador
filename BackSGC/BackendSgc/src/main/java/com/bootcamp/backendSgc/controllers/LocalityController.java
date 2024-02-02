package com.bootcamp.backendSgc.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.backendSgc.extra.ErrorHandler;
import com.bootcamp.backendSgc.models.LocalityModel;
import com.bootcamp.backendSgc.services.LocalityService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/localities")
public class LocalityController {
	
	@Autowired
	LocalityService localityService;
	
	@GetMapping()
	public ResponseEntity<?> getLocalities() {
		try {
			List<LocalityModel> localities = localityService.getLocalities();
			return ResponseEntity.ok(localities);
		}catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: localities not found");
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getLocalitiesById(@PathVariable Integer id) {
        try {
            Optional<LocalityModel> locality = localityService.getLocalityById(id);
            return ResponseEntity.ok(locality);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Locality not found by id");
        }
    }
	
	@PostMapping
	public ResponseEntity<?> postLocality(@Valid @RequestBody LocalityModel locality, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> errors = ErrorHandler.handleErrors(bindingResult);
                return ResponseEntity.badRequest().body(errors);
            }

            LocalityModel newLocality = localityService.postLocation(locality);
            return ResponseEntity.ok(newLocality);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Locality not created");
        }
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<?> putLocality(@PathVariable Integer id, @Valid @RequestBody LocalityModel locality, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> errors = ErrorHandler.handleErrors(bindingResult);
                return ResponseEntity.badRequest().body(errors);
            }

            LocalityModel auxLocality = localityService.updateLocality(id, locality);
            return auxLocality != null ? ResponseEntity.ok(auxLocality) : ResponseEntity.notFound().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Locality not updated: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLocality(@PathVariable Integer id) {
        try {
            localityService.deleteLocality(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Locality not deleted");
        }
    }
	
}
