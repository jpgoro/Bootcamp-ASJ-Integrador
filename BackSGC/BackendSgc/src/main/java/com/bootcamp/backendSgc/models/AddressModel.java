package com.bootcamp.backendSgc.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
@Table(name="address")
public class AddressModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable = false)
	@NotNull(message = "The street cannot be null")
	@NotBlank
	private String street;
	@Column(nullable = false)
	@NotNull(message = "The number cannot be null")
	@Positive
	private Integer number;
	@Column(name="postal_code", nullable = false)
	@NotNull(message = "The postalCode cannot be null")
	@NotBlank
	private String postalCode;
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdAt;
	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime updatedAt;
	@ManyToOne
	@JoinColumn(name = "id_ocality",referencedColumnName = "id", nullable = false)
	//@NotNull(message = "The idLocality cannot be null")
	private LocalityModel idLocality;
	
	public AddressModel() {
	}
	
	public AddressModel(Integer id, String street,Integer number,String postalCode, LocalDateTime createdAt,
			LocalDateTime updatedAt, LocalityModel idLocality) {
		this.id = id;
		this.street = street;
		this.number = number;
		this.postalCode = postalCode;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.idLocality = idLocality;
	}

	public Integer getId() {
		return id;
	}
	public String getStreet() {
		return street;
	}
	public Integer getNumber() {
		return number;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
	public LocalityModel getIdLocality() {
		return idLocality;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
	public void setIdLocality(LocalityModel idLocality) {
		this.idLocality = idLocality;
	}
	
	@Override
	public String toString() {
		return "AddressModel [id=" + id + ", street=" + street + ", number=" + number + ", postalCode=" + postalCode
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", idLocality=" + idLocality + "]";
	}
	
	
	
}
