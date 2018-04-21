package com.niit.shoppingcart.dao;

import java.util.List;

import com.niit.shoppingcart.domain.Payment;

public interface PaymentDAO {
	public boolean savePaymentInfo(Payment payment);

	public Payment getPaymentInfo(int paymentId);
	
	public List<Payment> getUserPaymentInfo(String emailId);
	
	public List<Payment> getUserCardPaymentInfo(String emailId);
	
}

