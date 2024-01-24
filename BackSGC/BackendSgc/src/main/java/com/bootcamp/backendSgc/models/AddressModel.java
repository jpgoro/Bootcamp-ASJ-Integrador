package com.bootcamp.backendSgc.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="address")
public class AddressModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String street;
	private Integer number;
	private String zipCode;
	private int id_locality;
	public AddressModel() {
	}
	public AddressModel(Integer id, String street, Integer number, String zipCode, int id_locality) {
		this.id = id;
		this.street = street;
		this.number = number;
		this.zipCode = zipCode;
		this.id_locality = id_locality;
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
	public String getZipCode() {
		return zipCode;
	}
	public int getId_locality() {
		return id_locality;
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
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public void setId_locality(int id_locality) {
		this.id_locality = id_locality;
	}
	
}
