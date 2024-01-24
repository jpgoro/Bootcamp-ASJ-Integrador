package com.bootcamp.backendSgc.models;

import java.sql.Timestamp;

public class DetailOcModel {
	private Integer id;
	private Integer quantity;
	private Timestamp created_at;
	private Timestamp updated_at;
	private int id_product;
	private int id_purchaseOrder;
	public DetailOcModel() {
	}
	public DetailOcModel(Integer id, Integer quantity, Timestamp created_at, Timestamp updated_at, int id_product,
			int id_purchaseOrder) {
		this.id = id;
		this.quantity = quantity;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.id_product = id_product;
		this.id_purchaseOrder = id_purchaseOrder;
	}
	public Integer getId() {
		return id;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public Timestamp getUpdated_at() {
		return updated_at;
	}
	public int getId_product() {
		return id_product;
	}
	public int getId_purchaseOrder() {
		return id_purchaseOrder;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
	public void setId_product(int id_product) {
		this.id_product = id_product;
	}
	public void setId_purchaseOrder(int id_purchaseOrder) {
		this.id_purchaseOrder = id_purchaseOrder;
	}
	
	
}
