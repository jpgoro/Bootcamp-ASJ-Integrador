package com.bootcamp.backendSgc.models;

public class ConditionIvaModel {
	private Integer id;
	private String conditionIva_name;

	public ConditionIvaModel() {
	}

	public ConditionIvaModel(Integer id, String conditionIva_name) {
		this.id = id;
		this.conditionIva_name = conditionIva_name;
	}

	public Integer getId() {
		return id;
	}

	public String getConditionIva_name() {
		return conditionIva_name;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setConditionIva_name(String conditionIva_name) {
		this.conditionIva_name = conditionIva_name;
	}

}
