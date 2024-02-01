package com.bootcamp.backendSgc.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="products")
public class ProductModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false, unique = true)
	@NotNull(message = "The product_sku cannot be null")
	@NotBlank
	private String sku;
	@Column(name="product_name", nullable = false)
	@NotNull(message = "The product_name cannot be null")
	@NotBlank
	private String name;
	@Column(name="product_description", nullable = false)
	@NotNull(message = "The product_description cannot be null")
	@NotBlank
	private String description;
	@Column(name="product_price", nullable = false)
	@NotNull(message = "The product_price cannot be null")
	@Positive
	private Double price;
	@Column(name="product_image", nullable = false)
	@NotNull(message = "The product_image cannot be null")
	@NotBlank
	private String image;
	@Column(name = "is_deleted")
	private Boolean isDeleted;
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime updatedAt;
	@ManyToOne
	@JoinColumn(name="id_category",referencedColumnName = "id", nullable = false)
	//@NotNull(message = "The id_category cannot be null")
	private CategoryModel category;
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="id_supplier",referencedColumnName = "id", nullable = false)
	//@NotNull(message = "The id_category cannot be null")
	private SupplierModel supplier;
	
	public ProductModel() {
	}
	public ProductModel(Integer id,String sku,String name,String description,Double price,String image, Boolean isDeleted,
			LocalDateTime createdAt, LocalDateTime updatedAt,CategoryModel category,SupplierModel supplier) {
		this.id = id;
		this.sku = sku;
		this.name = name;
		this.description = description;
		this.price = price;
		this.image = image;
		this.isDeleted = isDeleted;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.category = category;
		this.supplier = supplier;
	}
	public Integer getId() {
		return id;
	}
	public String getSku() {
		return sku;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public Double getPrice() {
		return price;
	}
	public String getImage() {
		return image;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public CategoryModel getCategory() {
		return category;
	}
	public SupplierModel getSupplier() {
		return supplier;
	}
	
	public void setSku(String sku) {
		this.sku = sku;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public void setCategory(CategoryModel category) {
		this.category = category;
	}
	public void setSupplier(SupplierModel supplier) {
		this.supplier = supplier;
	}
	
	@Override
	public String toString() {
		return "ProductModel [id=" + id + ", sku=" + sku + ", name=" + name + ", description=" + description
				+ ", price=" + price + ", image=" + image + ", isDeleted=" + isDeleted + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", category=" + category + ", supplier=" + supplier + "]";
	}
	
	
}
