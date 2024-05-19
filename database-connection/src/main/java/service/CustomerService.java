package service;

import persistence.Customer;

public interface CustomerService {
 
	void save(Customer customer);
	
	Customer login(String email, String password);
}
