package com.bootcamp.backendSgc.models;

public class StatusModel {
	private Integer id;
	private String status_name;
	public StatusModel() {
	}
	public StatusModel(Integer id, String status_name) {
		this.id = id;
		this.status_name = status_name;
	}
	public Integer getId() {
		return id;
	}
	public String getStatus_name() {
		return status_name;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setStatus_name(String status_name) {
		this.status_name = status_name;
	}
	
}
