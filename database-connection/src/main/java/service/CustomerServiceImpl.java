package service;

import java.util.Objects;

import dao.CustomerDao;
import dao.JdbcCustomerDao;
import persistence.Customer;

public class CustomerServiceImpl implements CustomerService{

	private CustomerDao customerDao;
	
	public CustomerServiceImpl() {
		customerDao = new JdbcCustomerDao(); 
	}
	
	@Override
	public void save(Customer customer) {
		Objects.requireNonNull(customer);
		customerDao.save(customer);
	}
	
	@Override
	public Customer login(String email, String password) {
		Objects.requireNonNull(email, "email should not be null");
		Objects.requireNonNull(password, "password should not be null");
		return customerDao.login(email, password);
	}
	
}
