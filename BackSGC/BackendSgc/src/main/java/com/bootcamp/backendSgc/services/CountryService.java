package com.bootcamp.backendSgc.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.backendSgc.models.CountryModel;
import com.bootcamp.backendSgc.repositories.CountryRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CountryService {
	@Autowired
	CountryRepository countryRepository;
	
	public List<CountryModel> getAllCountries(){
		return countryRepository.findAll();
	}
	
	public Optional<CountryModel> getCountryById(Integer id){
		Optional<CountryModel> country = countryRepository.findById(id);
    	if(country.isPresent()) {
    		return country;
    	} else {
    		throw new EntityNotFoundException("Country " + id + " was not found");
    	}
	}
	
	public CountryModel createCountry(CountryModel country) {
		country.setCreatedAt(LocalDateTime.now());
		return countryRepository.save(country);
	}
	
	public void deleteCountry(Integer id) {
		countryRepository.deleteById(id);
	}
}
