package com.niit.fairdeal.DAOImpl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.niit.fairdeal.dao.CartDAO;
import com.niit.fairdeal.domain.Cart;

@Repository
public class CartDAOImpl implements CartDAO {
	
public static final Logger log=LoggerFactory.getLogger(CartDAOImpl.class);
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public CartDAOImpl(SessionFactory sessionFactory) {
		log.info("cart session");
		this.sessionFactory=sessionFactory;
	}
	
	@Transactional
	public boolean save(Cart cart) {
		log.info("cart save operation started");
		try {
			sessionFactory.getCurrentSession().saveOrUpdate (cart);
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
			log.info("cart saved");
			return false;
		}
		
	}
	@Transactional
	public boolean update(Cart cart) {
		try {
			sessionFactory.getCurrentSession().update(cart);
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}
	}

	@Transactional
	public boolean delete(Cart cart) {
		try {
			sessionFactory.getCurrentSession().delete(cart);
			return true;
		} catch (Exception e) {
			
			e.printStackTrace();
			return false;
		}

	}
@Transactional
	@SuppressWarnings({ "unchecked", "deprecation" })
	public List<Cart> list() {
		List<Cart> listCart = (List<Cart>)sessionFactory.getCurrentSession()
						.createCriteria(Cart.class)
						.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

				return listCart;
			}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	public List<Cart> get(int userid) {
		String hql = "from"+" Cart"+" where userid="+userid+"and status='C'";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		List<Cart> list= (List<Cart>)query.list();
		return list;
	 
	}
	@Transactional
	@SuppressWarnings({ "unchecked" })
	public Cart getproduct(int productid,int userid) {
		String hql = "from"+" Cart"+" where Status='C'and userid="+userid+" and productid="+productid;
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Cart>listproduct=query.list();

		if(listproduct.isEmpty())
		{
			
			return null;
		}
		else
		{
			System.out.println("product");
			return listproduct.get(0);
		}
	}
	
	@Transactional
	public void pay(int userId) {
		String hql="update Cart set status='P' where userid="+userId;	
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}
	

	@SuppressWarnings("deprecation")
	@Transactional
	public long CartPrice(int userId) {
		Criteria c=sessionFactory.getCurrentSession().createCriteria(Cart.class);
		c.add(Restrictions.eq("userid", userId));
		c.add(Restrictions.eq("status","C"));
		c.setProjection(Projections.sum("price"));
		Long l= (Long)c.uniqueResult();
		return l;
	}
	@SuppressWarnings("deprecation")
	@Transactional
	public long cartsize(int userId) {
		Criteria c=sessionFactory.getCurrentSession().createCriteria(Cart.class);
		c.add(Restrictions.eq("userid", userId));
		c.add(Restrictions.eq("status","C"));
		c.setProjection(Projections.count("userid"));
		Long count=(Long) c.uniqueResult();
		return count;
	}
	/*@Transactional
	public Cart getproduct(int cartId) {
		String hql = "from"+" Cart"+" where id="+cartId;
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Cart> list =query.list();
		if (list == null || list.isEmpty()) {
			return null;
		} else {
			return list.get(0);
		}
	}*/
	@Transactional
	public Cart getitem(int cartId) {
		String hql = "from"+" Cart"+" where id="+cartId;
		@SuppressWarnings("rawtypes")
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		@SuppressWarnings("unchecked")
		List<Cart> list = (List<Cart>) query.list();
		if (list!= null && !list.isEmpty()) {
			return list.get(0);
		}
		return null;
	}

}
