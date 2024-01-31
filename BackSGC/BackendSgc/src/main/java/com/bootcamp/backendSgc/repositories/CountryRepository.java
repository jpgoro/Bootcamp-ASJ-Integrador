package com.bootcamp.backendSgc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.backendSgc.models.CountryModel;

public interface CountryRepository extends JpaRepository<CountryModel, Integer>{

}
