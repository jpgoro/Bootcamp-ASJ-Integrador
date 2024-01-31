package com.bootcamp.backendSgc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.backendSgc.models.ContactModel;

public interface ContactRepository extends JpaRepository<ContactModel, Integer>{

}
