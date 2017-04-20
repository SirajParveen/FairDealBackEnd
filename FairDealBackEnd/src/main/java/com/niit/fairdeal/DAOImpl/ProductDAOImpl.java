package com.niit.fairdeal.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.fairdeal.dao.ProductDAO;
import com.niit.fairdeal.domain.Product;

@Transactional
@Repository("productDAO")
public class ProductDAOImpl implements ProductDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	public ProductDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory=sessionFactory;
	}
	@SuppressWarnings("unchecked")
	public List<Product> getAllProducts() 
	{
	return	sessionFactory.getCurrentSession().createQuery("from Product").list();
	}

	public boolean createProduct(Product product) 
	{
		try {
			sessionFactory.getCurrentSession().save(product);
			return true;
			}
		catch (Exception e)
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
		catch (Exception e)
		{
		
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteProduct(Product product) {
		try {
			sessionFactory.getCurrentSession().delete(product);
			return true;
			}
		catch (Exception e)
		{
		
			e.printStackTrace();
			return false;
		}
	}

	public Product getProductByID(int id) {
		return (Product) sessionFactory.getCurrentSession().createQuery("from Product where id='"+id+"'").uniqueResult();
	}

	public Product getProductByName(String name) {
		return (Product) sessionFactory.getCurrentSession().createQuery("from Product where name='"+name+"'").list().get(0);
	}
	
	@Transactional
	public List<Product> navproduct(int id) {
		String hql = "from Product where category_id= " + id;
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Product> catproduct = (List<Product>) query.list();
		return catproduct;
	}
	
		@Transactional
		@SuppressWarnings("unchecked")
		public List<Product> getproduct(int id) {
			String hql="from Product where id= "+id;
			@SuppressWarnings("rawtypes")
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			List<Product> listProduct = (List<Product>) query.list();
			return listProduct;
		}
	}