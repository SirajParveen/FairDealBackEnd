package com.niit.shoppingcart.dao;

import java.util.List;

import com.niit.shoppingcart.domain.User;

public interface UserDAO {
	
	// get all users
	public List<User> getAllUsers();
	
	// create user
	public boolean createUser(User user);
	
	// update user
	public boolean updateUser(User user);
	
	// delete user
	public boolean deleteUser(User user);
	
	// get user by id
	public User getUserByID(String id);
	
	// get user by name
	public User getUserByName(String name);
	
	// validate the user
	public boolean validateUser(String id, String password);
}