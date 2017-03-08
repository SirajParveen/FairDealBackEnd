package com.niit.shoppingcart.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import org.springframework.stereotype.Component;

@Entity
@Component
public class Product {
	
	@Id
	private String ID;
	private String Name, Price, Description, Category_ID, Supplier_ID;
	
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPrice() {
		return Price;
	}
	public void setPrice(String price) {
		Price = price;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getCategory_ID() {
		return Category_ID;
	}
	public void setCategory_ID(String category_ID) {
		Category_ID = category_ID;
	}
	public String getSupplier_ID() {
		return Supplier_ID;
	}
	public void setSupplier_ID(String supplier_ID) {
		Supplier_ID = supplier_ID;
	} 
}
