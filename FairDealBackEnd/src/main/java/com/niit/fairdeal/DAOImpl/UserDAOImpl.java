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

import com.niit.fairdeal.dao.UserDAO;
import com.niit.fairdeal.domain.User;

@SuppressWarnings("deprecation")
@Transactional
@Repository("userDAO")
public class UserDAOImpl implements UserDAO {
	
	
	private static final Logger log = LoggerFactory.getLogger(UserDAOImpl.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public UserDAOImpl(SessionFactory sessionFactory) {
	
		log.info("User Session");
		this.sessionFactory = sessionFactory;
	}

	public Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		
		log.debug("Body of the method getAllUsers");
		return	getSession().createQuery("from User").list();
	}

	@Transactional
	public boolean createUser(User user) {
		
		log.debug("Starting of the method createUser");
		try
		{
			sessionFactory.getCurrentSession().save(user);
			log.debug("Ending of the method createUser");
			return true;
		} 
		catch(Exception e)
		{
			e.printStackTrace(); 
			log.error("Exception occurred while creating user");
			log.error(e.getMessage());
			return false;
		}	
	}

	@Transactional
	public boolean updateUser(User user) {
		
		log.debug("Starting of the method updateUser");
		try {
			sessionFactory.getCurrentSession().update(user);
			log.debug("Ending of the method updateUser");
			return true;
		} 
		catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occurred while updating user");
			log.error(e.getMessage());
			return false;
		}
	}

	@Transactional
	public boolean deleteUser(User user) {
		
		log.debug("Starting of the method deleteCategory");
		try {
			getSession().delete(user);
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
	public User getUserByID(int id) {
		
		log.debug("Body of the method getUserByID");
		return (User) getSession().get(User.class, id);
	}


	@Transactional
	public User getUserByName(String name) {
		
		log.debug("Starting of the method getUserByName");
	
		String hql = "from"+" User"+" where name='"+name+"'";
	log.debug("getUserByName:"+name);
	
	@SuppressWarnings({ "rawtypes" })
	Query query = sessionFactory.getCurrentSession().createQuery(hql);
	
	@SuppressWarnings({ "unchecked" })
	List<User> listUser = (List<User>) query.list();
	log.debug("method executed");
	
		log.debug("Ending of the method getUserByName");
		return listUser.get(0);
	}

	@Transactional
	public User validateUser(int id, String password) {
		
		log.debug("Starting of the method validateUser");
		
		String hql = "from User where id = '"+id+"' and password = '"+password+"'";
		
		if(sessionFactory.getCurrentSession().createQuery(hql).uniqueResult() == null)
		{
			return null;
		}
		log.debug("Ending of the method validateUser");
		return (User) sessionFactory.getCurrentSession().createQuery(hql).uniqueResult();
	}
}
