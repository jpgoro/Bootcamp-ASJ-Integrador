package com.bootcamp.backendSgc.models;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="status")
public class StatusModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="status_name", nullable = false)
	@NotNull(message = "The status_name cannot be null")
	@NotBlank
	private String status_name;
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime updatedAt;
//	@OneToMany(mappedBy = "status")
//	@JsonManagedReference
//	private List<PurchaseOrderModel>purchaseOrd;
	public StatusModel() {
	}
	public StatusModel(Integer id, String status_name,
			LocalDateTime createdAt, LocalDateTime updatedAt, List<PurchaseOrderModel> purchaseOrd) {
		this.id = id;
		this.status_name = status_name;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		//this.purchaseOrd = purchaseOrd;
	}
	public Integer getId() {
		return id;
	}
	public String getStatus_name() {
		return status_name;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
//	public List<PurchaseOrderModel> getPurchaseOrd() {
//		return purchaseOrd;
//	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
//	public void setPurchaseOrd(List<PurchaseOrderModel> purchaseOrd) {
//		this.purchaseOrd = purchaseOrd;
//	}
	@Override
	public String toString() {
		return "StatusModel [id=" + id + ", status_name=" + status_name + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + "]";
	}
	
	
}
