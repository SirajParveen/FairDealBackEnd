package com.niit.fairdeal.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.fairdeal.dao.UserDAO;
import com.niit.fairdeal.domain.User;

@Transactional
@Repository("userDAO")
public class UserDAOImpl implements UserDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		
		return	getSession().createQuery("from User").list();
	}

	public boolean createUser(User user) {
		try
		{
		getSession().save(user);
		return true;
		} 
		catch(Exception e)
		{
			e.printStackTrace(); 
			return false;
		}	
	}

	public boolean updateUser(User user) {
		try {
			getSession().update(user);
			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean deleteUser(User user) {
		User user1 = null;
		try {
			if(user.getId() != null)
				user1 = getUserByID(user.getId());
			else if(user.getName() != null)
				user1 = getUserByName(user.getName());
			getSession().delete(user1);
			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return false;	
		}
	}

	public User getUserByID(String id) {
		
		return (User) getSession().get(User.class, id);
	}

	public User getUserByName(String name) {
		
		return (User) getSession().createQuery("from User where name ='"+name+"'").list().get(0);
	}

	public User validateUser(String id, String password) {
		
		String hql = "from User where id = '"+id+"' and password = '"+password+"'";
		
		if(getSession().createQuery(hql).uniqueResult() == null)
		{
			return null;
		}
		return (User) getSession().createQuery(hql).uniqueResult();
	}
}
