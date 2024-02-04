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
@Table(name="purchases_orders")
public class PurchaseOrderModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="order_number",nullable = false, unique = true)
	@NotNull(message = "The order_number be null")
	private String number;
	@Column(name = "issue_date", nullable = false, unique = true)
	@NotNull
	private LocalDateTime issueDate;
	@Column(name = "delivery_date", nullable = false)
	@NotNull
	private LocalDateTime deliveryDate;
	@Column(name="is_active",nullable = false)
	@NotNull(message = "Active cannot be null")
	private Boolean active;
	@Column(name = "info_reception", nullable = false)
	@NotNull(message = "Reception information is required")
    @NotBlank(message = "Reception information must be complete")
    private String reception;
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime updatedAt;
	@ManyToOne
	@JoinColumn(name="id_supplier",referencedColumnName = "id", nullable = false)
	//@NotNull(message = "The id_supplier cannot be null")
	private SupplierModel supplier;
	@ManyToOne
	@JoinColumn(name="id_status",referencedColumnName = "id", nullable = false)
	//@NotNull(message = "The id_supplier cannot be null")
	private StatusModel status;
	
	public PurchaseOrderModel() {
	}

	public PurchaseOrderModel(Integer id,String number,LocalDateTime issueDate, LocalDateTime deliveryDate,Boolean active,String reception,
			LocalDateTime createdAt, LocalDateTime updatedAt, SupplierModel supplier, StatusModel status) {
		this.id = id;
		this.number = number;
		this.issueDate = issueDate;
		this.deliveryDate = deliveryDate;
		this.active = active;
		this.reception = reception;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.supplier = supplier;
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public String getNumber() {
		return number;
	}

	public LocalDateTime getIssueDate() {
		return issueDate;
	}

	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}

	public Boolean isActive() {
		return active;
	}

	public String getReception() {
		return reception;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public SupplierModel getSupplier() {
		return supplier;
	}

	public StatusModel getStatus() {
		return status;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public void setIssueDate(LocalDateTime issueDate) {
		this.issueDate = issueDate;
	}

	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public void setReception(String reception) {
		this.reception = reception;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public void setSupplier(SupplierModel supplier) {
		this.supplier = supplier;
	}

	public void setStatus(StatusModel status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "PurchaseOrderModel [id=" + id + ", number=" + number + ", issueDate=" + issueDate + ", deliveryDate="
				+ deliveryDate + ", active=" + active + ", reception=" + reception + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", supplier=" + supplier + ", status=" + status + "]";
	}
	
}
