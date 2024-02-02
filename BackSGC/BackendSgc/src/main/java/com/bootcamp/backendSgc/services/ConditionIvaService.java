package com.bootcamp.backendSgc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.backendSgc.models.ConditionIvaModel;
import com.bootcamp.backendSgc.repositories.ConditionIvaRepository;

@Service
public class ConditionIvaService {
	
	@Autowired
	ConditionIvaRepository conditionIvaRepository;
	
	public List<ConditionIvaModel> getIvaConditions() {
        return conditionIvaRepository.findAll();
    }

    public Optional<ConditionIvaModel> getIvaConditionById(Integer id) {
        return conditionIvaRepository.findById(id);
    }

    public ConditionIvaModel postIvaCondition(ConditionIvaModel condition) {
        return conditionIvaRepository.save(condition);
    }

    public void deleteIvaCondition(Integer id) {
        conditionIvaRepository.deleteById(id);
    }
}
