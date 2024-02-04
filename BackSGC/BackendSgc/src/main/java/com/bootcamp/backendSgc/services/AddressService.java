package com.bootcamp.backendSgc.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.backendSgc.models.AddressModel;
import com.bootcamp.backendSgc.models.LocalityModel;
import com.bootcamp.backendSgc.models.SupplierModel;
import com.bootcamp.backendSgc.repositories.AddressRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AddressService {
	
	@Autowired
	AddressRepository addressRepository;
	@Autowired
	LocalityService localityService;
	@Autowired
	SupplierService supplierService;
	
	
	public List<AddressModel> getAddresses() {
        return addressRepository.findAll();
    }

    public Optional<AddressModel> getAddressById(Integer id) {
        return addressRepository.findById(id);
    }
    
    public Optional<List<AddressModel>> getAddressBySupplierId(Integer supplierId) {
    	SupplierModel supplier = supplierService.getSupplierById(supplierId).orElseThrow(() ->
        new IllegalArgumentException("Supplier with ID " + supplierId + " not found"));
    	return Optional.ofNullable(addressRepository.findBySupplier(supplier));
    }

    public AddressModel postAddress(AddressModel address) {

    	if(validateAddressInput(address)) {
            if (address.getCreatedAt() == null) {
                address.setCreatedAt(LocalDateTime.now());
            }

            SupplierModel supplier = supplierService.getSupplierById(address.getSupplier().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Supplier not found"));

            LocalityModel location = localityService.getLocalityById(address.getLocality().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Location not found"));

            address.setSupplier(supplier);
            address.setLocality(location);

            return addressRepository.save(address);
    	}
    	
    	return null;
    }

    public AddressModel putAddress(Integer id, AddressModel modAddress) {
        Optional<AddressModel> optionalAddress = addressRepository.findById(id);

        if (optionalAddress.isPresent() && validateAddressInput(modAddress)) {
            AddressModel address = optionalAddress.get();

            SupplierModel supplier = supplierService.getSupplierById(modAddress.getSupplier().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Error: Supplier not found"));

            LocalityModel locality = localityService.getLocalityById(modAddress.getLocality().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Error: Location not found"));

            address.setStreet(modAddress.getStreet());
            address.setNumber(modAddress.getNumber());
            address.setLocality(locality);
            address.setSupplier(supplier);
            address.setPostalCode(modAddress.getPostalCode());

            updateTimestamp(address);

            return addressRepository.save(address);
        }

        return null;
    }

    public void deleteAddress(Integer id) {
        addressRepository.deleteById(id);
    }

    private boolean validateAddressInput(AddressModel address) {
        String regex1 = "^[0-9 A-Za-z]{3,50}$";
        String regex2 = "^[0-9]{4,8}$";

        if (!address.getStreet().matches(regex1)) {
            return false;
        }
        if (!address.getPostalCode().matches(regex2)) {
            return false;
        }
        if (address.getNumber() < 1) {
            return false;
        }
        return true;
    }


    private void updateTimestamp(AddressModel address) {
        address.setUpdatedAt(LocalDateTime.now());
    }
}
