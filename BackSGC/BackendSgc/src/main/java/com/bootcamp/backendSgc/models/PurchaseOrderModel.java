package com.bootcamp.backendSgc.models;

import java.sql.Timestamp;

public class PurchaseOrderModel {
	private Integer id;
	private Integer order_number;
	private Timestamp issuance_date;
	private Timestamp delivery_date;
	private Double total;
	private boolean active;
	private Timestamp created_at;
	private Timestamp updated_at;
	private int id_supplier;
	private int id_status;
	public PurchaseOrderModel() {
	}
	public PurchaseOrderModel(Integer id, Integer order_number, Timestamp issuance_date, Timestamp delivery_date,
			Double total, boolean active, Timestamp created_at, Timestamp updated_at, int id_supplier, int id_status) {
		this.id = id;
		this.order_number = order_number;
		this.issuance_date = issuance_date;
		this.delivery_date = delivery_date;
		this.total = total;
		this.active = active;
		this.created_at = created_at;
		this.updated_at = updated_at;
		this.id_supplier = id_supplier;
		this.id_status = id_status;
	}
	public Integer getId() {
		return id;
	}
	public Integer getOrder_number() {
		return order_number;
	}
	public Timestamp getIssuance_date() {
		return issuance_date;
	}
	public Timestamp getDelivery_date() {
		return delivery_date;
	}
	public Double getTotal() {
		return total;
	}
	public boolean isActive() {
		return active;
	}
	public Timestamp getCreated_at() {
		return created_at;
	}
	public Timestamp getUpdated_at() {
		return updated_at;
	}
	public int getId_supplier() {
		return id_supplier;
	}
	public int getId_status() {
		return id_status;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setOrder_number(Integer order_number) {
		this.order_number = order_number;
	}
	public void setIssuance_date(Timestamp issuance_date) {
		this.issuance_date = issuance_date;
	}
	public void setDelivery_date(Timestamp delivery_date) {
		this.delivery_date = delivery_date;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}
	public void setUpdated_at(Timestamp updated_at) {
		this.updated_at = updated_at;
	}
	public void setId_supplier(int id_supplier) {
		this.id_supplier = id_supplier;
	}
	public void setId_status(int id_status) {
		this.id_status = id_status;
	}
	
}
