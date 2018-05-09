package com.example.form.Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Store_Product {
	@Id 
	@GeneratedValue(strategy =GenerationType.AUTO)
	private String ID;
	@ManyToOne
    @JoinColumn(name = "ProductID")
private Product product ;
	@ManyToOne
    @JoinColumn(name = "StoreID")
private Store store ;
private String BrandID;
private int Quantity;
	
private double price ;

private int buyed;

private int visited ;

private float offer ; 

public float getOffer() {
	return offer;
}

public void setOffer(float offer) {
	this.offer = offer;
}


public Store_Product(String iD, Product product, Store store, String brandID, int quantity, double price, int buyed,
		int visited) {
	super();
	ID = iD;
	this.product = product;
	this.store = store;
	BrandID = brandID;
	Quantity = quantity;
	this.price = price;
	this.buyed = buyed;
	this.visited = visited;
}
public Store_Product(String iD, Product product) {
	super();
	ID = iD;
	this.product = product;
}
public String getBrandID() {
	return BrandID;
}
public void setBrandID(String brandID) {
	BrandID = brandID;
}
public int getQuantity() {
	return Quantity;
}
public void setQuantity(int quantity) {
	Quantity = quantity;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public String getID() {
	return ID;
}
public void setID(String iD) {
	ID = iD;
}
public int getBuyed() {
	return buyed;
}
public void setBuyed(int buyed) {
	this.buyed = buyed;
}
public int getVisited() {
	return visited;
}
public void setVisited(int visited) {
	this.visited = visited;
}



}
