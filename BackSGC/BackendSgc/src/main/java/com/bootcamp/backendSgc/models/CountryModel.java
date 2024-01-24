package com.bootcamp.backendSgc.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="countries")
public class CountryModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull(message = "The name cannot be null")
	private String country_name;
	
	public CountryModel() {
	}
	public CountryModel(Integer id, String country_name) {
		this.id = id;
		this.country_name = country_name;
	}
	public Integer getId() {
		return id;
	}
	public String getCountry_name() {
		return country_name;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setCountry_name(String country_name) {
		this.country_name = country_name;
	}
	
	
	

}
