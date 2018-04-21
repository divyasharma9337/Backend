package com.niit.shoppingcart.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.niit.shoppingcart.dao.AddressDAO;
import com.niit.shoppingcart.dao.CartDAO;
import com.niit.shoppingcart.dao.CategoryDAO;
import com.niit.shoppingcart.dao.OrderDAO;
import com.niit.shoppingcart.dao.PaymentDAO;
import com.niit.shoppingcart.dao.ProductDAO;
import com.niit.shoppingcart.dao.SupplierDAO;
import com.niit.shoppingcart.dao.UserDAO;
import com.niit.shoppingcart.daoimpl.AddressDAOImpl;
import com.niit.shoppingcart.daoimpl.CartDAOImpl;
import com.niit.shoppingcart.daoimpl.CategoryDAOImpl;
import com.niit.shoppingcart.daoimpl.OrderDAOImpl;
import com.niit.shoppingcart.daoimpl.PaymentDAOImpl;
import com.niit.shoppingcart.daoimpl.ProductDAOImpl;
import com.niit.shoppingcart.daoimpl.SupplierDAOImpl;
import com.niit.shoppingcart.daoimpl.UserDAOImpl;
import com.niit.shoppingcart.domain.Category;
import com.niit.shoppingcart.domain.Orders;
import com.niit.shoppingcart.domain.Payment;
import com.niit.shoppingcart.domain.Product;
import com.niit.shoppingcart.domain.Supplier;
import com.niit.shoppingcart.domain.User;
import com.niit.shoppingcart.domain.Address;
import com.niit.shoppingcart.domain.Cart;

@Configuration
@ComponentScan("com.niit.shoppingcart")
@EnableTransactionManagement
public class ApplicationContextConfig {
	@Autowired
	@Bean(name = "dataSource")
	public DataSource getH2DataSource() {

		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:h2:tcp://localhost/~/onlineshoppingdb");
		dataSource.setDriverClassName("org.h2.Driver");
		dataSource.setUsername("sa");
		dataSource.setPassword("sa");
		return dataSource;
	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();

		properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
		properties.put("hibernate.show_sql", "true");
		properties.put("hibernate.hbm2ddl.auto", "update");
		properties.put("hibernate.format_sql", "true");
		System.out.println("Setting Hibernate Properties");
		return properties;
	}

	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {

		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
		sessionBuilder.addProperties(getHibernateProperties());
		sessionBuilder.addAnnotatedClass(User.class);
		sessionBuilder.addAnnotatedClass(Category.class);
		sessionBuilder.addAnnotatedClass(Product.class);
		sessionBuilder.addAnnotatedClass(Supplier.class);
		sessionBuilder.addAnnotatedClass(Cart.class);
		sessionBuilder.addAnnotatedClass(Address.class);
		sessionBuilder.addAnnotatedClass(Orders.class);
		sessionBuilder.addAnnotatedClass(Payment.class);
		sessionBuilder.scanPackages("com.niit.shoppingcart");

		return sessionBuilder.buildSessionFactory();
	}

	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager(sessionFactory);

		return transactionManager;
	}

	@Autowired
	@Bean(name = "userDAO")
	public UserDAO getUserDAO(SessionFactory sessionFactory) {
		return new UserDAOImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "categoryDAO")
	public CategoryDAO getCategoryDAO(SessionFactory sessionFactory) {
		return new CategoryDAOImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "productDAO")
	public ProductDAO getPoductDAO(SessionFactory sessionFactory) {
		return new ProductDAOImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "supplierDAO")
	public SupplierDAO getSupplierDAO(SessionFactory sessionFactory) {
		return new SupplierDAOImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "cartDAO")
	public CartDAO getCartDAO(SessionFactory sessionFactory) {
		return new CartDAOImpl(sessionFactory);
	}
	@Autowired
	@Bean(name = "addressDAO")
	public AddressDAO getAddressDAO(SessionFactory sessionFactory) {
		return new AddressDAOImpl(sessionFactory);
	}

	@Autowired
	@Bean(name = "orderDAO")
	public OrderDAO getOrderDAO(SessionFactory sessionFactory) {
		return new OrderDAOImpl(sessionFactory);
	}
	@Autowired
	@Bean(name = "PaymentDAO")
	public PaymentDAO getPaymentDAO(SessionFactory sessionFactory) {
		return new PaymentDAOImpl(sessionFactory);
	}


}
