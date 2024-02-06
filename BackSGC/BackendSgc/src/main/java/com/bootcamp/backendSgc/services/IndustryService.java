package com.bootcamp.backendSgc.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.backendSgc.models.IndustryModel;
import com.bootcamp.backendSgc.repositories.IndustryRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class IndustryService {
	
	@Autowired
	IndustryRepository industryRepository;
	
	public List<IndustryModel> getIndustries() {
        return industryRepository.findAll();
    }

    public Optional<IndustryModel> getIndustryById(Integer id) {
    	Optional<IndustryModel> optionalIndustry = industryRepository.findById(id);
    	if(optionalIndustry.isPresent()) {
    		return optionalIndustry;
    	} else {
        	throw new EntityNotFoundException("Error: getIndustryById ");
        }
    }
    
    public List<IndustryModel> getActiveIndustries() {
        return industryRepository.findByActiveTrue();
    }
    
    public List<IndustryModel> getDeletedIndustries() {
        return industryRepository.findByActiveFalse();
    }

    public IndustryModel createIndustry(IndustryModel industry) {
    	List<IndustryModel> industries = getIndustries();
    	for (IndustryModel auxIndustry : industries) {
			if(auxIndustry.getIndustryName().equalsIgnoreCase(industry.getIndustryName())) {
				throw new EntityNotFoundException("Error: Duplicate industry");
			}
		}
        industry.setCreatedAt(LocalDateTime.now());
        return industryRepository.save(industry);
    }
    
    public IndustryModel updateIndustry(Integer id, IndustryModel modIndustry) {
    	Optional<IndustryModel> optionalIndustry = industryRepository.findById(id);

    	List<IndustryModel> industries = getIndustries();
    	for (IndustryModel auxIndustry : industries) {
			if(auxIndustry.getIndustryName().equalsIgnoreCase(modIndustry.getIndustryName())) {
				throw new EntityNotFoundException("Industry Already exists");
			}
		}
    	
        if (optionalIndustry.isPresent()) {
            IndustryModel industry = optionalIndustry.get();
            industry.setIndustryName(modIndustry.getIndustryName());
            return industryRepository.save(industry);
        } else {
        	throw new EntityNotFoundException("Error: industry don't updated");
        }
    }
    public IndustryModel undeleteIndustryById(Integer id) {
    	Optional<IndustryModel> optionalIndustry = industryRepository.findById(id);

        if (optionalIndustry.isPresent()) {
            IndustryModel industry = optionalIndustry.get();
            industry.setActive(true);
            return industryRepository.save(industry);
        } else {
        	throw new EntityNotFoundException("Error: undeletedIndustryById");
        }
    }

    public IndustryModel deleteIndustry(Integer id) {
        Optional<IndustryModel> optionalIndustry = industryRepository.findById(id);

        if (optionalIndustry.isPresent()) {
            IndustryModel industry = optionalIndustry.get();
            industry.setActive(false);
            return industryRepository.save(industry);
        }else {
        	throw new EntityNotFoundException("Error: industry don't deleted");
        }
    }
}

