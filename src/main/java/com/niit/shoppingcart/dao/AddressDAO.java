package com.niit.shoppingcart.dao;

import java.util.List;

import com.niit.shoppingcart.domain.Address;
public interface AddressDAO {
public boolean saveOrUpdate(Address address);
	
	public Address getAddressById(int addressId);
	
	public boolean deleteAddressById(int addressId);
	
	public List<Address> getAllAddressOfUser(String emailId);
	
	public Address getAddressOfUser(String emailId);
}
