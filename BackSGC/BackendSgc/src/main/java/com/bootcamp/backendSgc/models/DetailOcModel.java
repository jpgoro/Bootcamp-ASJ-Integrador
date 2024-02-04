package com.bootcamp.backendSgc.models;

import java.sql.Timestamp;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="details_oc")
public class DetailOcModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	@NotNull(message = "The quantity cannot be null")
	@Positive
	private Integer quantity;
	@Column(nullable = false)
	@NotNull(message = "The price cannot be null")
	@Positive
	private Double price;
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdAt;
	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime updatedAt;
	@ManyToOne
	@JoinColumn(name="id_product",referencedColumnName = "id", nullable = false)
	//@NotNull(message = "The product cannot be null")
	private ProductModel product;
	@ManyToOne
	@JoinColumn(name="id_purchaseOrder",referencedColumnName = "id", nullable = false)
	//@NotNull(message = "The purchaseOrder cannot be null")
	private PurchaseOrderModel purchaseOrder;
	public DetailOcModel() {
	}
	public DetailOcModel(Integer id,Integer quantity,Double price, LocalDateTime createdAt,
			LocalDateTime updatedAt,ProductModel product,PurchaseOrderModel purchaseOrder) {
		this.id = id;
		this.quantity = quantity;
		this.price = price;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.product = product;
		this.purchaseOrder = purchaseOrder;
	}
	public Integer getId() {
		return id;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public Double getPrice() {
		return price;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public ProductModel getProduct() {
		return product;
	}
	public PurchaseOrderModel getPurchaseOrder() {
		return purchaseOrder;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public void setProduct(ProductModel product) {
		this.product = product;
	}
	public void setPurchaseOrder(PurchaseOrderModel purchaseOrder) {
		this.purchaseOrder = purchaseOrder;
	}
	@Override
	public String toString() {
		return "DetailOcModel [id=" + id + ", quantity=" + quantity + ", price=" + price + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", product=" + product + ", purchaseOrder=" + purchaseOrder + "]";
	}
	
	
	
}
