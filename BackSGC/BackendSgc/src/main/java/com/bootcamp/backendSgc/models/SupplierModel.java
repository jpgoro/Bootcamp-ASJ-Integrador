package com.bootcamp.backendSgc.models;

import java.sql.Timestamp;

public class SupplierModel {
	
	private Integer id;
	private String supplier_code;
	private String supplier_legalName;
	private String supplier_web;
	private String supplier_tel;
	private String supplier_email;
	private String supplier_cuit;
	private Timestamp created_at;
	private Timestamp updated_at;
	private int id_industry;
	private int id_address;
	private int id_conditionIva;
	public SupplierModel() {
	}
	public SupplierModel(Integer id, String supplier_code, String supplier_legalName, String supplier_web,
			String supplier_tel, String supplier_email, String supplier_cuit, Timestamp created_at,
			Timestamp updated_at, int id_industry, int id_address, int id_conditionIva) {
		this.id = id;
		this.supplier_code = supplier_code;
		this.supplier_legalName = supplier_legalName;
		this.supplier_web = supplier_web;
		this.supplier_tel = supplier_tel;
		this.supplier_email = supplier_email;
		this.supplier_cuit = supplier_cuit;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.id_industry = id_industry;
		this.id_address = id_address;
		this.id_conditionIva = id_conditionIva;
	}
	public Integer getId() {
		return id;
	}
	public String getSupplier_code() {
		return supplier_code;
	}
	public String getSupplier_legalName() {
		return supplier_legalName;
	}
	public String getSupplier_web() {
		return supplier_web;
	}
	public String getSupplier_tel() {
		return supplier_tel;
	}
	public String getSupplier_email() {
		return supplier_email;
	}
	public String getSupplier_cuit() {
		return supplier_cuit;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public Timestamp getUpdated_at() {
		return updated_at;
	}
	public int getId_industry() {
		return id_industry;
	}
	public int getId_address() {
		return id_address;
	}
	public int getId_conditionIva() {
		return id_conditionIva;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setSupplier_code(String supplier_code) {
		this.supplier_code = supplier_code;
	}
	public void setSupplier_legalName(String supplier_legalName) {
		this.supplier_legalName = supplier_legalName;
	}
	public void setSupplier_web(String supplier_web) {
		this.supplier_web = supplier_web;
	}
	public void setSupplier_tel(String supplier_tel) {
		this.supplier_tel = supplier_tel;
	}
	public void setSupplier_email(String supplier_email) {
		this.supplier_email = supplier_email;
	}
	public void setSupplier_cuit(String supplier_cuit) {
		this.supplier_cuit = supplier_cuit;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
	public void setId_industry(int id_industry) {
		this.id_industry = id_industry;
	}
	public void setId_address(int id_address) {
		this.id_address = id_address;
	}
	public void setId_conditionIva(int id_conditionIva) {
		this.id_conditionIva = id_conditionIva;
	}
	
	
	
}
