package com.bootcamp.backendSgc.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="localities")
public class LocalityModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull(message = "The locality_name cannot be null")
	private String locality_name;
	private int id_province;
	public LocalityModel() {
	}
	public LocalityModel(Integer id, String locality_name, int id_province) {
		this.id = id;
		this.locality_name = locality_name;
		this.id_province = id_province;
	}
	public Integer getId() {
		return id;
	}
	public String getLocality_name() {
		return locality_name;
	}
	public int getId_province() {
		return id_province;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setLocality_name(String locality_name) {
		this.locality_name = locality_name;
	}
	public void setId_province(int id_province) {
		this.id_province = id_province;
	}
	
	
	
}
