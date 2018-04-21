package com.niit.shoppingcart.daoimpl;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.shoppingcart.dao.CartDAO;
import com.niit.shoppingcart.domain.Cart;

@Transactional
@Repository("cartDAO")
public class CartDAOImpl implements CartDAO{
	@Autowired
	SessionFactory sessionFactory;
	@SuppressWarnings("unused")
	@Autowired 
	private Cart cart;
	private static Logger log = LoggerFactory.getLogger(CartDAOImpl.class);

	public CartDAOImpl(SessionFactory sessionFactory) {
		log.info("CartDAOImpl : getSessionFactory");
		this.sessionFactory=sessionFactory;
	}

	public boolean saveOrUpdate(Cart cart) {
		try {
			log.info("CartDAOImpl : save or update Cart detail");
			Session session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			session.saveOrUpdate(cart);
			tx.commit();
			session.clear();
		} catch (HibernateException e) {
			
			e.printStackTrace();
		}
		
		return true;
	}

	public Cart getCartById(int cartId) {

		log.info("CartDAOImpl : get Cart detail by Id");
		Cart cart= (Cart) sessionFactory.getCurrentSession().createQuery("from Cart where id = :cartId")
				.setParameter("cartId", cartId).uniqueResult();
		
		log.info("CartDAOImpl : Fetched Cart detail by Id => "+cart.toString());
		return cart;
	}
	public boolean deleteCartById(int cartId) {
		try{
			log.info("CartDAOImpl : get Session from session factory");
			
			Session session = sessionFactory.getCurrentSession();
			
			log.info("CartDAOImpl : load cart detail by Id");
			
			Object object = session.load(Cart.class, cartId);
			
			if(object!=null){
				
				session.delete(object);
				log.info("CartDAOImpl : Cart Object deleted Success fully");
				
				
			}
			}catch (Exception e) {
				return false;
			}
			return true;
		
		}
	public List<Cart> getCartByUserId(String emailId) {
		log.info("CartDAOImpl : get Cart detail by user Id");
		List<Cart> cartList=  sessionFactory.getCurrentSession().createQuery("from Cart where emailId = :emailId and status = 'ACTIVE'",Cart.class).setParameter("emailId", emailId).list();
		
		log.info("CartDAOImpl : Fetched Cart detail by User Id => ");
		return cartList;
	}
	
	public double getCartTotal(String emailId) {
	 double totalCartValue =0.0;
		try{
		String queryString = "select sum(subTotal) from Cart c where emailId = :emailId and status = 'ACTIVE'";
		Query query = sessionFactory.getCurrentSession().createQuery(queryString).setParameter("emailId", emailId);
		totalCartValue = (Double) query.getSingleResult();
		}catch(Exception he){
			totalCartValue = 0;
		}
		return totalCartValue;
	}


	public Cart getCartItem(int productId, String emailId) {
		log.info("CartDAOImpl : get Cart Item by user Id and product Id");
		Cart cart;
		try{
		String query = "from Cart where emailId = :emailId and productId = :productId and status = 'ACTIVE'";
		log.info("CartDAOImpl : get Cart Item by user Id and product Id ---set Parameter");
		Query queryObj = sessionFactory.getCurrentSession().createQuery(query,Cart.class).setParameter("emailId", emailId).setParameter("productId", productId);
		log.info("CartDAOImpl : getCartItem --- executeQuery");
		  cart = (Cart) queryObj.getSingleResult();
		}catch(Exception he){
			cart =null;
		}
		
		return cart;
	}

	
	public long getCartSize(String emailId) {
		long totalCartSize = 0;
		try{
		String queryString = "select count(c) from Cart c where emailId = :emailId and status = 'ACTIVE'";
		Query query = sessionFactory.getCurrentSession().createQuery(queryString).setParameter("emailId", emailId);
		totalCartSize = (Long) query.getSingleResult();
		}catch(Exception he){
			totalCartSize = 0;
		}
		return totalCartSize;
	}

	public int updateCartStatus(String emailId, String status) {
		String query = "UPDATE Cart set status = :statusVal WHERE emailId = :emailId AND status = 'ACTIVE'";
		
		 Session session = sessionFactory.openSession();
		 session.beginTransaction();
		 int rowsAffected = session.createQuery(query).setParameter("statusVal", status)
		 .setParameter("emailId", emailId).executeUpdate();
		 session.getTransaction().commit();
		return rowsAffected;
	}

	
	

}