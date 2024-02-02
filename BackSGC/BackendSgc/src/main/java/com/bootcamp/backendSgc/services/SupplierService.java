package com.bootcamp.backendSgc.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.backendSgc.models.ConditionIvaModel;
import com.bootcamp.backendSgc.models.IndustryModel;
import com.bootcamp.backendSgc.models.SupplierModel;
import com.bootcamp.backendSgc.repositories.SupplierRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SupplierService {
	@Autowired
	SupplierRepository supplierRepository;
	
	@Autowired
	IndustryService industryService;
	
	@Autowired
	ConditionIvaService conditionIvaService;
	
	public List<SupplierModel> getSuppliers() {
        return supplierRepository.findAll();
    }

    public List<SupplierModel> getSuppliersActive() {
        return supplierRepository.findByActiveTrue();
    }

    public List<SupplierModel> getSuppliersDeleted() {
        return supplierRepository.findByActiveFalse();
    }

    public Optional<SupplierModel> getSupplierById(Integer id) {
        return supplierRepository.findById(id);
    }
    
    public List<SupplierModel> getSuppliersByLegalNameAsc() {
        return supplierRepository.findAllByOrderByLegalNameAsc();
    }
    
    public List<SupplierModel> getSuppliersByLegalNameDesc() {
        return supplierRepository.findAllByOrderByLegalNameDesc();
    }
    
public SupplierModel postSupplier(SupplierModel supplier) {
    	
    	List<SupplierModel> suppliers = getSuppliers();
    	
    	for (SupplierModel auxSupplier : suppliers) {
			if(auxSupplier.getCode().equalsIgnoreCase(supplier.getCode())) {
				throw new EntityNotFoundException("Code in use");
			}
		}
    	
    	if(validateSupplierInput(supplier)) {
            if (supplier.getCreatedAt() == null) {
                supplier.setCreatedAt(LocalDateTime.now());
            }

            IndustryModel industry = industryService.getIndustryById(supplier.getIndustry().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Industry not found"));

            ConditionIvaModel conditionIva = conditionIvaService.getIvaConditionById(supplier.getConditionIva().getId())
                    .orElseThrow(() -> new EntityNotFoundException("conditionIva not found"));
            supplier.setIndustry(industry);
            supplier.setConditionIva(conditionIva);
            

            return supplierRepository.save(supplier);
    	}
    	return null;
    }

    public SupplierModel deleteSupplier(Integer id) {
        Optional<SupplierModel> optionalSupplier = supplierRepository.findById(id);

        if (optionalSupplier.isPresent()) {
            SupplierModel supplier = optionalSupplier.get();
            supplier.setActive(false);
            supplier.setUpdatedAt(LocalDateTime.now());
            return supplierRepository.save(supplier);
        }

        return null;
    }

    public SupplierModel putSupplier(Integer id, SupplierModel supplierMod) {
        Optional<SupplierModel> existingSupplierOptional = supplierRepository.findById(id);

        if (existingSupplierOptional.isPresent() && validateSupplierInput(supplierMod)) {
            SupplierModel supplier = existingSupplierOptional.get();

            IndustryModel industry = industryService.getIndustryById(supplierMod.getIndustry().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Industry not found"));

            ConditionIvaModel conditionIva = conditionIvaService.getIvaConditionById(supplierMod.getConditionIva().getId())
                    .orElseThrow(() -> new EntityNotFoundException("ConditionIva not found"));

            supplier.setCode(supplierMod.getCode());
            supplier.setIndustry(industry);
            supplier.setLegalName(supplierMod.getLegalName());
            supplier.setEmail(supplierMod.getEmail());
            supplier.setWeb(supplierMod.getWeb());
            supplier.setTel(supplierMod.getTel());
            supplier.setImage(supplierMod.getImage());
            supplier.setConditionIva(conditionIva);

            supplier.setUpdatedAt(LocalDateTime.now());

            return supplierRepository.save(supplier);
        }

        return null;
    }

    public SupplierModel undeleteSupplierById(Integer id) {
        Optional<SupplierModel> optionalSupplier = supplierRepository.findById(id);

        if (optionalSupplier.isPresent()) {
            SupplierModel supplier = optionalSupplier.get();
            supplier.setActive(true);
            supplier.setUpdatedAt(LocalDateTime.now());
            return supplierRepository.save(supplier);
        }

        return null;
    }
    
    private boolean validateSupplierInput(SupplierModel supplier) {
    	
    	String regex1 = "^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$";
    	String regex2 = "^[0-9 A-Z a-z]{3,50}$";
    	String regex3 = "^\\+\\d{1,4}-\\d{1,6}-\\d{4,20}$";
    	String regex4 = "^(ftp|http|https):\\/\\/[^ \"]+$";
    	String regex5 = "^\\d{2}-\\d{8}-\\d{1}$";
    	String regex6 = "^(?=.*[0-9])(?=.*[A-Za-z])[0-9A-Za-z]{4,8}$";
    	
    	if(!supplier.getLegalName().matches(regex2)) {
    		return false;
    	}
    	if(!supplier.getCuit().matches(regex5)) {
    		return false;
    	}
    	if(!supplier.getEmail().matches(regex1)) {
    		return false;
    	}
    	if(!supplier.getImage().matches(regex4)) {
    		return false;
    	}
    	if(!supplier.getTel().matches(regex3)) {
    		return false;
    	}
    	if(!supplier.getCode().matches(regex6)) {
    		return false;
    	}
    	if(!supplier.getWeb().matches(regex4)) {
    		return false;
    	}
    	
    	return true;
    }

}
