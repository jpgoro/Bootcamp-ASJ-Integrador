package com.bootcamp.backendSgc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.backendSgc.models.PurchaseOrderModel;
import com.bootcamp.backendSgc.models.StatusModel;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrderModel, Integer>{
	List<PurchaseOrderModel> findByActiveTrue();
	List<PurchaseOrderModel> findByActiveFalse();
	List<PurchaseOrderModel> findByStatus(StatusModel status);
}
