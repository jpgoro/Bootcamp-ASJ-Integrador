package com.bootcamp.backendSgc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.backendSgc.models.SupplierModel;

public interface SupplierRepository extends JpaRepository<SupplierModel, Integer>{

}
