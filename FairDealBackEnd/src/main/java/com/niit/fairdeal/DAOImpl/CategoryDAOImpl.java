package com.niit.fairdeal.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.fairdeal.dao.CategoryDAO;
import com.niit.fairdeal.domain.Category;

@SuppressWarnings("deprecation")
@Transactional
@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO 
{
	private static final Logger log = LoggerFactory.getLogger(CategoryDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	public CategoryDAOImpl(SessionFactory sessionFactory) 
	{
		log.info("Category Session");
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings({ "unchecked" })
	@Transactional
	public List<Category> getAllCategories() {
		
		log.debug("Starting of the method getAllCategories");
		
		String hql = "from Category";
		@SuppressWarnings("rawtypes")
		Query query =  getSession().createQuery(hql);
		
		log.debug("Ending of the method getAllCategories");
	    return	 query.list();
	}

	@Transactional
	public boolean createCategory(Category category) {
		
		log.debug("Starting of the method createCategory");
		try
		{
		getSession().save(category);
		log.debug("Ending of the method createCategory");
		return true;
		} 
		catch(Exception e)
		{
			e.printStackTrace(); 
			log.error("Exception occurred while creating category");
			log.error(e.getMessage());
			return false;
		}	
	}

	@Transactional
	public boolean updateCategory(Category category) {
		
		log.debug("Starting of the method updateCategory");
		try {
			getSession().update(category);
			log.debug("Ending of the method updateCategory");
			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occurred while updating category");
			log.error(e.getMessage());
			return false;
		}
	}

	@Transactional
	public boolean deleteCategory(Category category) {
		
		log.debug("Starting of the method deleteCategory");
		try {
			getSession().delete(category);
			log.debug("Ending of the method deleteCategory");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occurred while deleting category");
			log.error(e.getMessage());
			return false;
		}
	}

	@Transactional
	public Category getCategoryByID(int id) {
		
		log.debug("Starting of the method getCategoryByID");
		
		String hql = "from Category where id=" + "'"+ id +"'";
		
		@SuppressWarnings("rawtypes")
		Query query = getSession().createQuery(hql);
		
		log.debug("hql:" + hql);
		
		@SuppressWarnings("unchecked")
		List<Category> listCategory = (List<Category>) query.list();
		
		if (listCategory != null && !listCategory.isEmpty()) 
		{
			return listCategory.get(0);
		}
		log.debug("Ending of the method getCategoryByID");
		return null;
	}

	@Transactional
	public Category getCategoryByName(String name) {
		
		log.debug("Starting of the method getCategoryByName");
		
		String hql = "from Category where name=" + "'"+ name +"'";
		
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		log.debug("Ending of the method getCategoryByName");
		
		return (Category)query.uniqueResult();
		
	/*	@SuppressWarnings("unchecked")
		List<Category> listCategory = (List<Category>) query.list();
		
		if (listCategory != null && !listCategory.isEmpty()) {
			return listCategory.get(0);
		}
		
		return null;*/
	}
}
