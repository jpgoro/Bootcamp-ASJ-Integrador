package com.bootcamp.backendSgc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.backendSgc.models.DetailOcModel;
import com.bootcamp.backendSgc.models.PurchaseOrderModel;

public interface DetailOcRepository extends JpaRepository<DetailOcModel, Integer>{
	List<DetailOcModel> findByPurchaseOrder(PurchaseOrderModel purchaseOrder);
}
