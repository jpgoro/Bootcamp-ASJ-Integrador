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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.backendSgc.extra.ErrorHandler;
import com.bootcamp.backendSgc.models.StatusModel;
import com.bootcamp.backendSgc.services.StatusService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/status")
public class StatusController {
	@Autowired
	StatusService statusService;
	
	@GetMapping()
    public ResponseEntity<?> getStatus() {
        try {
            List<StatusModel> statuses = statusService.getStatuses();
            return new ResponseEntity<>(statuses, HttpStatus.OK);
        } catch (Exception e) {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: status not fetched");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getStatusById(@PathVariable Integer id) {
        try {
            Optional<StatusModel> status = statusService.getStatusById(id);
            return new ResponseEntity<>(status, HttpStatus.OK);
        } catch (Exception e) {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: status by id not fetched");
        }
    }

    @PostMapping()
    public ResponseEntity<Object> createStatus(@Valid @RequestBody StatusModel status, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> errors = ErrorHandler.handleErrors(bindingResult);
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }

            StatusModel createdStatus = statusService.createStatus(status);
            return new ResponseEntity<>(createdStatus, HttpStatus.OK);
        } catch (EntityNotFoundException e) {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStatus(@PathVariable Integer id, @Valid @RequestBody StatusModel updatedStatus, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> errors = ErrorHandler.handleErrors(bindingResult);
                return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
            }

            StatusModel result = statusService.updateStatus(id, updatedStatus);
            if (result != null) {
                return new ResponseEntity<>(result, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Status not found", HttpStatus.NOT_FOUND);
            }
        } catch (EntityNotFoundException e) {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStatus(@PathVariable Integer id) {
        try {
            statusService.deleteStatus(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
        	return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Status not deleted");
        }
    }
}
