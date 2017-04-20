package com.niit.fairdeal.dao;

import java.util.List;

import com.niit.fairdeal.domain.Product;

public interface ProductDAO {
	
public List<Product> getAllProducts();
	
	public boolean createProduct(Product product);
	
	public boolean updateProduct(Product product);
	
	public boolean deleteProduct(Product product);
	
	public Product getProductByID(int id);
	
	public Product getProductByName(String name);
	
	public List<Product> navproduct(int id);
	
	public List<Product> getproduct(int id);
}
