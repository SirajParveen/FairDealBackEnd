package com.niit.fairdeal.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.fairdeal.dao.SupplierDAO;
import com.niit.fairdeal.domain.Supplier;

@Transactional
@Repository("supplierDAO")
public class SupplierDAOImpl implements SupplierDAO {
	
	@Autowired SessionFactory sessionFactory;

	public SupplierDAOImpl(SessionFactory sessionFactory) {
	
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<Supplier> getAllSuppliers() {
		
		return	sessionFactory.getCurrentSession().createQuery("from Supplier").list();
	}

	public boolean createSupplier(Supplier supplier) {
		try
		{
		sessionFactory.getCurrentSession().save(supplier);
		return true;
		} catch(Exception e)
		{
			e.printStackTrace(); 
			return false;
		}	
	}

	public boolean updateSupplier(Supplier supplier) {
		try {
			sessionFactory.getCurrentSession().update(supplier);
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
			if(supplier.getId() != null)
				supplier1 = getSupplierByID(supplier.getId());
			else if(supplier.getName() != null)
				supplier1 = getSupplierByName(supplier.getName());
			sessionFactory.getCurrentSession().delete(supplier1);
			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return false;	
		}
	}

	public Supplier getSupplierByID(String id) {
		
		return (Supplier) sessionFactory.getCurrentSession().get(Supplier.class, id);
	}

	public Supplier getSupplierByName(String name) {
		
		return (Supplier) sessionFactory.getCurrentSession().createQuery("from Supplier where name ='"+name+"'").list().get(0);
	}
}
