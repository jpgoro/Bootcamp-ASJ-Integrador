package com.bootcamp.backendSgc.models;

import java.sql.Timestamp;

public class ProductModel {
	
	private Integer id;
	private String product_sku;
	private String product_name;
	private String product_description;
	private Double product_price;
	private String product_image;
	private Timestamp created_at;
	private Timestamp updated_at;
	private int id_category;
	private int id_supplier;
	public ProductModel() {
		
	}
	public ProductModel(Integer id, String product_sku, String product_name, String product_description,
			Double product_price, String product_image, Timestamp created_at, Timestamp updated_at, int id_category,
			int id_supplier) {
		this.id = id;
		this.product_sku = product_sku;
		this.product_name = product_name;
		this.product_description = product_description;
		this.product_price = product_price;
		this.product_image = product_image;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.id_category = id_category;
		this.id_supplier = id_supplier;
	}
	public Integer getId() {
		return id;
	}
	public String getProduct_sku() {
		return product_sku;
	}
	public String getProduct_name() {
		return product_name;
	}
	public String getProduct_description() {
		return product_description;
	}
	public Double getProduct_price() {
		return product_price;
	}
	public String getProduct_image() {
		return product_image;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public Timestamp getUpdated_at() {
		return updated_at;
	}
	public int getId_category() {
		return id_category;
	}
	public int getId_supplier() {
		return id_supplier;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setProduct_sku(String product_sku) {
		this.product_sku = product_sku;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public void setProduct_description(String product_description) {
		this.product_description = product_description;
	}
	public void setProduct_price(Double product_price) {
		this.product_price = product_price;
	}
	public void setProduct_image(String product_image) {
		this.product_image = product_image;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
	public void setId_category(int id_category) {
		this.id_category = id_category;
	}
	public void setId_supplier(int id_supplier) {
		this.id_supplier = id_supplier;
	}
	
	
	
}
