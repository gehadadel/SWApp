package com.example.form.Entities;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Statistics {
	@Id
	private String name;
	private boolean status;
	
	public Statistics() {
		super();
	}
	public Statistics(String name, boolean status) {
		super();
		this.name = name;
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	

}
