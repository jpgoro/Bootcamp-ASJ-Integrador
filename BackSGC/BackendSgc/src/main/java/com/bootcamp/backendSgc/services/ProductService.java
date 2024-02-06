package com.bootcamp.backendSgc.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bootcamp.backendSgc.models.CategoryModel;
import com.bootcamp.backendSgc.models.ProductModel;
import com.bootcamp.backendSgc.models.SupplierModel;
import com.bootcamp.backendSgc.repositories.ProductRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	SupplierService supplierService;
	
	@Autowired
	CategoryService categoryService;
	
	public List<ProductModel> getProducts(){
		return productRepository.findAll();
	}
	
	public List<ProductModel> getProductsActive(){
		return productRepository.findByActiveTrue();
	}
	
	public List<ProductModel> getProductsDeleted(){
		return productRepository.findByActiveFalse();
	}
	
	public Optional<ProductModel> getProductById(Integer id){
		Optional<ProductModel> product = productRepository.findById(id);
		if(product.isPresent()) {
			return product;
		} else {
			throw new EntityNotFoundException("Error: product " + id + " was not found");
		}
	}
	
	public List<ProductModel> getProductsByPriceAsc(){
		return productRepository.findAllByOrderByPriceAsc();
	}
	
	public List<ProductModel> getProductsByPriceDesc(){
		return productRepository.findAllByOrderByPriceDesc();
	}
	
	public Optional<List<ProductModel>> getProductBySupplierId(Integer id) {
		SupplierModel supplier = supplierService.getSupplierById(id).orElseThrow(() ->
        new EntityNotFoundException("Supplier " + id + " not found"));
		return Optional.ofNullable(productRepository.findBySupplier(supplier));
	}
	
	public Optional<List<ProductModel>> getProductByCategoryId(Integer id) {
		CategoryModel category = categoryService.getCategoryById(id).orElseThrow(() ->
        new EntityNotFoundException("Category " + id + " not found"));
		return Optional.ofNullable(productRepository.findByCategory(category));
	}
	
	public ProductModel postProduct(ProductModel product) {
		
		List<ProductModel> products = getProducts();
    	
    	for (ProductModel auxProduct : products) {
			if(auxProduct.getSku().equals(product.getSku())) {
				throw new EntityNotFoundException("Sku is used");
			}
		}
		
		if(validateProductInput(product)) {
			if (product.getCreatedAt() == null) {
				product.setCreatedAt(LocalDateTime.now());
	        }
			
			Optional<SupplierModel> supplier = Optional.ofNullable(supplierService.getSupplierById(product.getSupplier().getId()).orElseThrow(() ->
	        new EntityNotFoundException("Supplier  " + product.getSupplier().getId() + " not found")));
			Optional<CategoryModel> category = Optional.ofNullable(categoryService.getCategoryById(product.getCategory().getId()).orElseThrow(() ->
	        new EntityNotFoundException("Supplier " + product.getCategory().getId() + " not found")));
			
			product.setSupplier(supplier.get());
			product.setCategory(category.get());
			return productRepository.save(product);
		} else {
			throw new EntityNotFoundException("The values are incorrect2");
		}		
	}
	
	public ProductModel deleteProduct(Integer id){
		Optional<ProductModel> prodOptional = productRepository.findById(id);
		
		if(prodOptional.isPresent()) {
			ProductModel product = prodOptional.get();
			product.setActive(false);
			product.setUpdatedAt(LocalDateTime.now());
            return productRepository.save(product);
		} else {
			throw new EntityNotFoundException("Product was not deleted");
		}
	}
	
	public ProductModel putProduct(Integer id, ProductModel modProduct){
		Optional<ProductModel> productOptional = productRepository.findById(id);
		
		String sku = productOptional.get().getSku();
		
		if(!sku.equals(modProduct.getSku())) {
			List<ProductModel> products = getProducts();
	    	for (ProductModel auxProduct : products) {
				if(auxProduct.getSku().equals(modProduct.getSku())) {
					throw new EntityNotFoundException("The sku is used");
				}
			}
		}
		
		if(productOptional.isPresent() && validateProductInput(modProduct)) {
			ProductModel product = productOptional.get();
			
			Optional<SupplierModel> supplier = Optional.ofNullable(supplierService.getSupplierById(modProduct.getSupplier().getId()).orElseThrow(() ->
	        new EntityNotFoundException("Supplier  " + modProduct.getSupplier().getId() + " not found")));
			Optional<CategoryModel> category = Optional.ofNullable(categoryService.getCategoryById(modProduct.getCategory().getId()).orElseThrow(() ->
	        new EntityNotFoundException("Supplier  " + modProduct.getCategory().getId() + " not found")));
			
			product.setSku(modProduct.getSku());
			product.setSupplier(supplier.get());
			product.setCategory(category.get());
			product.setDescription(modProduct.getDescription());
			product.setImage(modProduct.getImage());
			product.setPrice(modProduct.getPrice());
			product.setName(modProduct.getName());

			product.setUpdatedAt(LocalDateTime.now());

	        return productRepository.save(product);
		} else {
			throw new EntityNotFoundException("The values are incorrect1");
		}
	}
	
	public ProductModel undeleteProductById(Integer id) {
        Optional<ProductModel> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            ProductModel product = optionalProduct.get();

            product.setActive(true); 
            
            product.setUpdatedAt(LocalDateTime.now());

            return productRepository.save(product);
        } else {
			throw new EntityNotFoundException("Product was not undeleted");
		}
    }
	
	private boolean validateProductInput(ProductModel product) {
		
		String regex1 = "^[0-9]{8}$";
		String regex2 = "^(ftp|http|https):\\/\\/[^ \"]+$";
		String regex3 = "^[0-9 A-Z a-z]{3,50}$";
		
		if(product.getDescription().length() < 15) {
			return false;
		}
		if(!product.getImage().matches(regex2)) {
			return false;
		}
		if(product.getPrice() < 0) {
			return false;
		}
		if(!product.getName().matches(regex3)) {
			return false;
		}
		if(!product.getSku().matches(regex1)) {
			return false;
		}
		
		return true;
	}
}
