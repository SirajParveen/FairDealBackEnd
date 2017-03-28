package com.niit.fairdeal.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.fairdeal.dao.SupplierDAO;
import com.niit.fairdeal.domain.Supplier;

@Transactional
@Repository("supplierDAO")
public class SupplierDAOImpl implements SupplierDAO {
	
	private static final Logger log = LoggerFactory.getLogger(SupplierDAOImpl.class);
	
	@Autowired 
	private SessionFactory sessionFactory;

	public SupplierDAOImpl(SessionFactory sessionFactory) {
	
		log.info("Supplier Session");
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	@SuppressWarnings("unchecked")
	public List<Supplier> getAllSuppliers() {
		
		log.debug("Body of the method getAllSuppliers");
		return	getSession().createQuery("from Supplier").list();
	}

	@Transactional
	public boolean createSupplier(Supplier supplier) {
		log.debug("Starting of the method createSupplier");
		try
		{
		getSession().save(supplier);
		log.debug("Ending of the method createSupplier");
		return true;
		} catch(Exception e)
		{
			e.printStackTrace(); 
			log.error("Exception occurred while creating supplier");
			log.error(e.getMessage());
			return false;
		}	
	}

	@Transactional
	public boolean updateSupplier(Supplier supplier) {
		
		log.debug("Starting of the method updateSupplier");
		try {
			getSession().update(supplier);
			log.debug("Ending of the method updateSupplier");
			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occurred while updating supplier");
			log.error(e.getMessage());
			return false;
		}
	}

	@Transactional
	public boolean deleteSupplier(Supplier supplier) {

		log.debug("Starting of the method deleteSupplier");
		try {
			getSession().delete(supplier);
			log.debug("Ending of the method deleteSupplier");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occurred while deleting supplier");
			log.error(e.getMessage());
			return false;
		}
	}

	@Transactional
	public Supplier getSupplierByID(int id) {
	
		log.debug("Body of the methpd getSupplierByID");
		return (Supplier) sessionFactory.getCurrentSession().get(Supplier.class, id);
	}

	@Transactional
	public Supplier getSupplierByName(String name) {
		
		log.debug("Body of the method getSupplierByName");
		return (Supplier) sessionFactory.getCurrentSession().createQuery("from Supplier where name ='"+name+"'").list().get(0);
	}
}
