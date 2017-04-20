package com.niit.fairdeal.testcase;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.fairdeal.dao.ProductDAO;
import com.niit.fairdeal.domain.Product;

public class ProductTestCase {

	@Autowired
	private static Product product;
	
	@Autowired
	private static ProductDAO productDAO;
	
	@BeforeClass
	public static void initialize()
	{
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
	
		context.scan("com.niit");
		context.refresh();
		
		product = (Product) context.getBean("product");
		productDAO = (ProductDAO) context.getBean("productDAO");
	}
	
	@Test
	public void getAllProductsTestCase()
	{
		int recordFromDB = productDAO.getAllProducts().size();
		
		assertEquals("getAllProductsTestCase", 3, recordFromDB);
	}
	
	@Test
	public void createProductTestCase()
	{
		product.setName("Handbag");
		product.setPrice(2500);
		product.setDescription("This is Handbag");
		product.setCategory_id(06);
		product.setSupplier_id(01);
		
		boolean flag = productDAO.createProduct(product);
		
		assertEquals("createProductTestCase", true, flag);
	}
	
	@Test
	public void updateProductTestCase()
	{
		product.setName("Lenovo k3");
		product.setDescription("This is Lenovo mobile brand");
		product.setPrice(10000);
		product.setCategory_id(03);
		product.setSupplier_id(02);
		 
		boolean flag = productDAO.updateProduct(product);
		
		assertEquals("updateProductTestCase", true, flag);
	}
	
	@Test
	public void deleteProductTestCase()
	{
		product.setId(04);
		
		boolean flag = productDAO.deleteProduct(product);
		
		assertEquals("deleteProductTestCase", true, flag);
	}
	
	@Test
	public void getProductByIDTestCase()
	{
		product = productDAO.getProductByID(01);
		
		assertEquals("getProductByIDTestCase", null, product);
	}
	
	@Test
	public void getProductByNameTestCase()
	{
		product = productDAO.getProductByName("Kurta");
		
		assertEquals("getProductByNameTestCase", null, product);
	}
}