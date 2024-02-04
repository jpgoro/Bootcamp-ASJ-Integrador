package com.bootcamp.backendSgc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.backendSgc.models.AddressModel;
import com.bootcamp.backendSgc.models.SupplierModel;

public interface AddressRepository extends JpaRepository<AddressModel, Integer>{
	List<AddressModel> findBySupplier(SupplierModel supplier);
}
