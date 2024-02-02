package com.bootcamp.backendSgc.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name="conditionsIva")
public class ConditionIvaModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name="tax_condition",nullable = false)
	@NotNull(message = "The tax_condition cannot be null")
	@NotBlank
	private String taxCondition;
	
	public ConditionIvaModel() {}

	public ConditionIvaModel(Integer id,String taxCondition) {
		this.id = id;
		this.taxCondition = taxCondition;
	}

	public Integer getId() {
		return id;
	}

	public String getTaxCondition() {
		return taxCondition;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setTaxCondition(String taxCondition) {
		this.taxCondition = taxCondition;
	}

	@Override
	public String toString() {
		return "ConditionIvaModel [id=" + id + ", taxCondition=" + taxCondition + "]";
	}
	
	
	
}
