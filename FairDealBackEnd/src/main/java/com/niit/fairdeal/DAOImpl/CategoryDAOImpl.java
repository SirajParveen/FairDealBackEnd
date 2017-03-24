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
	
	public CategoryDAOImpl()
	{
		
	}

	@Autowired
	private SessionFactory sessionFactory;
	
	public CategoryDAOImpl(SessionFactory sessionFactory) 
	{
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings({ "unchecked" })
	@Transactional
	public List<Category> getAllCategories() {
		
		/*return getSession().createQuery("from Category").list();*/
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
			log.error("Exception occured while saving category");
			log.error(e.getMessage());
			return false;
		}	
	}
	
	/*@Transactional
	public boolean saveOrUpdate(Category category) { 
		
		log.debug("Starting of the method saveOrUpdate");
		try {
			getSession().saveOrUpdate(category);
			log.debug("Ending of the method saveOrUpdate");
			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occured while saveOrUpdate category");
			log.error(e.getMessage());
			return false;
		}	
	}*/

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
			log.error("Exception occured while update category");
			log.error(e.getMessage());
			return false;
		}
	}

	@Transactional
	public boolean deleteCategory(Category category) {
		
		log.debug("Starting of the method delete");
		log.info("Going go delete :"+category);
		Category category1 = null;
		try {
			if(category.getID() != null)
				category1 = getCategoryByID(category.getID());
			else if(category.getName() != null)
				category1 = getCategoryByName(category.getName());
			getSession().delete(category1);
			log.debug("Ending of the method deleteCategory");
			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return false;	
		}
	}

	@Transactional
	public Category getCategoryByID(String id) {
		
		log.debug("Calling get");
		
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
		log.debug("Ending getCategoryByID");
		return null;
	}

	public Category getCategoryByName(String name) {
		
		log.debug("Calling getCategoryByName");
		
		String hql = "from Category where name=" + "'"+ name +"'";
		
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		log.debug("Ending getCategoryByName");
		
		return (Category)query.uniqueResult();
		
	/*	@SuppressWarnings("unchecked")
		List<Category> listCategory = (List<Category>) query.list();
		
		if (listCategory != null && !listCategory.isEmpty()) {
			return listCategory.get(0);
		}
		
		return null;*/
	}
}
