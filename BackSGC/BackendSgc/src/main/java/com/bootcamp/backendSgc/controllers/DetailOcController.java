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
import com.bootcamp.backendSgc.models.DetailOcModel;
import com.bootcamp.backendSgc.services.DetailOCService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/details")
public class DetailOcController {
	@Autowired
	DetailOCService detailOCService;
	
	@GetMapping()
    public ResponseEntity<?> getAllDetailsOrders() {
        try {
            List<DetailOcModel> orderDetails = detailOCService.getOrderDetails();
            return ResponseEntity.ok(orderDetails);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching order details");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDetailsOrderById(@PathVariable Integer id) {
        try {
            Optional<DetailOcModel> orderDetail = detailOCService.getOrderDetailsById(id);
            return ResponseEntity.ok(orderDetail);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching order detail by id");
        }
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<?> getDetailOrderByOrderId(@PathVariable Integer orderId) {
        try {
            Optional<List<DetailOcModel>> orderDetails = detailOCService.getOrderDetailByOrderId(orderId);
            return ResponseEntity.ok(orderDetails);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching order details by order id");
        }
    }

    @PostMapping()
    public ResponseEntity<?> postDetailOrder(@Valid @RequestBody List<DetailOcModel> orderDetails, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> errors = ErrorHandler.handleErrors(bindingResult);
                return ResponseEntity.badRequest().body(errors);
            }

            List<DetailOcModel> createdOrderDetails = detailOCService.createOrderDetails(orderDetails);
            return ResponseEntity.ok(createdOrderDetails);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while creating order details: " + e.getMessage());
        }
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<?> updateOrderDetails(@PathVariable Integer id, @Valid @RequestBody List<OrderDetail> updatedOrderDetails, BindingResult bindingResult) {
//        try {
//            if (bindingResult.hasErrors()) {
//                Map<String, String> errors = ErrorHandler.validation(bindingResult);
//                return ResponseEntity.badRequest().body(errors);
//            }
//
//            List<OrderDetail> result = orderDetailsService.updateOrderDetails(id, updatedOrderDetails);
//            if (result != null) {
//                return ResponseEntity.ok(result);
//            } else {
//                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("OrderDetails not found");
//            }
//        } catch (EntityNotFoundException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while updating order details: " + e.getMessage());
//        }
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDetailOrder(@PathVariable Integer id) {
        try {
            detailOCService.deleteOrderDetails(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting order details");
        }
    }
}
