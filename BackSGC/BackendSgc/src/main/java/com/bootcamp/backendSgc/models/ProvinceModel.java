package com.bootcamp.backendSgc.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name="provinces")
public class ProvinceModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@NotNull(message = "The name cannot be null")
	private String province_name;
	@NotNull(message = "The country_id cannot be null")
	private int country_id;
	public ProvinceModel() {
	}
	public ProvinceModel(Integer id,String province_name,int country_id) {
		super();
		this.id = id;
		this.province_name = province_name;
		this.country_id = country_id;
	}
	public Integer getId() {
		return id;
	}
	public String getProvince_name() {
		return province_name;
	}
	public int getCountry_id() {
		return country_id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}
	public void setCountry_id(int country_id) {
		this.country_id = country_id;
	}
	
	
	
	
	
	
	
	
}
