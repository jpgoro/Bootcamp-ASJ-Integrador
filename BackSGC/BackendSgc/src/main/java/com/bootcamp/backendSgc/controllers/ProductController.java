package com.bootcamp.backendSgc.controllers;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bootcamp.backendSgc.extra.ErrorHandler;
import com.bootcamp.backendSgc.models.ProductModel;
import com.bootcamp.backendSgc.services.ProductService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	ProductService productService;
	
	@GetMapping()
    public ResponseEntity<?> getProducts() {
        try {
            List<ProductModel> products = productService.getProducts();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching products");
        }
    }

    @GetMapping("/active")
    public ResponseEntity<?> getProductsActive() {
        try {
            List<ProductModel> products = productService.getProductsActive();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching active products");
        }
    }

    @GetMapping("/deleted")
    public ResponseEntity<?> getProductsDeleted() {
        try {
            List<ProductModel> products = productService.getProductsDeleted();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching deleted products");
        }
    }
    
    @GetMapping("/priceProdAsc")
    public ResponseEntity<?> getProductsByPriceAsc() {
        try {
            List<ProductModel> products = productService.getProductsByPriceAsc();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching deleted products");
        }
    }
    
    @GetMapping("/priceProdDesc")
    public ResponseEntity<?> getProductsByPriceDesc() {
        try {
            List<ProductModel> products = productService.getProductsByPriceDesc();
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching deleted products");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Integer id) {
        try {
            Optional<ProductModel> product = productService.getProductById(id);
            return ResponseEntity.ok(product);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching product by id");
        }
    }

    @GetMapping("/supplier/{supplierId}")
    public ResponseEntity<?> getProductBySupplierId(@PathVariable Integer supplierId) {
        try {
            Optional<List<ProductModel>> products = productService.getProductBySupplierId(supplierId);
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching products by supplier id");
        }
    }
    
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<?> getProductByCategoryId(@PathVariable Integer categoryId) {
        try {
            Optional<List<ProductModel>> products = productService.getProductByCategoryId(categoryId);
            return ResponseEntity.ok(products);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching products by supplier id");
        }
    }

    @PostMapping()
    public ResponseEntity<?> postProduct(@Valid @RequestBody ProductModel product, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> errors = ErrorHandler.handleErrors(bindingResult);
                return ResponseEntity.badRequest().body(errors);
            }

         
            return ResponseEntity.ok(productService.postProduct(product));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while creating product: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProductById(@PathVariable Integer id) {
        try {
            ProductModel deletedProduct = productService.deleteProduct(id);
            return ResponseEntity.ok(deletedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while deleting product");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putProduct(@Valid @RequestBody ProductModel product, @PathVariable Integer id, BindingResult bindingResult) {
        try {
            if (bindingResult.hasErrors()) {
                Map<String, String> errors = ErrorHandler.handleErrors(bindingResult);
                return ResponseEntity.badRequest().body(errors);
            }

            return ResponseEntity.ok(productService.putProduct(id, product));
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while updating product: " + e.getMessage());
        }
    }

    @PatchMapping("/{id}/undelete")
    public ResponseEntity<?> undeleteProductById(@PathVariable Integer id) {
        try {
            ProductModel undeletedProduct = productService.undeleteProductById(id);
            return ResponseEntity.ok(undeletedProduct);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while undeleting product");
        }
    }
}
