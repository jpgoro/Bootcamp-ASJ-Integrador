package com.bootcamp.backendSgc.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.backendSgc.models.PurchaseOrderModel;
import com.bootcamp.backendSgc.models.StatusModel;
import com.bootcamp.backendSgc.models.SupplierModel;
import com.bootcamp.backendSgc.repositories.PurchaseOrderRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PurchaseOrderService {
	
	@Autowired
	PurchaseOrderRepository purchaseOrderRepository;
	
	 @Autowired
	 SupplierService supplierService;

	 @Autowired
	 StatusService statusService;
	 
	 public List<PurchaseOrderModel> getAllOrders() {
	        return purchaseOrderRepository.findAll();
	    }

	    public List<PurchaseOrderModel> getActiveOrders() {
	        return purchaseOrderRepository.findByActiveTrue();
	    }

	    public List<PurchaseOrderModel> getDeletedOrders() {
	        return purchaseOrderRepository.findByActiveFalse();
	    }

	    public Optional<PurchaseOrderModel> getOrderById(Integer orderId) {
	        return purchaseOrderRepository.findById(orderId);
	    }

		public List<PurchaseOrderModel> getOrdersByStatusId(Integer statusId) {
			StatusModel status = statusService.getStatusById(statusId).orElseThrow(() ->
	        new EntityNotFoundException("Status with ID " + statusId + " not found"));
			return purchaseOrderRepository.findByStatus(status);
		}

	    
	    public PurchaseOrderModel createOrder(PurchaseOrderModel newOrder) {
	    	
	    	List<PurchaseOrderModel> orders = getAllOrders();
	    	
	    	for (PurchaseOrderModel order2 : orders) {
				if(order2.getNumber().equals(newOrder.getNumber())) {
					throw new EntityNotFoundException("The order number is used");
				}
			}
	    	
	    	if(validateOrder(newOrder)) {
	            if (newOrder.getCreatedAt() == null) {
	                newOrder.setCreatedAt(LocalDateTime.now());
	            }

	            SupplierModel orderSupplier = supplierService.getSupplierById(newOrder.getSupplier().getId())
	                    .orElseThrow(() -> new EntityNotFoundException("Supplier not found with ID: " + newOrder.getSupplier().getId()));

	            StatusModel orderStatus = statusService.getStatusById(newOrder.getStatus().getId())
	                    .orElseThrow(() -> new EntityNotFoundException("Status not found with ID: " + newOrder.getStatus().getId()));

//	            User orderUser = userService.getUserById(newOrder.getUser().getId())
//	                    .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + newOrder.getUser().getId()));
	            
	            if(orderStatus.getId() == 2) {
	            	newOrder.setActive(false);
	            }

	            newOrder.setSupplier(orderSupplier);
	            newOrder.setStatus(orderStatus);
	           // newOrder.setUser(orderUser);

	            return purchaseOrderRepository.save(newOrder);
	    	} else {
	    		throw new EntityNotFoundException("Values are wrong");
	    	}
	    }


	    public PurchaseOrderModel desactivateOrderById(Integer orderId) {
	        Optional<PurchaseOrderModel> optionalOrder = purchaseOrderRepository.findById(orderId);

	        if (optionalOrder.isPresent()) {
	        	StatusModel updatedStatus = statusService.getStatusById(2).orElseThrow(
	             		() -> new EntityNotFoundException("Status not found with ID: " + 2));
	        	PurchaseOrderModel orderToDeactivate = optionalOrder.get();
	            orderToDeactivate.setStatus(updatedStatus);
	            orderToDeactivate.setActive(false);
	            orderToDeactivate.setUpdatedAt(LocalDateTime.now());
	            return purchaseOrderRepository.save(orderToDeactivate);
	        } else {
	        	throw new EntityNotFoundException("Order can't be canceled");
	        }
	    }

	    public PurchaseOrderModel updateOrder(Integer orderId, PurchaseOrderModel updatedOrder) {
	        Optional<PurchaseOrderModel> optionalOrder = purchaseOrderRepository.findById(orderId);
	        
	        String initOrderNumber = optionalOrder.get().getNumber();
	        
	        if(!initOrderNumber.equals(updatedOrder.getNumber())) {
	        	List<PurchaseOrderModel> orders = getAllOrders();
	        	for (PurchaseOrderModel order2 : orders) {
	    			if(order2.getNumber().equals(updatedOrder.getNumber())) {
	    				throw new EntityNotFoundException("The order number is used");
	    			}
	    		}
	        }

	        if (optionalOrder.isPresent() && validateOrder(updatedOrder)) {
	        	PurchaseOrderModel existingOrder = optionalOrder.get();

	            SupplierModel updatedSupplier = supplierService.getSupplierById(updatedOrder.getSupplier().getId()).orElseThrow(
	            		() -> new EntityNotFoundException("Supplier not found with ID: " + updatedOrder.getSupplier().getId()));
	            StatusModel updatedStatus = statusService.getStatusById(updatedOrder.getStatus().getId()).orElseThrow(
	            		() -> new EntityNotFoundException("Status not found with ID: " + updatedOrder.getStatus().getId()));
//	            User updatedUser = userService.getUserById(updatedOrder.getUser().getId()).orElseThrow(
//	            		() -> new EntityNotFoundException("User not found with ID: " + updatedOrder.getUser().getId()));

	            existingOrder.setSupplier(updatedSupplier);
	            existingOrder.setStatus(updatedStatus);
//	            existingOrder.setUser(updatedUser);
	            existingOrder.setDeliveryDate(updatedOrder.getDeliveryDate());
	            existingOrder.setIssueDate(updatedOrder.getIssueDate());
	            existingOrder.setReception(updatedOrder.getReception());
	            existingOrder.setNumber(updatedOrder.getNumber());

	            existingOrder.setUpdatedAt(LocalDateTime.now());

	            return purchaseOrderRepository.save(existingOrder);
	        } else {	
	        	throw new EntityNotFoundException("Values are wrong");
	        }
	    }
	    
	    private boolean validateOrder(PurchaseOrderModel order) {
	    	
	        String regex1 = "^[0-9]{1,12}$";
	        
	    	if(!order.getNumber().matches(regex1)) {
	    		return false;
	    	}
	    	if(order.getReception().length() < 15) {
	    		return false;
	    	}
	    	
	    	return true;
	    }

	    public PurchaseOrderModel undeleteOrderById(Integer orderId) {
	        Optional<PurchaseOrderModel> optionalOrder = purchaseOrderRepository.findById(orderId);

	        if (optionalOrder.isPresent()) {
	        	StatusModel updatedStatus = statusService.getStatusById(3).orElseThrow(
	             		() -> new EntityNotFoundException("Status not found with ID: " + 3));
	        	PurchaseOrderModel orderToUndelete = optionalOrder.get();

	            orderToUndelete.setStatus(updatedStatus);
	            orderToUndelete.setActive(true);
	            orderToUndelete.setUpdatedAt(LocalDateTime.now());

	            return purchaseOrderRepository.save(orderToUndelete);
	        } else {
	        	throw new EntityNotFoundException("Order can't be activated");
	        }
	    }
}
