package com.lambdaschool.getorders.services;

import com.lambdaschool.getorders.models.Customer;

import java.util.List;

//The service that works with the Customer model
public interface CustomersService {

    //Returns a list of all Customers with their orders and agents
    //@return List of customers. If no customers, empty list
    List<Customer> findAllCustomers();

    //Returns a list of all Customers whose name contains the given substring
    //@param custname The substring (String) of the name you wish to search for
    //@return A list of all customers whoe name contains the given substring. If no matching customers, empty list.
    List<Customer> findByCustomerName(String custname);

    //Returns the customer with the given primary key
    //@param id The primary key (long) of the customer you seek
    //@return The given customer or throws an exception if not found
    Customer findCustomersById(long id);
}
