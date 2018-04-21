package com.niit.shoppingcart.daoimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.niit.shoppingcart.dao.UserDAO;
import com.niit.shoppingcart.domain.User;

@Repository("userDAO") //will create instance of UserDAOImpl and the name will userDAOImpl
public class UserDAOImpl implements UserDAO {

	private static Logger log = LoggerFactory.getLogger(UserDAOImpl.class);
	//@Autowire
	@Autowired //session factory will automatically inject in this class
	 SessionFactory sessionFactory;
	@SuppressWarnings("unused")
	@Autowired
	
	private User user;
	@SuppressWarnings("unused")
	private Restrictions Restrictions;
	public UserDAOImpl(){}

	public UserDAOImpl(SessionFactory sessionFactory) {
		log.info("UserDAOImpl : getSessionFactory");
     this.sessionFactory=sessionFactory;
	}

	
	
	public boolean saveOrUpdate(User user) {
	 try {
		Session session=sessionFactory.openSession();
		Transaction tx=session.beginTransaction();
		
		session.saveOrUpdate(user);
		tx.commit();
		session.clear();
		
	} catch (HibernateException e) {
		
		e.printStackTrace();
		return false;
	} 
	 return true;
	}

	

	/*public boolean delete(String emailID) {
		 try {
			 
			 
		 Session session=sessionFactory.openSession();
		        User user = (User) session.get(User.class, emailID);
		        Transaction tx=session.beginTransaction();
		        session.delete(user);
		        tx.commit();
		    } catch (Exception e) {
		        
		        e.printStackTrace();
		    }
		return true; 
	}*/


	public User getUserByEmailId(String emailId) {
		log.info("UserDAOImpl : getUserByEmail -- getCurrentSession");
		Session session = sessionFactory.getCurrentSession();
		
		log.info("UserDAOImpl : getUserByEmail -- select user by email");
		User user = (User) session.createQuery("select u from User u where u.email = :email")
						.setParameter("emailId", emailId).uniqueResult();
		
		return user;
	}

	public List<User> getAllUsers() {
		log.info("UserDAOImpl : getAll the users");
		List<User> users = sessionFactory.getCurrentSession().createQuery("from User", User.class).list();
		return users;
	}

	

}