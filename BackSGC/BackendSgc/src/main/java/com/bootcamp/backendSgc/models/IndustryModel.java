package com.bootcamp.backendSgc.models;

public class IndustryModel {
	
	private Integer id;
	private String  industry_name;
	public IndustryModel() {
	}
	public IndustryModel(Integer id, String industry_name) {
		this.id = id;
		this.industry_name = industry_name;
	}
	public Integer getId() {
		return id;
	}
	public String getIndustry_name() {
		return industry_name;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setIndustry_name(String industry_name) {
		this.industry_name = industry_name;
	}
	
	
}
