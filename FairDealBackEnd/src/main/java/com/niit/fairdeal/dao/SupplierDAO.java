package com.niit.fairdeal.dao;

import java.util.List;

import com.niit.fairdeal.domain.Supplier;

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
		public Supplier getSupplierByID(int id);
		
		// get supplier by name
		public Supplier getSupplierByName(String name);
}
