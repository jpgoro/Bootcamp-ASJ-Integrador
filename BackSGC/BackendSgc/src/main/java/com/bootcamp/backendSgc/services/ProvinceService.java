package com.bootcamp.backendSgc.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.backendSgc.models.CountryModel;
import com.bootcamp.backendSgc.models.ProvinceModel;
import com.bootcamp.backendSgc.repositories.ProvinceRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProvinceService {
	@Autowired
	ProvinceRepository provinceRepository;
	
	@Autowired
	CountryService countryService;
	
	
	public List<ProvinceModel> getProvinces(){
		return provinceRepository.findAll();
	}
	
	public Optional<ProvinceModel>getProvinceById(Integer id){
		return provinceRepository.findById(id);
	}
	
	//Lista de provincias por id pais
	public List<ProvinceModel> getProvincesByCountryId(Integer countryId){
		CountryModel country = countryService.getCountryById(countryId).orElseThrow(() ->
		new EntityNotFoundException("Country id: "+countryId + "not found"));
		return provinceRepository.findByCountry(country);
	}
	
	public ProvinceModel createProvince(ProvinceModel province) {
        CountryModel country = countryService.getCountryById(province.getCountry().getId()).orElseThrow(() ->
                new EntityNotFoundException("Country id " + province.getCountry().getId() + " not found"));
        province.setCreatedAt(LocalDateTime.now());
        province.setCountry(country);
        return provinceRepository.save(province);
    }
	
	public ProvinceModel updateProvince(Integer id, ProvinceModel province) {
        Optional<ProvinceModel> optionalProvince = provinceRepository.findById(id);

        if (optionalProvince.isPresent()) {
            ProvinceModel modProvince = optionalProvince.get();

            CountryModel country = countryService.getCountryById(province.getCountry().getId()).orElseThrow(() ->
                    new EntityNotFoundException("Country with ID " + province.getCountry().getId() + " not found"));

            modProvince.setCountry(country);
            modProvince.setName(province.getName());

            return provinceRepository.save(modProvince);
        } else {
            throw new EntityNotFoundException("Province not found");
        }
    }
	
	public void deleteProvince(Integer id) {
        provinceRepository.deleteById(id);
    }
}
