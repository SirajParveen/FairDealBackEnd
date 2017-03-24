package com.niit.fairdeal.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Entity
@Component
public class User {
	
	// Please add validations related annotations in other domain objects also

	@Id
	private String id;
	
/*	@NotEmpty(message="User name should not be empty")*/
	private String name;
	
/*	@NotEmpty(message="Password should not be empty")
	@Min(5)
	@Max(15)*/
	private String password;
	
/*	@NotEmpty
	@Column(unique=true, nullable=false)*/
	private String mail;

	/*@NotEmpty*/
	private String contact;
	
/*	@NotEmpty*/
	private String role;

	private boolean enabled;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}


	
}
