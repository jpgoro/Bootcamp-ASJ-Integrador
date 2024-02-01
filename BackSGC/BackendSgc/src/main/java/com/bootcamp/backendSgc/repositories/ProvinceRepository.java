package com.bootcamp.backendSgc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.backendSgc.models.CountryModel;
import com.bootcamp.backendSgc.models.ProvinceModel;

public interface ProvinceRepository extends JpaRepository<ProvinceModel, Integer>{
	List<ProvinceModel> findByCountry(CountryModel country);
}
