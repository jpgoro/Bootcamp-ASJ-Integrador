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
import com.bootcamp.backendSgc.models.PurchaseOrderModel;
import com.bootcamp.backendSgc.services.PurchaseOrderService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/purchaseOrder")
public class PurchaseOrderController {
	@Autowired
	PurchaseOrderService purchaseOrderService;
	
	@GetMapping()
    public ResponseEntity<?> getOrders() {
        try {
            List<PurchaseOrderModel> orders = purchaseOrderService.getAllOrders();
            return ResponseEntity.ok(orders);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching orders");
        }
    }

    @GetMapping("/active")
    public ResponseEntity<?> getOrdersActive() {
        try {
            List<PurchaseOrderModel> activeOrders = purchaseOrderService.getActiveOrders();
            return ResponseEntity.ok(activeOrders);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching active orders");
        }
    }

    @GetMapping("/deleted")
    public ResponseEntity<?> getOrdersDeleted() {
        try {
            List<PurchaseOrderModel> deletedOrders = purchaseOrderService.getDeletedOrders();
            return ResponseEntity.ok(deletedOrders);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching deleted orders");
        }
    }
    
    @GetMapping("/status/{statusId}")
    public ResponseEntity<?> getOrdersByStatusId(@PathVariable Integer statusId) {
        try {
            List<PurchaseOrderModel> statusOrders = purchaseOrderService.getOrdersByStatusId(statusId);
            return ResponseEntity.ok(statusOrders);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching deleted orders");
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Integer id) {
        try {
            Optional<PurchaseOrderModel> order = purchaseOrderService.getOrderById(id);
            return ResponseEntity.ok(order);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching order by id");
        }
    }

    @PostMapping()
    public ResponseEntity<?> postOrder(@Valid @RequestBody PurchaseOrderModel order, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> errors = ErrorHandler.handleErrors(bindingResult);
                return ResponseEntity.badRequest().body(errors);
            }
            return ResponseEntity.ok(purchaseOrderService.createOrder(order));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while creating order: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(purchaseOrderService.desactivateOrderById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting order by id");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putOrder(@Valid @RequestBody PurchaseOrderModel order, @PathVariable Integer id, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> errors = ErrorHandler.handleErrors(bindingResult);
                return ResponseEntity.badRequest().body(errors);
            }
            return ResponseEntity.ok(purchaseOrderService.updateOrder(id, order));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while updating order: " + e.getMessage());
        }
    }

    @PatchMapping("/{id}/undelete")
    public ResponseEntity<?> undeleteOrder(@PathVariable Integer id) {
        try {
            return ResponseEntity.ok(purchaseOrderService.undeleteOrderById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while undeleting order by id");
        }
    }
}
