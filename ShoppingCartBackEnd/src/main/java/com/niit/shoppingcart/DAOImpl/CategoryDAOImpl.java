package com.niit.shoppingcart.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.domain.Category;

@Transactional
@Repository("categoryDAO")
public class CategoryDAOImpl implements CategoryDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<Category> getAllCategories() {
		
			//select * from category  -SQL Query - mention the table name
			//from Category  -> HQL -mention Domain class name not table name
				
			//convert the hibernate query into db specific language
			return	getSession().createQuery("from Category").list();
	}

	public boolean createCategory(Category category) {
		try
		{
		getSession().save(category);
		return true;
		} catch(Exception e)
		{
			e.printStackTrace(); 
			return false;
		}	
	}

	public boolean updateCategory(Category category) {
		try {
			getSession().update(category);
			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteCategory(Category category) {
		Category category1 = null;
		try {
			if(category.getID() != null)
				category1 = getCategoryByID(category.getID());
			else if(category.getName() != null)
				category1 = getCategoryByName(category.getName());
			getSession().delete(category1);
			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return false;	
		}
	}

	public Category getCategoryByID(String id) {
		
		return (Category) getSession().get(Category.class, id);
	}

	public Category getCategoryByName(String name) {
		
		// select * from category where name ='mobile'
		
		return (Category) getSession().createQuery("from Category where name ='"+name+"'").list().get(0);
	}
}
