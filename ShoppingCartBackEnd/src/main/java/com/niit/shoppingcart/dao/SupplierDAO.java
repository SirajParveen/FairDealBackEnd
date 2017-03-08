package com.niit.shoppingcart.dao;

import java.util.List;

import com.niit.shoppingcart.domain.Supplier;

public interface SupplierDAO {
	
		// get all suppliers
		public List<Supplier> getAllSuppliers();
		
		// create supplier
		public boolean createSupplier(Supplier supplier);
		
		// update supplier
		public boolean updateSupplier(Supplier supplier);
		
		// delete supplier
		public boolean deleteSupplier(Supplier supplier);
		
		// get supplier by id
		public Supplier getSupplierByID(String id);
		
		// get supplier by name
		public Supplier getSupplierByName(String name);
}
