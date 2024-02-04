package com.bootcamp.backendSgc.models;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name="contacts")
public class ContactModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="contact_name", nullable = false)
	@NotNull(message = "The contact_name cannot be null")
	@NotBlank
	private String name;
	@Column(name="contact_lastName", nullable = false)
	@NotNull(message = "The contact_lastName cannot be null")
	@NotBlank
	private String lastName;
	@Column(name="contact_email", nullable = false)
	@NotNull(message = "The contact_email cannot be null")
	@NotBlank
	@Email(message = "Email must be valid")
	private String email;
	@NotNull(message = "Phone number is required")
    @NotBlank(message = "Phone number must be complete")
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;
	@Column(name="contact_role", nullable = false)
	@NotNull(message = "The contact_role cannot be null")
	@NotBlank
	private String role;
	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime updatedAt;
	
	@OneToOne
	@JoinColumn(name = "id_supplier",referencedColumnName = "id")
	//@NotNull(message = "The supplier cannot be null")
	private SupplierModel supplier;
	
	public ContactModel() {
	}

	

	public ContactModel(Integer id, String name,String lastName,String email,String phoneNumber,String role, LocalDateTime createdAt,
			LocalDateTime updatedAt, SupplierModel supplier) {
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.role = role;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.supplier = supplier;
	}



	public Integer getId() {
		return id;
	}



	public String getName() {
		return name;
	}



	public String getLastName() {
		return lastName;
	}



	public String getEmail() {
		return email;
	}



	public String getPhoneNumber() {
		return phoneNumber;
	}



	public String getRole() {
		return role;
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



	public void setId(Integer id) {
		this.id = id;
	}



	public void setName(String name) {
		this.name = name;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}



	public void setRole(String role) {
		this.role = role;
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



	@Override
	public String toString() {
		return "ContactModel [id=" + id + ", name=" + name + ", lastName=" + lastName + ", email=" + email
				+ ", phoneNumber=" + phoneNumber + ", role=" + role + ", createdAt=" + createdAt + ", updatedAt="
				+ updatedAt + ", supplier=" + supplier + "]";
	}



	
}
