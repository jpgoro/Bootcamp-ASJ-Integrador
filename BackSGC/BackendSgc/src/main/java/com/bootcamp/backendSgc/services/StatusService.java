package com.bootcamp.backendSgc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.backendSgc.models.StatusModel;
import com.bootcamp.backendSgc.repositories.StatusRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class StatusService {
	@Autowired
	StatusRepository statusRepository;
	
	public List<StatusModel> getStatuses() {
        return statusRepository.findAll();
    }

    public Optional<StatusModel> getStatusById(Integer id) {
    	Optional<StatusModel> optionalStatus = statusRepository.findById(id);
    	if(optionalStatus.isPresent()) {
    		return optionalStatus;
    	} else {
        	throw new EntityNotFoundException("Error: status" + id + " was not found");
        }
    }
    public List<StatusModel> findByStatusName(String string) {
    	return statusRepository.findByStatusName(string);
    }

    public StatusModel createStatus(StatusModel status) {
        return statusRepository.save(status);
    }

    public StatusModel updateStatus(Integer id, StatusModel modStatus) {
        Optional<StatusModel> optionalStatus = statusRepository.findById(id);

        if (optionalStatus.isPresent()) {
            StatusModel status = optionalStatus.get();
            status.setStatusName(modStatus.getStatusName());
            return statusRepository.save(status);
        }else {
        	throw new EntityNotFoundException("Error: status don't updated");
        }
    }
    
    public void deleteStatus(Integer id) {
        statusRepository.deleteById(id);
    }
}
