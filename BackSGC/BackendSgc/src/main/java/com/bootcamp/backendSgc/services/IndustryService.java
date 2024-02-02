package com.bootcamp.backendSgc.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.backendSgc.models.IndustryModel;
import com.bootcamp.backendSgc.repositories.IndustryRepository;

@Service
public class IndustryService {
	
	@Autowired
	IndustryRepository industryRepository;
	
	public List<IndustryModel> getIndustries() {
        return industryRepository.findAll();
    }

    public Optional<IndustryModel> getIndustryById(Integer id) {
        return industryRepository.findById(id);
    }

    public IndustryModel createIndustry(IndustryModel industry) {
        industry.setCreatedAt(LocalDateTime.now());
        return industryRepository.save(industry);
    }
    
    public IndustryModel updateIndustry(Integer id, IndustryModel modIndustry) {
    	Optional<IndustryModel> optionalIndustry = industryRepository.findById(id);

        if (optionalIndustry.isPresent()) {
            IndustryModel industry = optionalIndustry.get();
            industry.setIndustryName(modIndustry.getIndustryName());
            return industryRepository.save(industry);
        }

        return null;
    }

    public IndustryModel deleteIndustry(Integer id) {
        Optional<IndustryModel> optionalIndustry = industryRepository.findById(id);

        if (optionalIndustry.isPresent()) {
            IndustryModel industry = optionalIndustry.get();
            industry.setActive(false);
            return industryRepository.save(industry);
        }

        return null;
    }
}

