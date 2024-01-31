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
	@Column(name="order_number",nullable = false)
	@NotNull(message = "The order_number be null")
	private Integer number;
	@Column(name = "issue_date", nullable = false, unique = true)
	@NotNull
	private LocalDateTime issueDate;
	@Column(name = "delivery_date", nullable = false)
	@NotNull
	private LocalDateTime deliveryDate;
	@Column(nullable = false)
	@NotNull(message = "The total be null")
	@Positive
	private Double total;
	@Column(name="is_deleted",nullable = false)
	@NotNull(message = "The deleted cannot be null")
	private boolean isDeleted;
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime updatedAt;
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="id_supplier",referencedColumnName = "id", nullable = false)
	//@NotNull(message = "The id_supplier cannot be null")
	private SupplierModel supplier;
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name="id_status",referencedColumnName = "id", nullable = false)
	//@NotNull(message = "The id_supplier cannot be null")
	private StatusModel status;
	@OneToMany(mappedBy = "purchaseOrder")
	@JsonManagedReference
	//@NotNull(message = "The detailOc cannot be null")
	private List<DetailOcModel> detailOc;
	public PurchaseOrderModel() {
	}
	public PurchaseOrderModel(Integer id, Integer number,LocalDateTime issueDate,LocalDateTime deliveryDate,Double total,boolean isDeleted, LocalDateTime createdAt,
			LocalDateTime updatedAt,SupplierModel supplier,StatusModel status,List<DetailOcModel> detailOc) {
		this.id = id;
		this.number = number;
		this.issueDate = issueDate;
		this.deliveryDate = deliveryDate;
		this.total = total;
		this.isDeleted = isDeleted;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.supplier = supplier;
		this.status = status;
		this.detailOc = detailOc;
	}
	public Integer getId() {
		return id;
	}
	public Integer getNumber() {
		return number;
	}
	public LocalDateTime getIssueDate() {
		return issueDate;
	}
	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}
	public Double getTotal() {
		return total;
	}
	public boolean isDeleted() {
		return isDeleted;
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
	public List<DetailOcModel> getDetailOc() {
		return detailOc;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public void setIssueDate(LocalDateTime issueDate) {
		this.issueDate = issueDate;
	}
	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
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
	public void setDetailOc(List<DetailOcModel> detailOc) {
		this.detailOc = detailOc;
	}
	@Override
	public String toString() {
		return "PurchaseOrderModel [id=" + id + ", number=" + number + ", issueDate=" + issueDate + ", deliveryDate="
				+ deliveryDate + ", total=" + total + ", isDeleted=" + isDeleted + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", supplier=" + supplier + ", status=" + status + ", detailOc="
				+ detailOc + "]";
	}
	
	
}
