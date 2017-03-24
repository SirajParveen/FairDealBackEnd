package com.niit.fairdeal.DAOImpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.fairdeal.dao.UserDAO;
import com.niit.fairdeal.domain.User;

@SuppressWarnings("deprecation")
@Transactional
@Repository("userDAO")
public class UserDAOImpl implements UserDAO {
	
	
	Logger log=LoggerFactory.getLogger(UserDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	

	public UserDAOImpl(SessionFactory sessionFactory) {
	
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		
		return	sessionFactory.getCurrentSession().createQuery("from User").list();
	}

	public boolean createUser(User user) {
		try
		{
			sessionFactory.getCurrentSession().save(user);
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
			sessionFactory.getCurrentSession().update(user);
			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

/*	public boolean deleteUser(User user) {
		User user1 = null;
		try {
			if(user.getId() != null)
				user1 = getUserByID(user.getId());
			else if(user.getName() != null)
				user1 = getUserByName(user.getName());
			sessionFactory.getCurrentSession().delete(user1);
			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			return false;	
		}
	}*/

	public User getUserByID(String id) {
		
		return (User) sessionFactory.getCurrentSession().get(User.class, id);
	}


	public User getUserByName(String name) {
	
		String hql = "from"+" User"+" where name='"+name+"'";
	log.debug("getUserByName:"+name);
	
	@SuppressWarnings({ "rawtypes" })
	Query query = sessionFactory.getCurrentSession().createQuery(hql);
	
	@SuppressWarnings({ "unchecked" })
	List<User> listUser = (List<User>) query.list();
	log.debug("method executed");
	
		return listUser.get(0);

	
	
}

	
/*	
	public User getUserByName(String name) {
		
		return (User) sessionFactory.getCurrentSession().createQuery("from User where name ='"+name+"'").list().get(0);
	}*/

	public User validateUser(String id, String password) {
		
		String hql = "from User where id = '"+id+"' and password = '"+password+"'";
		
		if(sessionFactory.getCurrentSession().createQuery(hql).uniqueResult() == null)
		{
			return null;
		}
		return (User) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
	}

	public boolean deleteUser(User user) {
		// TODO Auto-generated method stub
		return false;
	}
}
