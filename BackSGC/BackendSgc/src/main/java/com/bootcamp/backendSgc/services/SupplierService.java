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
    	Optional<SupplierModel> optionalSupplier = supplierRepository.findById(id);
    	if(optionalSupplier.isPresent()) {
    		return optionalSupplier;
    	} else {
    		throw new EntityNotFoundException("Error: supplier " + id + " was not found");
    	}
    }
    
    public List<SupplierModel> getSuppliersByBusinessNameAsc() {
        return supplierRepository.findAllByOrderByLegalNameAsc();
    }
    
    public List<SupplierModel> getSuppliersByBusinessNameDesc() {
        return supplierRepository.findAllByOrderByLegalNameDesc();
    }

    public SupplierModel postSupplier(SupplierModel supplier) {
    	
    	List<SupplierModel> suppliers = getSuppliers();
    	
    	for (SupplierModel auxSupplier : suppliers) {
			if(auxSupplier.getCode().equals(supplier.getCode())) {
				throw new EntityNotFoundException("code is used");
			}
		}
    	
    	if(validateSupplierInput(supplier)) {
            if (supplier.getCreatedAt() == null) {
                supplier.setCreatedAt(LocalDateTime.now());
            }

            IndustryModel industry = industryService.getIndustryById(supplier.getIndustry().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Industry not found"));

            ConditionIvaModel ivaCondition = conditionIvaService.getIvaConditionById(supplier.getConditionIva().getId())
                    .orElseThrow(() -> new EntityNotFoundException("IvaCondition not found"));

            supplier.setIndustry(industry);
            supplier.setConditionIva(ivaCondition);
            

            return supplierRepository.save(supplier);
    	} else {
    		throw new EntityNotFoundException("The values are wrong");
    	}
    }

    public SupplierModel deleteSupplier(Integer id) {
        Optional<SupplierModel> optionalSupplier = supplierRepository.findById(id);

        if (optionalSupplier.isPresent()) {
            SupplierModel supplier = optionalSupplier.get();
            supplier.setActive(false);
            supplier.setUpdatedAt(LocalDateTime.now());
            return supplierRepository.save(supplier);
        } else {
        	throw new EntityNotFoundException("Supplier can't be deleted");
        }

    }

    public SupplierModel putSupplier(Integer id, SupplierModel updatedSupplier) {
        Optional<SupplierModel> existingSupplierOptional = supplierRepository.findById(id);
        
        String initSupplierCode = existingSupplierOptional.get().getCode();
        
        if(!initSupplierCode.equals(updatedSupplier.getCode())) {
        	List<SupplierModel> suppliers = getSuppliers();
        	for (SupplierModel supplier2 : suppliers) {
    			if(supplier2.getCode().equals(updatedSupplier.getCode())) {
    				throw new EntityNotFoundException("The supplier code is used");
    			}
    		}
        }

        if (existingSupplierOptional.isPresent() && validateSupplierInput(updatedSupplier)) {
            SupplierModel existingSupplier = existingSupplierOptional.get();

            IndustryModel industry = industryService.getIndustryById(updatedSupplier.getIndustry().getId())
                    .orElseThrow(() -> new EntityNotFoundException("Industry not found")); // Handle properly

            ConditionIvaModel conditionIva = conditionIvaService.getIvaConditionById(updatedSupplier.getConditionIva().getId())
                    .orElseThrow(() -> new EntityNotFoundException("IvaCondition not found")); // Handle properly

            existingSupplier.setCode(updatedSupplier.getCode());
            existingSupplier.setIndustry(industry);
            existingSupplier.setCuit(updatedSupplier.getCuit());
            existingSupplier.setLegalName(updatedSupplier.getLegalName());
            existingSupplier.setEmail(updatedSupplier.getEmail());
            existingSupplier.setWeb(updatedSupplier.getWeb());
            existingSupplier.setTel(updatedSupplier.getTel());
            existingSupplier.setImage(updatedSupplier.getImage());
            existingSupplier.setConditionIva(conditionIva);

            existingSupplier.setUpdatedAt(LocalDateTime.now());

            return supplierRepository.save(existingSupplier);
        } else {
        	throw new EntityNotFoundException("The values are wrong");
        }
    }

    public SupplierModel undeleteSupplierById(Integer id) {
        Optional<SupplierModel> optionalSupplier = supplierRepository.findById(id);

        if (optionalSupplier.isPresent()) {
            SupplierModel supplier = optionalSupplier.get();
            supplier.setActive(true);
            supplier.setUpdatedAt(LocalDateTime.now());
            return supplierRepository.save(supplier);
        } else {
        	throw new EntityNotFoundException("Supplier can't be undeleted");
        }
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
