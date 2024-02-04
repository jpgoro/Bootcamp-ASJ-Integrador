package com.bootcamp.backendSgc.models;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "suppliers")
public class SupplierModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "supplier_code", nullable = false, unique = true)
	@NotNull(message = "The supplier_code cannot be null")
	private String code;
	@Column(name = "supplier_legalName", nullable = false)
	@NotNull(message = "The supplier_legalName cannot be null")
	private String legalName;
	@Column(name = "supplier_email", nullable = false)
	@NotNull(message = "The supplier_email cannot be null")
	@Email
	private String email;
	@Column(name = "supplier_web", nullable = false)
	@NotNull(message = "The supplier_web cannot be null")
	private String web;
	@Column(name = "phone_number", nullable = false)
	@NotNull(message = "The phone_number cannot be null")
	private String tel;
	
	@NotNull(message = "Image is required")
	@NotBlank(message = "Image must be complete")
	@Lob
	@Column(name = "image_supp", nullable = false, columnDefinition = "TEXT")
	private String image;
	@Column(name = "is_active", nullable = false)
	@NotNull(message = "The active cannot be null")
	private Boolean active;
	@Column(name = "supplier_cuit", nullable = false)
	@NotNull(message = "The supplier_cuit cannot be null")
	private String cuit;
	
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime updatedAt;
	@ManyToOne
	@JoinColumn(name = "id_industry", referencedColumnName = "id")
	private IndustryModel industry;
	@ManyToOne
	@JoinColumn(name = "id_conditionIva", referencedColumnName = "id")
	private ConditionIvaModel conditionIva;
	public SupplierModel() {
		super();
	}
	public SupplierModel(Integer id,String code,String legalName,String email,String web,String tel,String image,Boolean active,String cuit, LocalDateTime createdAt,
			LocalDateTime updatedAt, IndustryModel industry, ConditionIvaModel conditionIva) {
		this.id = id;
		this.code = code;
		this.legalName = legalName;
		this.email = email;
		this.web = web;
		this.tel = tel;
		this.image = image;
		this.active = active;
		this.cuit = cuit;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.industry = industry;
		this.conditionIva = conditionIva;
	}
	public Integer getId() {
		return id;
	}
	public String getCode() {
		return code;
	}
	public String getLegalName() {
		return legalName;
	}
	public String getEmail() {
		return email;
	}
	public String getWeb() {
		return web;
	}
	public String getTel() {
		return tel;
	}
	public String getImage() {
		return image;
	}
	public Boolean getActive() {
		return active;
	}
	public String getCuit() {
		return cuit;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public IndustryModel getIndustry() {
		return industry;
	}
	public ConditionIvaModel getConditionIva() {
		return conditionIva;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public void setIndustry(IndustryModel industry) {
		this.industry = industry;
	}
	public void setConditionIva(ConditionIvaModel conditionIva) {
		this.conditionIva = conditionIva;
	}

	

}
