package com.bootcamp.backendSgc.models;

public class ContactModel {
	
	private Integer id;
	private String contact_name;
	private String contact_lastName;
	private String contact_email;
	private String contact_role;
	private int id_supplier;
	public ContactModel() {
	}
	public ContactModel(Integer id, String contact_name, String contact_lastName, String contact_email,
			String contact_role, int id_supplier) {
		this.id = id;
		this.contact_name = contact_name;
		this.contact_lastName = contact_lastName;
		this.contact_email = contact_email;
		this.contact_role = contact_role;
		this.id_supplier = id_supplier;
	}
	public Integer getId() {
		return id;
	}
	public String getContact_name() {
		return contact_name;
	}
	public String getContact_lastName() {
		return contact_lastName;
	}
	public String getContact_email() {
		return contact_email;
	}
	public String getContact_role() {
		return contact_role;
	}
	public int getId_supplier() {
		return id_supplier;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setContact_name(String contact_name) {
		this.contact_name = contact_name;
	}
	public void setContact_lastName(String contact_lastName) {
		this.contact_lastName = contact_lastName;
	}
	public void setContact_email(String contact_email) {
		this.contact_email = contact_email;
	}
	public void setContact_role(String contact_role) {
		this.contact_role = contact_role;
	}
	public void setId_supplier(int id_supplier) {
		this.id_supplier = id_supplier;
	}
	
	
	

}
