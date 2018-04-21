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

import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.domain.Supplier;

@Repository //will create instance of UserDAOImpl and the name will userDAOImpl
public class SupplierDAOImpl implements SupplierDAO {
	private static Logger log = LoggerFactory.getLogger(SupplierDAOImpl.class);
	@Autowired
	SessionFactory sessionFactory;
	@SuppressWarnings("unused")
	@Autowired
     private Supplier supplier;
	 @SuppressWarnings("unused")
	private Restrictions Restrictions;
	public SupplierDAOImpl(SessionFactory sessionFactory) {
		this.sessionFactory=sessionFactory;
	}
	public boolean saveOrUpdate(Supplier supplier) {
		try {
			Session session=sessionFactory.openSession();
			Transaction tx=session.beginTransaction();
			session.saveOrUpdate(supplier);
			tx.commit();
			session.clear();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public Supplier getSupplierById(int supplierId) {
		log.info("SupplierDAOImpl : get Supplier detail by Id -- "+supplierId);
		Supplier supplier = (Supplier) sessionFactory.getCurrentSession().createQuery("select s from Supplier s where s.id = :supplierId")
				.setParameter("supplierId", supplierId).uniqueResult();
		//System.out.println(supplier.toString());
		log.info("SupplierDAOImpl : get Supplier detail by Id --supplier detail "+supplier.toString());
		return supplier;
	}
	public boolean deleteSupplierById(int supplierId) {
		log.info("SupplierDAOImpl : delete Supplier detail by Id --getSession ");
		Session session = sessionFactory.getCurrentSession();
		
		log.info("SupplierDAOImpl : deleteSupplierById -- load supplier record --"+supplierId);
		Object persistenceInstance = session.load(Supplier.class, supplierId);
		
		log.info("SupplierDAOImpl : deleteSupplierById -- delete selected supplier --"+supplierId);
		if(persistenceInstance !=null){
			
			session.delete(persistenceInstance);
			log.info("SupplierDAOImpl :  -- User deleted Successfully -- supplierid --"+supplierId);
			System.out.println("User deleted Successfully");
		}
		
		return true;
	}
	public List<Supplier> getAllSuppliers() {

		log.info("SupplierDAOImpl : getAll the suppliers");
		List<Supplier> supplier = sessionFactory.getCurrentSession().createQuery("from Supplier", Supplier.class).list();
		return supplier;
	}

	

	

}