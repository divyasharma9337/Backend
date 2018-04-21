package com.niit.shoppingcart.daoimpl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.shoppingcart.dao.OrderDAO;
import com.niit.shoppingcart.domain.Orders;


@Transactional
@Repository("orderDAO") 
public class OrderDAOImpl implements OrderDAO{
	private static Logger log = LoggerFactory.getLogger(OrderDAOImpl.class);
	//@Autowire
		@Autowired //session factory will automatically inject in this class
		 SessionFactory sessionFactory;
		@Autowired
		private Orders orders;
		@SuppressWarnings("unused")
		private Restrictions Restrictions;
		public OrderDAOImpl(SessionFactory sessionFactory) {
			log.info("OrderDAOImpl : getSessionFactory");
			this.sessionFactory=sessionFactory;
			
		}
		public boolean saveOrUpdate(Orders order) {
			try {
				Session session=sessionFactory.openSession();
				Transaction tx=session.beginTransaction();
				
				session.saveOrUpdate(orders);
				tx.commit();
				session.clear();
				
			} catch (Exception e) {
				
			}
			return true;
		}
		public Orders getOrderById(int orderId) {
			Orders order= (Orders) sessionFactory.getCurrentSession().createQuery("from Order where id = :orderId")
					.setParameter("orderId", orderId).uniqueResult();
			return order;
		}
		public boolean deleteOrderById(int orderId) {
			try{
				
				Session session = sessionFactory.getCurrentSession();
				
				Object object = session.load(Orders.class, orderId);
				
				if(object!=null){
					
					session.delete(object);
					log.info("OrderDAOImpl : Order Object deleted Successfully");				
					
				}
				return true;
			}catch(Exception e){
				return false;
			}
		}
		public List<Orders> getAllOrdersOfUser(String emailId) {
			List<Orders> orderList =  sessionFactory.getCurrentSession().createQuery("from Orders where emailId = :emailId and orderStatus = 'PROCESSED'",Orders.class).setParameter("emailId", emailId).list();
			return orderList;
		}
}
