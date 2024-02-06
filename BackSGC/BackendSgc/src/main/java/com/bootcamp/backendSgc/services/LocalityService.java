package com.bootcamp.backendSgc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.backendSgc.models.LocalityModel;
import com.bootcamp.backendSgc.models.ProvinceModel;
import com.bootcamp.backendSgc.repositories.LocalityRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class LocalityService {
	
	@Autowired
	LocalityRepository localityRepository;
	@Autowired
	ProvinceService provinceService;
	
	public List<LocalityModel> getLocalities(){
		return localityRepository.findAll();
	}
	public Optional<LocalityModel> getLocalityById(Integer id) {
		Optional<LocalityModel> locality = localityRepository.findById(id);
    	if(locality.isPresent()) {
    		return locality;
    	} else {
    		throw new EntityNotFoundException("Locality  " + id + " was not found");
    	}
    }
	
	public LocalityModel postLocation(LocalityModel locality) {
    	if(validateLocalityInput(locality)) {
    		ProvinceModel province = provinceService.getProvinceById(locality.getProvince().getId())
    				.orElseThrow(() -> new EntityNotFoundException("Province not found"));
    		
    		locality.setProvince(province);
    		
    		return localityRepository.save(locality);
    	} else {
    		throw new EntityNotFoundException("Error: Locality don't created");
    	}
    }
	
	 public LocalityModel updateLocality(Integer id, LocalityModel locality) {
	        Optional<LocalityModel> optionalLocality = localityRepository.findById(id);

	        if (optionalLocality.isPresent() && validateLocalityInput(locality)) {
	            LocalityModel auxLocality = optionalLocality.get();

	            ProvinceModel province = provinceService.getProvinceById(locality.getProvince().getId()).orElseThrow(() -> new EntityNotFoundException("Province not found"));

	            auxLocality.setProvince(province);
	            auxLocality.setLocalityName(locality.getLocalityName());
	            
	            return localityRepository.save(auxLocality);
	        }else {
	    		throw new EntityNotFoundException("Error: locality don't updated");
	    	}
	    }
	 public void deleteLocality(Integer id) {
	        localityRepository.deleteById(id);
	    }
	 
	private boolean validateLocalityInput(LocalityModel locality) {
        String auxiliar = "^[0-9 A-Za-z]{3,50}$";
    	if(!locality.getLocalityName().matches(auxiliar)) {
    		return false;
    	}
    	return true;
    }
}
