package org.example.service;

import org.example.model.Customer;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class CustomerService {
    private static CustomerService customerService;
    private final Map<String,Customer> customers;
    private CustomerService(){
        customers=new HashMap<>();
    }
    //static reference for CustomerService class
    public static CustomerService getInstance(){
        if(customerService==null){
            customerService=new CustomerService();
        }
        return  customerService;
    }
    //method to add Customers
    public void addCustomer(String firstName, String lastName, String email){
        try {
            Customer customer=new Customer(firstName,lastName,email);
            customers.put(email,customer);
        }catch(IllegalArgumentException e){
            System.out.println("Please enter the right email: name.@domain.com");
        }
    }
    //method to retrieve customer
    public Customer getCustomer(String customerEmail){
        return customers.getOrDefault(customerEmail,null);
    }
    //method to retrieve all customers
    public Collection<Customer> getAllCustomers(){
        return customers.values();
    }
}
