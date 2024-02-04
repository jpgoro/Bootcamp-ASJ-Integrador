package com.bootcamp.backendSgc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.backendSgc.models.StatusModel;

public interface StatusRepository extends JpaRepository<StatusModel, Integer>{
	List<StatusModel> findByStatusName(String statusName);
}
