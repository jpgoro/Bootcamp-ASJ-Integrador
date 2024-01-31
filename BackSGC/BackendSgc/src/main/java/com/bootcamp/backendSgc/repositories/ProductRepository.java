package com.bootcamp.backendSgc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.backendSgc.models.ProductModel;

public interface ProductRepository extends JpaRepository<ProductModel, Integer>{

}
