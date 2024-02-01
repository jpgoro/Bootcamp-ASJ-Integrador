package com.bootcamp.backendSgc.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.backendSgc.extra.ErrorHandler;
import com.bootcamp.backendSgc.models.ProvinceModel;
import com.bootcamp.backendSgc.services.ProvinceService;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/provinces")
public class ProvinceController {
	
	@Autowired
	ProvinceService provinceService;
	
	@GetMapping
	public ResponseEntity<?> getProvinces() {
        try {
            List <ProvinceModel> provinces = provinceService.getProvinces();
            return ResponseEntity.ok(provinces);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: fetching provinces");
        }
    }
	
	@GetMapping("/{id}")
    public ResponseEntity<?> getProvinceById(@PathVariable Integer id) {
        try {
            Optional<ProvinceModel> province = provinceService.getProvinceById(id);
            return ResponseEntity.ok(province);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: fetching province by id");
        }
    }
	
	@GetMapping("/country/{countryId}")
    public ResponseEntity<?> getProvincesByCountryId(@PathVariable Integer countryId) {
        try {
            List<ProvinceModel> provinces = provinceService.getProvincesByCountryId(countryId);
            return ResponseEntity.ok(provinces);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: fetching provinces by country_id");
        }
    }
	
	@PostMapping()
    public ResponseEntity<?> postProvince(@Valid @RequestBody ProvinceModel province, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> errors = ErrorHandler.handleErrors(bindingResult);
                return ResponseEntity.badRequest().body(errors);
            }

            ProvinceModel newProvince = provinceService.createProvince(province);
            return ResponseEntity.status(HttpStatus.CREATED).body(newProvince);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
	
	@PutMapping("/{id}")
    public ResponseEntity<?> putProvince(@PathVariable Integer id, @Valid @RequestBody ProvinceModel province, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> errors = ErrorHandler.handleErrors(bindingResult);
                return ResponseEntity.badRequest().body(errors);
            }

            ProvinceModel auxProvince = provinceService.updateProvince(id, province);
            if (auxProvince != null) {
                return ResponseEntity.ok(auxProvince);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Province not found");
            }
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
	
	@DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProvince(@PathVariable Integer id) {
        try {
            provinceService.deleteProvince(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("The province could not be deleted");
        }
    }
}
