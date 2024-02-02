package com.bootcamp.backendSgc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.backendSgc.models.AddressModel;
import com.bootcamp.backendSgc.repositories.AddressRepository;

@Service
public class AddressService {
	
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	LocalityService localityService;
	
	
	public List<AddressModel> getAddress(){
		return addressRepository.findAll();
	}
	public Optional<AddressModel> getAddressById(Integer id) {
        return addressRepository.findById(id);
    }
	
}
