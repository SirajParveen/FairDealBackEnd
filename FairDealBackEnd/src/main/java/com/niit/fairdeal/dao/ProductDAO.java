package com.niit.fairdeal.dao;

import java.util.List;

import com.niit.fairdeal.domain.Product;

public interface ProductDAO {
	
		/*// get all products
		public List<Product> getAllProducts();
		
		// create product
		public boolean createProduct(Product product);
		
		// update product
		public boolean updateProduct(Product product);
		
		// delete product
		public boolean deleteProduct(Product product);
		
		// get product by id
		public Product getProductByID(int id);
		
		// get product by name
		public Product getProductByName(String name);
		
		public List<Product> navproduct(int id);*/
public List<Product> getAllProducts();
	
	public boolean createProduct(Product product);
	
	public boolean updateProduct(Product product);
	
	public boolean deleteProduct(Product product);
	
	public Product getProductByID(int id);
	
	public Product getProductByName(int name);
	
	public List<Product> navproduct(int id);
	
	public List<Product> getproduct(int id);
}
