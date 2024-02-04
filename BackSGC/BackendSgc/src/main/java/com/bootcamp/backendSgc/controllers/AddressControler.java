package com.bootcamp.backendSgc.controllers;

import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.backendSgc.extra.ErrorHandler;
import com.bootcamp.backendSgc.models.AddressModel;
import com.bootcamp.backendSgc.services.AddressService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/address")
public class AddressControler {
	@Autowired
	AddressService addressService;
	
	 @GetMapping()
	    public ResponseEntity<?> getAddresses() {
	        try {
	            List<AddressModel> addresses = addressService.getAddresses();
	            return ResponseEntity.ok(addresses);
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Address not fetched");
	        }
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<?> getAddressById(@PathVariable Integer id) {
	    	try {
	    		 return ResponseEntity.ok(addressService.getAddressById(id));
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: address by id not fetched");
			}
	       
	    }

	    @GetMapping("/suppliers/{id}")
	    public ResponseEntity<?> getAddressBySupplierId(@PathVariable Integer id) {
	    	try {
	    		return ResponseEntity.ok(addressService.getAddressBySupplierId(id));
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: addresses by supplier id not fetched ");
			}
	        
	    }
	    
	    @PostMapping()
	    public ResponseEntity<?> postAddress(@Valid @RequestBody AddressModel address, BindingResult bindingResult) {
	    	try {
	            if (bindingResult.hasErrors()) {
	                Map<String, String> errors = ErrorHandler.handleErrors(bindingResult);
	                return ResponseEntity.badRequest().body(errors);
	            }
	            AddressModel createdAddress = addressService.postAddress(address);
	            return ResponseEntity.status(HttpStatus.CREATED).body(createdAddress);
			} catch (EntityNotFoundException e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			}
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<?> putAddress(@PathVariable Integer id, @Valid @RequestBody AddressModel updatedAddress, BindingResult bindingResult) {
	    	try {
	            if (bindingResult.hasErrors()) {
	                Map<String, String> errors = ErrorHandler.handleErrors(bindingResult);
	                return ResponseEntity.badRequest().body(errors);
	            }

	            AddressModel result = addressService.putAddress(id, updatedAddress);
	            if (result != null) {
	                return ResponseEntity.ok(result);
	            } else {
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Address not found");
	            }
			} catch (EntityNotFoundException e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
			}

	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<?> deleteAddress(@PathVariable Integer id) {
	    	try {
	            addressService.deleteAddress(id);
	            return ResponseEntity.ok().build();
			} catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: address not deleted");
			}

	    }
}
