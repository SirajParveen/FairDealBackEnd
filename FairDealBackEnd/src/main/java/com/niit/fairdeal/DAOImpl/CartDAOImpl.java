package com.niit.fairdeal.DAOImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.fairdeal.dao.CartDAO;
import com.niit.fairdeal.domain.Cart;

@SuppressWarnings("deprecation")
@Repository("cartDAO")
public class CartDAOImpl implements CartDAO {
	
	public static final Logger log = LoggerFactory.getLogger(CartDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;

	public CartDAOImpl(SessionFactory sessionFactory) {
		log.info("Cart Session");
		this.sessionFactory = sessionFactory;
	}
	
	public Session getSession()
	{
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings({ "unchecked" })
	@Transactional
	public List<Cart> getAllCarts(int userID) 
	{
		log.debug("Starting of the method getAllCarts");
		
		String hql = "from Cart where userID=" + "'" + userID + "'  and status = " + "'N'";
		@SuppressWarnings({ "rawtypes" })
		Query query = getSession().createQuery(hql);
		
		log.debug("Ending of the method getAllCarts");
		return query.list();
	}

	@Transactional
	public Cart get(int id) 
	{
		log.debug("Body of the method get");
		return (Cart) getSession().get(Cart.class, id);
	}
	
	@Transactional
	public boolean createCart(Cart cart) {
		
		log.debug("Starting of the method createCart");
		
		/*cart.setId(getMaxId());*/
		try {
			getSession().save(cart);
			log.debug("Ending of the method createCart");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occurred while creating cart");
			log.error(e.getMessage());
			return false;
		}
	}
	
	@Transactional
	public boolean deleteCart(Cart cart) {
		
		log.debug("Body of the method deleteCart");
		cart.setStatus('X');
		return updateCart(cart);
	}

	@Transactional
	public boolean updateCart(Cart cart) {
		
		log.debug("Starting of the method updateCart");
		try {
			getSession().update(cart);
			log.debug("Ending of the method updateCart");
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception occurred while updating cart");
			log.error(e.getMessage());
			return false;
		}
	}

	@Transactional
	public Long getTotalAmount(int userID) {
		
		log.debug("Starting of the method getTotalAmount");
		
		String hql = "select sum(price*quantity) from Cart where userID=" + "'" + userID + "' " + "  and status = " + "'N'";

		@SuppressWarnings("rawtypes")
		Query query = getSession().createQuery(hql);
		
		log.debug("Ending of the method getTotalAmount");
		return (Long) query.uniqueResult();
	}

	public int getAllCarts(String loggedInUserid) {
		// TODO Auto-generated method stub
		return 0;
	}

	public Object getTotalAmount(String loggedInUserid) {
		// TODO Auto-generated method stub
		return null;
	}

	/*public Long getMaxId() {

		Long maxID = 100L;
		try
		{
			String hql = "select max(id) from Cart";
			@SuppressWarnings("rawtypes")
			Query query = getSession().createQuery(hql);
			maxID = (Long) query.uniqueResult();
		} 
		catch (Exception e) 
		{
			maxID = 100L;
			e.printStackTrace();
		}
		return maxID - 1;
	}*/
}
