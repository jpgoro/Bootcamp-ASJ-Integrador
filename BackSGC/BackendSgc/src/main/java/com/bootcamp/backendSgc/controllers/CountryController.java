package com.bootcamp.backendSgc.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.backendSgc.extra.ErrorHandler;
import com.bootcamp.backendSgc.models.CountryModel;
import com.bootcamp.backendSgc.repositories.CountryRepository;
import com.bootcamp.backendSgc.services.CountryService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/countries")
public class CountryController {
	@Autowired
	CountryService countryService;

	@GetMapping
	public ResponseEntity<List<CountryModel>> getAllCountries() {
		try {
			return ResponseEntity.ok(countryService.getAllCountries());
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<CountryModel> getCountryById(@PathVariable Integer id) {
		try {
			Optional<CountryModel> country = countryService.getCountryById(id);
			if (country.isPresent()) {
				return ResponseEntity.ok(country.get());
			} else {
				return ResponseEntity.notFound().build();
			}
		} catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
			return ResponseEntity.internalServerError().build();
		}
	}
	
	@PostMapping
    public ResponseEntity<Object> postCountry(@Valid @RequestBody CountryModel country, BindingResult bindingResult) {
    	try {
            if (bindingResult.hasErrors()) {
                Map<String, String> errors = ErrorHandler.handleErrors(bindingResult);
                return ResponseEntity.badRequest().body(errors);
            }
            CountryModel newCountry = countryService.createCountry(country);
            return ResponseEntity.status(HttpStatus.CREATED).body(newCountry);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCountry(@PathVariable Integer id) {
    	try {
            countryService.deleteCountry(id);
            return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error deleting country");
		}
    }
}
