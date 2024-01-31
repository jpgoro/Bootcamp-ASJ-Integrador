package com.bootcamp.backendSgc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.backendSgc.models.AddressModel;

public interface AddressRepository extends JpaRepository<AddressModel, Integer>{
	
}
