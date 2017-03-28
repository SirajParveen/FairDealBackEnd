package com.niit.fairdeal.dao;

import java.util.List;

import com.niit.fairdeal.domain.Category;

public interface CategoryDAO {

	// declare the methods related to CRUD Operations

	// access_specifier return_type method_name(parameter_list) throws exception_list

	// get all categories
	public List<Category> getAllCategories();

	// create category
	public boolean createCategory(Category category);

	// update category
	public boolean updateCategory(Category category);

	// delete category
	public boolean deleteCategory(Category category);
	
	// get category by id
	public Category getCategoryByID(int id);

	// get category by name
	public Category getCategoryByName(String name);
}