package com.bootcamp.backendSgc.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bootcamp.backendSgc.models.CategoryModel;
import com.bootcamp.backendSgc.models.ProductModel;
import com.bootcamp.backendSgc.models.SupplierModel;

public interface ProductRepository extends JpaRepository<ProductModel, Integer>{
	List<ProductModel> findByActiveFalse();
	List<ProductModel> findByActiveTrue();
	List<ProductModel> findBySupplier(SupplierModel supplier);
	List<ProductModel> findByCategory(CategoryModel category);
	List<ProductModel> findAllByOrderByPriceDesc();
	List<ProductModel> findAllByOrderByPriceAsc();
}
