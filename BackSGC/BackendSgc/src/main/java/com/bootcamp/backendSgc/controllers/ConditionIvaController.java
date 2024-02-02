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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.backendSgc.extra.ErrorHandler;
import com.bootcamp.backendSgc.models.ConditionIvaModel;
import com.bootcamp.backendSgc.services.ConditionIvaService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/iva")
public class ConditionIvaController {
	
	@Autowired
	ConditionIvaService conditionIvaService;
	
	@GetMapping
    public ResponseEntity<?> getIvaConditionList() {
        try {
            List<ConditionIvaModel> ivaConditions = conditionIvaService.getIvaConditions();
            return ResponseEntity.ok(ivaConditions);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: conditionsIva not fetched");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getIvaConditionById(@PathVariable Integer id) {
    	try {
            Optional<ConditionIvaModel> ivaCondition = conditionIvaService.getIvaConditionById(id);
            return ResponseEntity.ok(ivaCondition);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: Industry not fetched by ID:");
        }
    }

    @PostMapping
    public ResponseEntity<?> postIvaCondition(@Valid @RequestBody ConditionIvaModel ivaCondition, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> errors = ErrorHandler.handleErrors(bindingResult);
                return ResponseEntity.badRequest().body(errors);
            }

            ConditionIvaModel createdIvaCondition = conditionIvaService.postIvaCondition(ivaCondition);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdIvaCondition);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error, conditionIva not created: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteIvaCondition(@PathVariable Integer id) {
        try {
            conditionIvaService.deleteIvaCondition(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error: conditionIva not deleted");
        }
    }
}
