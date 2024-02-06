package com.bootcamp.backendSgc.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.backendSgc.extra.ErrorHandler;
import com.bootcamp.backendSgc.models.SupplierModel;
import com.bootcamp.backendSgc.services.SupplierService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {
	@Autowired
	SupplierService supplierService;
	
	@GetMapping
    public ResponseEntity<?> getAllSuppliers() {
        try {
            List<SupplierModel> suppliers = supplierService.getSuppliers();
            return new ResponseEntity<>(suppliers, HttpStatus.OK);
        } catch (Exception e) {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: suppliers not fetched");
        }
    }

    @GetMapping("/active")
    public ResponseEntity<?> getActiveSuppliers() {
        try {
            List<SupplierModel> active = supplierService.getSuppliersActive();
            return new ResponseEntity<>(active, HttpStatus.OK);
        } catch (Exception e) {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: active suppliers not fetched");
        }
    }

    @GetMapping("/deleted")
    public ResponseEntity<?> getDeletedSuppliers() {
        try {
            List<SupplierModel> supplierDel = supplierService.getSuppliersDeleted();
            return new ResponseEntity<>(supplierDel, HttpStatus.OK);
        } catch (Exception e) {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: deleted suppliers not fetched ");
        }
    }
    
    @GetMapping("/legalNameAsc")
    public ResponseEntity<?> getSuppliersByBusinessNameAsc() {
        try {
            List<SupplierModel> suppliers = supplierService.getSuppliersByBusinessNameAsc();
            return new ResponseEntity<>(suppliers, HttpStatus.OK);
        } catch (Exception e) {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: SuppliersByBusinessNameAsc NOT FETCHED");
        }
    }
    
    @GetMapping("/legalnameDesc")
    public ResponseEntity<?> getSuppliersByBusinessNameDesc() {
        try {
            List<SupplierModel> suppliers = supplierService.getSuppliersByBusinessNameDesc();
            return new ResponseEntity<>(suppliers, HttpStatus.OK);
        } catch (Exception e) {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: SuppliersByBusinessNameDesc NOT FETCHED");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSupplierById(@PathVariable Integer id) {
        try {
            Optional<SupplierModel> supplier = supplierService.getSupplierById(id);
            return new ResponseEntity<>(supplier, HttpStatus.OK);
        } catch (Exception e) {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: supplier by id NOT FETCHED");
        }
    }

    @PostMapping
    public ResponseEntity<?> createSupplier(@Valid @RequestBody SupplierModel supplier, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> errors = ErrorHandler.handleErrors(bindingResult);
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }

            SupplierModel newSupplier = supplierService.postSupplier(supplier);
            return new ResponseEntity<>(newSupplier, HttpStatus.CREATED); 
        } catch (EntityNotFoundException e) {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> updateSupplier(@PathVariable Integer id, @Valid @RequestBody SupplierModel supplier, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> errors = ErrorHandler.handleErrors(bindingResult);
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }

            SupplierModel modSupplier = supplierService.putSupplier(id, supplier);
            if (modSupplier != null) {
                return new ResponseEntity<>(modSupplier, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Supplier not found", HttpStatus.NOT_FOUND);
            }
        } catch (EntityNotFoundException e) {
        	return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
    @PatchMapping("/{id}/undelete")
    public ResponseEntity<?> undeleteSupplierById(@PathVariable Integer id) {
        try {
            SupplierModel supplier = supplierService.undeleteSupplierById(id);
            return new ResponseEntity<>(supplier, HttpStatus.OK);
        } catch (Exception e) {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to undelete the supplier");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteSupplier(@PathVariable Integer id) {
        try {
            SupplierModel supplier = supplierService.deleteSupplier(id);
            if (supplier != null) {
                return new ResponseEntity<>(supplier, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Supplier not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: supplier not deleted");
        }
    }

    

    
}
