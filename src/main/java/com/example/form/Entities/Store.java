package com.example.form.Entities;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
@Entity
public class Store {
	@Id
private String Storename;
private String Addres;
private boolean State;
private String Type;




@ManyToOne 
@JoinColumn(name= "user_name")
private User user;



@OneToMany(mappedBy="store")
Set<Store_Product>stores;

public Store(String storename, String addres, String type) {
	super();
	Storename = storename;
	Addres = addres;
	Type = type;
	State=false;
}
public Store() {
	super();
}
public Store(String storename, String addres, boolean state, String type) {
	super();
	Storename = storename;
	Addres = addres;
	State = state;
	Type = type;
}


public String getStorename() {
	return Storename;
}
public void setStorename(String storename) {
	Storename = storename;
}
public String getAddres() {
	return Addres;
}
public void setAddres(String addres) {
	Addres = addres;
}
public boolean getState() {
	return State;
}
public void setState(boolean state) {
	State = state;
}
public String getType() {
	return Type;
}
public void setType(String type) {
	Type = type;
}

}
