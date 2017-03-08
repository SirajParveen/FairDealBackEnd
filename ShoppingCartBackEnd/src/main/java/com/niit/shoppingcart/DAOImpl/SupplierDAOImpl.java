package com.niit.shoppingcart.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.domain.Supplier;

@Transactional
@Repository("supplierDAO")
public class SupplierDAOImpl implements SupplierDAO {
	
	@Autowired SessionFactory sessionFactory;
	
	public Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<Supplier> getAllSuppliers() {
		
		return	getSession().createQuery("from Supplier").list();
	}

	public boolean createSupplier(Supplier supplier) {
		try
		{
		getSession().save(supplier);
		return true;
		} catch(Exception e)
		{
			e.printStackTrace(); 
			return false;
		}	
	}

	public boolean updateSupplier(Supplier supplier) {
		try {
			getSession().update(supplier);
			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteSupplier(Supplier supplier) {
		Supplier supplier1 = null;
		try {
			if(supplier.getID() != null)
				supplier1 = getSupplierByID(supplier.getID());
			else if(supplier.getName() != null)
				supplier1 = getSupplierByName(supplier.getName());
			getSession().delete(supplier1);
			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return false;	
		}
	}

	public Supplier getSupplierByID(String id) {
		
		return (Supplier) getSession().get(Supplier.class, id);
	}

	public Supplier getSupplierByName(String name) {
		
		return (Supplier) getSession().createQuery("from Supplier where name ='"+name+"'").list().get(0);
	}
}
