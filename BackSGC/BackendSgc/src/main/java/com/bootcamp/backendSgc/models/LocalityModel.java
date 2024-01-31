package com.bootcamp.backendSgc.models;

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
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "localities")
public class LocalityModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "locality_name", nullable = false)
	@NotNull(message = "The localityName cannot be null")
	@NotBlank
	private String localityName;

	@Column(name = "created_at")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime updatedAt;
	@ManyToOne
	@JoinColumn(name = "id_province", referencedColumnName = "id", nullable = false)
	// @NotNull(message = "The id_province cannot be null")
	private ProvinceModel province;

	public LocalityModel() {
	}

	public LocalityModel(Integer id, String localityName, LocalDateTime createdAt, LocalDateTime updatedAt,
			ProvinceModel province) {
		this.id = id;
		this.localityName = localityName;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.province = province;
	}

	public Integer getId() {
		return id;
	}

	public String getLocalityName() {
		return localityName;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public ProvinceModel getProvince() {
		return province;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLocalityName(String localityName) {
		this.localityName = localityName;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public void setProvince(ProvinceModel province) {
		this.province = province;
	}

	@Override
	public String toString() {
		return "LocalityModel [id=" + id + ", localityName=" + localityName + ", createdAt=" + createdAt
				+ ", updatedAt=" + updatedAt + ", province=" + province + "]";
	}

}
