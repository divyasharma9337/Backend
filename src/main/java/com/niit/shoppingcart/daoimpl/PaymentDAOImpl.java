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

import com.niit.shoppingcart.dao.PaymentDAO;
import com.niit.shoppingcart.domain.Payment;
@Transactional
@Repository("paymentDAO") 
public class PaymentDAOImpl implements PaymentDAO{
	private static Logger log = LoggerFactory.getLogger(PaymentDAOImpl.class);
	//first -inject hibernate session 
	//@Autowire
	@Autowired //session factory will automatically inject in this class
	 SessionFactory sessionFactory;
	@SuppressWarnings("unused")
	@Autowired
	private Payment payment;
	@SuppressWarnings("unused")
	private Restrictions Restrictions;
	public PaymentDAOImpl(SessionFactory sessionFactory) {
		log.info("PaymentDAOImpl : getSessionFactory");
		this.sessionFactory=sessionFactory;
		
	}

	public boolean savePaymentInfo(Payment payment) {
		try {
			Session session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			
			session.saveOrUpdate(payment);
			tx.commit();
			session.clear();
			
		} catch (Exception e) {
			
		}
		return true;
	}

	public Payment getPaymentInfo(int paymentId) {
		log.info("PaymentDAOImpl : get Payment detail by payment Id");
		Payment payment= (Payment) sessionFactory.getCurrentSession().createQuery("from Payment where id = :Id")
				.setParameter("Id", paymentId).uniqueResult();
		
		log.info("PaymentDAOImpl : Fetched Payment detail by Id => "+payment.toString());
		return payment;
	}

	public List<Payment> getUserPaymentInfo(String emailId) {
		log.info("PaymentDAOImpl : get Payment detail by User Id");
		List<Payment> paymentList=  sessionFactory.getCurrentSession().createQuery("from Payment where emailId = :emailId",Payment.class)
				.setParameter("emailId", emailId).list();
		return paymentList;
	}

	public List<Payment> getUserCardPaymentInfo(String emailId) {
		log.info("PaymentDAOImpl : get User Card Payment detail by User Id");
		List<Payment> paymentList=  sessionFactory.getCurrentSession().createQuery("from Payment where emailId = :emailId and paymentMethod IN ('creditcard','debitcard')",Payment.class)
				.setParameter("emailId", emailId).list();
		return paymentList;
	}

}
