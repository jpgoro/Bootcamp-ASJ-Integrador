package com.bootcamp.backendSgc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.backendSgc.models.IndustryModel;

public interface IndustryRepository extends JpaRepository<IndustryModel, Integer>{
	List<IndustryModel> findByActiveTrue();
	List<IndustryModel> findByActiveFalse();
}
