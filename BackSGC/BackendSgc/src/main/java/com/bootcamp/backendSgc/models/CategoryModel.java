package com.bootcamp.backendSgc.models;

public class CategoryModel {
	private Integer id;
	private String category_name;
	public CategoryModel() {
	}
	public CategoryModel(Integer id, String category_name) {
		this.id = id;
		this.category_name = category_name;
	}
	public Integer getId() {
		return id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	
}
