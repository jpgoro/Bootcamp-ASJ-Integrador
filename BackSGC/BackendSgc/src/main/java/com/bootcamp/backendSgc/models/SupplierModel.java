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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="suppliers")
public class SupplierModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="supplier_code", nullable = false)
	@NotNull(message = "The supplier_code cannot be null")
	private String code;
	@Column(name="supplier_legalName", nullable = false)
	@NotNull(message = "The supplier_legalName cannot be null")
	private String legalName;
	@Column(name="supplier_web", nullable = false)
	@NotNull(message = "The supplier_web cannot be null")
	private String web;
	@Column(name="supplier_tel", nullable = false)
	@NotNull(message = "The supplier_tel cannot be null")
	private String tel;
	@Column(name="supplier_email", nullable = false)
	@NotNull(message = "The supplier_email cannot be null")
	@Email
	private String email;
	@Column(name="supplier_cuit", nullable = false)
	@NotNull(message = "The supplier_cuit cannot be null")
	private String cuit;
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
	@JoinColumn(name="id_industry",referencedColumnName = "id", nullable = false)
	//@NotNull(message = "The id_industry cannot be null")
	private IndustryModel industry;
	@ManyToOne
	@JoinColumn(name="id_address",referencedColumnName = "id", nullable = false)
	//@NotNull(message = "The id_address cannot be null")
	private AddressModel address;
	@ManyToOne
	@JoinColumn(name="id_conditionIva",referencedColumnName = "id", nullable = false)
	//@NotNull(message = "The id_conditionIva cannot be null")
	private ConditionIvaModel conditionIva;
	@OneToOne
	@JoinColumn(name = "id_contact",referencedColumnName = "id", nullable = false)
	//@NotNull(message = "The id_contact cannot be null")
	private ContactModel contact;
	
	public SupplierModel() {
		super();
	}
	public SupplierModel(Integer id,String code,String legalName,String web,String tel,String email,String cuit,boolean isDeleted, LocalDateTime createdAt,
			LocalDateTime updatedAt, IndustryModel industry, AddressModel address, ConditionIvaModel conditionIva,
			ContactModel contact) {
		this.id = id;
		this.code = code;
		this.legalName = legalName;
		this.web = web;
		this.tel = tel;
		this.email = email;
		this.cuit = cuit;
		this.isDeleted = isDeleted;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.industry = industry;
		this.address = address;
		this.conditionIva = conditionIva;
		this.contact = contact;
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
	public String getWeb() {
		return web;
	}
	public String getTel() {
		return tel;
	}
	public String getEmail() {
		return email;
	}
	public String getCuit() {
		return cuit;
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
	public IndustryModel getIndustry() {
		return industry;
	}
	public AddressModel getAddress() {
		return address;
	}
	public ConditionIvaModel getConditionIva() {
		return conditionIva;
	}
	public ContactModel getContact() {
		return contact;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
	public void setWeb(String web) {
		this.web = web;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
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
	public void setIndustry(IndustryModel industry) {
		this.industry = industry;
	}
	public void setAddress(AddressModel address) {
		this.address = address;
	}
	public void setConditionIva(ConditionIvaModel conditionIva) {
		this.conditionIva = conditionIva;
	}
	public void setContact(ContactModel contact) {
		this.contact = contact;
	}
	@Override
	public String toString() {
		return "SupplierModel [id=" + id + ", code=" + code + ", legalName=" + legalName + ", web=" + web + ", tel="
				+ tel + ", email=" + email + ", cuit=" + cuit + ", isDeleted=" + isDeleted + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", industry=" + industry + ", address=" + address + ", conditionIva="
				+ conditionIva + ", contact=" + contact + "]";
	}
	
	
	
	
}
