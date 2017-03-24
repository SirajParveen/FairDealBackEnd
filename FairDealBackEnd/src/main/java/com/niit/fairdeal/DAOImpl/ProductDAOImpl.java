package com.niit.fairdeal.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.fairdeal.dao.ProductDAO;
import com.niit.fairdeal.domain.Product;

@Transactional
@Repository("productDAO")
public class ProductDAOImpl implements ProductDAO {
	
	

	public ProductDAOImpl(SessionFactory sessionFactory) {
	
		this.sessionFactory = sessionFactory;
	}

	@Autowired
	private SessionFactory sessionFactory;
	

	@SuppressWarnings("unchecked")
	public List<Product> getAllProducts() {
		
		return sessionFactory.getCurrentSession().createQuery("from Product").list();
	}

	public boolean createProduct(Product product) {
		try
		{
			sessionFactory.getCurrentSession().save(product);
		return true;
		} catch(Exception e)
		{
			e.printStackTrace(); 
			return false;
		}	
	}

	public boolean updateProduct(Product product) {
		try {
			sessionFactory.getCurrentSession().update(product);
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
			if(product.getId() != null)
				product1 = getProductByID(product.getId());
			else if(product.getName() != null)
				product1 = getProductByName(product.getName());
			sessionFactory.getCurrentSession().delete(product1);
			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return false;	
		}
	}

	public Product getProductByID(String id) {
		
		return (Product)sessionFactory.getCurrentSession().get(Product.class, id);
	}

	public Product getProductByName(String name) {
		
		return (Product) sessionFactory.getCurrentSession().createQuery("from Product where name ='"+name+"'").list().get(0);
	}
}
