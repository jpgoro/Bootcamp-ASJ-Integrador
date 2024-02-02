package com.bootcamp.backendSgc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.backendSgc.models.SupplierModel;

public interface SupplierRepository extends JpaRepository<SupplierModel, Integer>{
	List<SupplierModel> findByActiveFalse();
	List<SupplierModel> findByActiveTrue();
	List<SupplierModel> findAllByOrderByLegalNameAsc();
	List<SupplierModel> findAllByOrderByLegalNameDesc();

}
