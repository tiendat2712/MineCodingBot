package dao;

import persistence.Customer;

public interface CustomerDao {

	// Update a new customer into databse
	void save(Customer customer);
	
	// Check the login of the customer account
	Customer login(String email, String password);
	
}
