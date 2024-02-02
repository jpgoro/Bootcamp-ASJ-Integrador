package com.bootcamp.backendSgc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.backendSgc.models.ContactModel;
import com.bootcamp.backendSgc.models.SupplierModel;

public interface ContactRepository extends JpaRepository<ContactModel, Integer>{
	List<ContactModel> findBySupplier(SupplierModel supplier);
}
