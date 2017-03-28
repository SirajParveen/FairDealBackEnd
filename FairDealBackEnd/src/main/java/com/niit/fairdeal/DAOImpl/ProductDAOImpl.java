package com.niit.fairdeal.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.fairdeal.dao.ProductDAO;
import com.niit.fairdeal.domain.Product;

@Transactional
@Repository("productDAO")
public class ProductDAOImpl implements ProductDAO {
	
	private static final Logger log = LoggerFactory.getLogger(ProductDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public ProductDAOImpl(SessionFactory sessionFactory) 
	{
		log.info("Product Session");
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Product> getAllProducts() {
		
		log.debug("Body of the method getAllProducts");
		return getSession().createQuery("from Product").list();
	}

	@Transactional
	public boolean createProduct(Product product) {
		
		log.debug("Starting of the method createProduct");
		try
		{
			getSession().save(product);
			log.debug("Ending of the method createProduct");
		return true;
		} catch(Exception e)
		{
			e.printStackTrace(); 
			log.error("Exception occurred while creating product");
			log.error(e.getMessage());
			return false;
		}	
	}

	@Transactional
	public boolean updateProduct(Product product) {
		
		log.debug("Starting of the method updateProduct");
		try {
			getSession().update(product);
			log.debug("Ending of the method updateProduct");
			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occurred while updating product");
			log.error(e.getMessage());
			return false;
		}
	}

	@Transactional
	public boolean deleteProduct(Product product) {
		
		log.debug("Starting of the method deleteProduct");
		try {
			getSession().delete(product);
			log.debug("Ending of the method deleteProduct");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occurred while deleting product");
			log.error(e.getMessage());
			return false;
		}
	}

	@Transactional
	public Product getProductByID(int id) {
		
		log.debug("Body of the method getProductByID");
		return (Product) getSession().get(Product.class, id);
	}

	@Transactional
	public Product getProductByName(String name) {
		
		log.debug("Body of the method getProductByName");
		return (Product) getSession().createQuery("from Product where name ='"+name+"'").list().get(0);
	}
}
