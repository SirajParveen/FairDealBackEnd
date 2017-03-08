package com.niit.shoppingcart.testcase;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.domain.Supplier;

public class SupplierTestCase {

	@Autowired
	private static Supplier supplier;
	
	@Autowired
	private static SupplierDAO supplierDAO;
	
	@BeforeClass
	public static void initialize()
	{
		@SuppressWarnings("resource")
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
		context.scan("com.niit.shoppingcart");
		context.refresh();

		supplier = (Supplier) context.getBean("supplier");
		supplierDAO = (SupplierDAO) context.getBean("supplierDAO");
	}
	

	@Test
	public void getAllSuppliersTestCase() {
		
		int recordsFromDB = supplierDAO.getAllSuppliers().size();

		assertEquals("getAllSuppliersTestCase", 3, recordsFromDB);
	}

	@Test
	public void createSupplierTestCase() {
		
		supplier.setID("01");
		supplier.setName("Kushal");
		supplier.setAddress("kurnool");
		
		boolean flag = supplierDAO.createSupplier(supplier);
		
		assertEquals("createSupplierTestCase", true, flag);
	}

	@Test
	public void updateSupplierTestCase() {

		supplier.setID("04");
		supplier.setName("Pradeep");
		supplier.setAddress("KPHB");

		boolean flag = supplierDAO.updateSupplier(supplier);

		assertEquals("updateSupplierTestCase", true, flag);
	}

	@Test
	public void deleteSupplierTestCase()
	{
		supplier.setID("01");
		
		boolean flag = supplierDAO.deleteSupplier(supplier);
		
		assertEquals("deleteSupplierTestCase", true, flag);
	}
	
	@Test
	public void getSupplierByIDTestCase()
	{
		supplier = supplierDAO.getSupplierByID("02");
		
		assertEquals("getSupplierByIDTestCase", null, supplier);
	}
	
	@Test
	public void getSupplierByNameTestCase()
	{
		supplier = supplierDAO.getSupplierByName("Imran");
		
		assertEquals("getSupplierByNameTestCase", null, supplier);
	}
}
