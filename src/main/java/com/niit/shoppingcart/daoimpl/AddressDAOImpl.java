package com.niit.shoppingcart.daoimpl;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.niit.shoppingcart.dao.AddressDAO;
import com.niit.shoppingcart.domain.Address;
@Transactional
@Repository("AddressDAO") 
public class AddressDAOImpl implements AddressDAO {
	private static Logger log = LoggerFactory.getLogger(AddressDAOImpl.class);
	@Autowired //session factory will automatically inject in this class
	 SessionFactory sessionFactory;
	@SuppressWarnings("unused")
	@Autowired
	private Address address;
	@SuppressWarnings("unused")
	private Restrictions Restrictions;
	public AddressDAOImpl(SessionFactory sessionFactory) {
		log.info("AddressDAOImpl : getSessionFactory");
		this.sessionFactory=sessionFactory;
		
	}

	public boolean saveOrUpdate(Address address) {
		try {
			Session session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			
			session.saveOrUpdate(address);
			tx.commit();
			session.clear();
			
		} catch (Exception e) {
			
		}
		return true;
	}
	

	public Address getAddressById(int addressId) {
		log.info("AddressDAOImpl : get Address detail by Id --"+addressId);
		Address address =  (Address) sessionFactory.getCurrentSession().createQuery("select a from Address a where a.id = :addressId")
				.setParameter("addressId", addressId).uniqueResult();
		log.info("AddressDAOImpl : Address detail by Id --"+address.toString());
		return address;
	}

	public boolean deleteAddressById(int addressId) {
		log.info("AddressDAOImpl : get Session Factory");
		Session session = sessionFactory.getCurrentSession();
		log.info("AddressDAOImpl : load Address detail by Id --"+addressId);
		Object persistenceInstance = session.load(Address.class, addressId);
		
		if(persistenceInstance !=null){
			log.info("AddressDAOImpl : delete Address detail by Id --"+addressId);
			session.delete(persistenceInstance);
			log.info("AddressDAOImpl : Address deleted Successfully --"+addressId);
			System.out.println("Address deleted Successfully");
		}
		
		return true;
	}

	public List<Address> getAllAddressOfUser(String emailId) {
		log.info("AddressDAOImpl : get Session Factory");
		Session session = sessionFactory.getCurrentSession();
		log.info("AddressDAOImpl : load all Address details of given user Id --"+emailId);
		CriteriaBuilder cb =  session.getCriteriaBuilder();
		CriteriaQuery<Address> cq = cb.createQuery(Address.class);
		
		Root<Address> addressRoot  = cq.from(Address.class);
		@SuppressWarnings("unused")
		ParameterExpression<Integer> personId = cb.parameter(Integer.class);
		
		cq.select(addressRoot).where(cb.equal(addressRoot.get("personId"), emailId));
		
		Query query = session.createQuery(cq);
		
		log.info("AddressDAOImpl : criteria query of Address details of given user Id --"+query.toString());
		@SuppressWarnings(value="unchecked")
		List<Address> results = (List<Address>) query.getResultList();
		log.info("AddressDAOImpl : Successful retrieval of Address details of given user Id --"+emailId);
		return results;
	}

	public Address getAddressOfUser(String emailId) {

		Session session = sessionFactory.openSession();
		Address address = session.createQuery("from Address where personId = :emailId",Address.class).setParameter("emailId", emailId).uniqueResult();
		session.close();
		return address;
	}

	

}
