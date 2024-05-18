package view;

import persistence.Customer;
import service.CustomerService;
import service.CustomerServiceImpl;

public class Ex04CustomerView {

	private static CustomerService customerService;
	
	static {
		customerService = new CustomerServiceImpl();
	}
	
	public static void main(String[] args) {
//		Customer customerToBeInserted1st = 
//				new Customer(6, "Ronaldo", "cr7@gmail.com", "Portugal", "011111111", "ghjk1234");
//		
//		Customer customerToBeInserted2nd = 
//				new Customer(7, "Messi", "m10@gmail.com", "Argentinal", "02222222", "abcd6789");
//	    
//		customerService.save(customerToBeInserted1st);
//		customerService.save(customerToBeInserted2nd);
		
		Customer customer = customerService.login("cr7@gmail.com", "ghjk1234");
		
		if(customer == null) {
			System.out.println("Email or password is incorrect!!");
		} else {
			System.out.println("Logged in successfully!!");
		}
		
		System.out.println("======= Finished =======");
	
	}
}
