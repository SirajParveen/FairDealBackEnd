package com.niit.fairdeal.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "Category") // if the class name and table name is different
@Component // if want to create instance of Class Category - category
public class Category {
	
	// add simple properties - same as Category table
	// generate getter/setter methods

	@Id
	private String ID;
	
	@Column(name = "name")  // if the field name and property name is different
	@NotEmpty(message="Category name should not be empty")
	private String Name;
	
	@NotEmpty
	private String Description;
	
	@OneToMany(mappedBy="category",fetch = FetchType.EAGER)
	private Set<Product> products;
	
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

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Set<Product> getProducts() {
		return products;
	}

	public void setProducts(Set<Product> products) {
		this.products = products;
	}
}
