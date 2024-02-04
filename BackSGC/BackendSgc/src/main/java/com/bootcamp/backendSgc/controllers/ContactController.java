package com.bootcamp.backendSgc.controllers;

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
import com.bootcamp.backendSgc.models.ContactModel;
import com.bootcamp.backendSgc.services.ContactService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/contacts")
public class ContactController {
	
	@Autowired
	ContactService contactService;
	
	@GetMapping
    public ResponseEntity<?> getAllContacts() {
    	try {
            return ResponseEntity.ok(contactService.getAllContacts());
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: contacts not fetched");
		}

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getContactById(@PathVariable Integer id) {
    	try {	
    		return ResponseEntity.ok(contactService.getContactById(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: contacts by id not fetched");
		}
    }
    
    @GetMapping("/suppliers/{id}")
    public ResponseEntity<?> getContactBySupplier(@PathVariable Integer id) {
    	try {
    		return ResponseEntity.ok(contactService.getContactBySupplier(id));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: contacts by supplier not fetched");
		}
    }

    @PostMapping
    public ResponseEntity<?> postContact(@Valid @RequestBody ContactModel contact, BindingResult bindingResult) {
    	try {
            if (bindingResult.hasErrors()) {
                Map<String, String> errors = ErrorHandler.handleErrors(bindingResult);
                return ResponseEntity.badRequest().body(errors);
            }

            ContactModel newContact = contactService.createContact(contact);
            return ResponseEntity.status(HttpStatus.CREATED).body(newContact);
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putContact(@PathVariable Integer id, @Valid @RequestBody ContactModel contact, BindingResult bindingResult) {
    	try {
            if (bindingResult.hasErrors()) {
                Map<String, String> errors = ErrorHandler.handleErrors(bindingResult);
                return ResponseEntity.badRequest().body(errors);
            }

            ContactModel auxContact = contactService.updateContact(id, contact);
            if (auxContact != null) {
                return ResponseEntity.ok(auxContact);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Contact not found");
            }
		} catch (EntityNotFoundException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteContact(@PathVariable Integer id) {
    	try {
    		contactService.deleteContactById(id);
    		return ResponseEntity.noContent().build();
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: contact not deleted");
		}
    }
}
