package com.niit.fairdeal.DAOImpl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.fairdeal.dao.CartDAO;
import com.niit.fairdeal.domain.Cart;

@SuppressWarnings("deprecation")
@Repository("cartDAO")
public class CartDAOImpl implements CartDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public CartDAOImpl() {

	}

	public CartDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings({ "unchecked" })
	@Transactional
	public List<Cart> getAllCarts(String userID) 
	{
		String hql = "from Cart where userID=" + "'" + userID + "'  and status = " + "'N'";
		@SuppressWarnings({ "rawtypes" })
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		return query.list();
	}

	@Transactional
	public boolean saveCart(Cart cart) {
		
		/*cart.setId(getMaxId());*/
		try {
			sessionFactory.getCurrentSession().save(cart);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public Long getTotalAmount(String userID) {
		
		String hql = "select sum(price*quantity) from Cart where userID=" + "'" + userID + "' " + "  and status = " + "'N'";

		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		return (Long) query.uniqueResult();
	}

	/*public Long getMaxId() {

		Long maxID = 100L;
		try
		{
			String hql = "select max(id) from Cart";
			@SuppressWarnings("rawtypes")
			Query query = sessionFactory.getCurrentSession().createQuery(hql);
			maxID = (Long) query.uniqueResult();
		} 
		catch (Exception e) 
		{
			maxID = 100L;
			e.printStackTrace();
		}
		return maxID - 1;
	}*/

	public Cart get(String id) {
	
		return (Cart) sessionFactory.getCurrentSession().get(Cart.class, id);
	}

	public boolean deleteCart(Cart cart) {
		
		cart.setStatus('X');
		return updateCart(cart);
	}

	public boolean updateCart(Cart cart) {
		try {
			sessionFactory.getCurrentSession().update(cart);
			return true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
}
