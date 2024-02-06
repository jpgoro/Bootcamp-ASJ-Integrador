package com.bootcamp.backendSgc.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.backendSgc.models.DetailOcModel;
import com.bootcamp.backendSgc.models.ProductModel;
import com.bootcamp.backendSgc.models.PurchaseOrderModel;
import com.bootcamp.backendSgc.repositories.DetailOcRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class DetailOCService {
	@Autowired
	DetailOcRepository detailOCRepository;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	PurchaseOrderService purchaseOrderService;
	
	public List<DetailOcModel> getOrderDetails() {
        return detailOCRepository.findAll();
    }

    public Optional<DetailOcModel> getOrderDetailsById(Integer id) {
    	Optional<DetailOcModel> detailOc = detailOCRepository.findById(id);
       	if(detailOc.isPresent()) {
       		return detailOc;
       	} else {
       		throw new EntityNotFoundException("Error: Detail order " + id + " was not found");
       	}
    }
    
    public Optional<List<DetailOcModel>> getOrderDetailByOrderId(Integer orderId) {
    	PurchaseOrderModel order = purchaseOrderService.getOrderById(orderId).orElseThrow(() ->
        new EntityNotFoundException("Order with ID " + orderId + " not found"));
    	return Optional.ofNullable(detailOCRepository.findByPurchaseOrder(order));
    }

    public List<DetailOcModel> createOrderDetails(List<DetailOcModel> orderDetailsList) {
        List<DetailOcModel> savedOrderDetails = new ArrayList<>();

        orderDetailsList.forEach(orderDetail -> {
        	if(validateOrderDetails(orderDetail)) {
                orderDetail.setCreatedAt(LocalDateTime.now());

                Optional<ProductModel> optionalProduct = productService.getProductById(orderDetail.getProduct().getId());
                ProductModel product = optionalProduct.orElseThrow(() -> new EntityNotFoundException("Product not found with ID: " + orderDetail.getProduct().getId()));

                Optional<PurchaseOrderModel> optionalOrder = purchaseOrderService.getOrderById(orderDetail.getPurchaseOrder().getId());
                PurchaseOrderModel order = optionalOrder.orElseThrow(() -> new EntityNotFoundException("Order not found with ID: " + orderDetail.getPurchaseOrder().getId()));
                orderDetail.setProduct(product);
                orderDetail.setPurchaseOrder(order);

                savedOrderDetails.add(detailOCRepository.save(orderDetail));
        	}
        });

        return savedOrderDetails;
    }
    
    public void deleteOrderDetails(Integer id) {
    	detailOCRepository.deleteById(id);
    }



//    public List<OrderDetail> updateOrderDetails(Integer id, List<OrderDetail> updatedOrderDetailsList) {   
//    	
//        
//        Order order = orderService.getOrderById(id).orElseThrow(() ->
//        new EntityNotFoundException("Order with ID " + id + " not found"));
//        List<OrderDetail> updatedOrderDetails = orderDetailsRepository.findByOrder(order);
//        updatedOrderDetails.forEach(item -> {
//        	deleteOrderDetails(item.getId());
//        });
//        
//        return createOrderDetails(updatedOrderDetailsList);
//    }

    
    
    
    private boolean validateOrderDetails(DetailOcModel orderDetails) {
    	
        if(orderDetails.getQuantity() < 1) {
        	return false;
        }
        if(orderDetails.getPrice() < 1) {
        	return false;
        }
        
        return true;
    }


    
}
