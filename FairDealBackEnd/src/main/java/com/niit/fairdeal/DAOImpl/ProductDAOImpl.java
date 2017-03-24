package com.niit.fairdeal.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.fairdeal.dao.ProductDAO;
import com.niit.fairdeal.domain.Product;

@Transactional
@Repository("productDAO")
public class ProductDAOImpl implements ProductDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<Product> getAllProducts() {
		
		return	getSession().createQuery("from Product").list();
	}

	public boolean createProduct(Product product) {
		try
		{
		getSession().save(product);
		return true;
		} catch(Exception e)
		{
			e.printStackTrace(); 
			return false;
		}	
	}

	public boolean updateProduct(Product product) {
		try {
			getSession().update(product);
			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteProduct(Product product) {
		Product product1 = null;
		try {
			if(product.getID() != null)
				product1 = getProductByID(product.getID());
			else if(product.getName() != null)
				product1 = getProductByName(product.getName());
			getSession().delete(product1);
			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return false;	
		}
	}

	public Product getProductByID(String id) {
		
		return (Product) getSession().get(Product.class, id);
	}

	public Product getProductByName(String name) {
		
		return (Product) getSession().createQuery("from Product where name ='"+name+"'").list().get(0);
	}
}
