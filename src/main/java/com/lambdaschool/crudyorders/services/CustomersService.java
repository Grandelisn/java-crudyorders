package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Customer;

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

    /*
        Given a complete customer object, save the customer in the database
        If a primary key is provided, the record is completely replaced
        If no primary key is provided, one is automatically generated and the record is added the database

        @param customer The customer object to be saved
        @return The save customer object including any automatically generated fields
     */
    Customer save(Customer customer);

    /*
        Updates the provided fields in the customer record referenced buy the primary
        <p>
        Regarding orders, this prices only allows adding order. deleting and editing those lists
            is done through separate endpoints.

        @param customer     Just the customer fields to be updated
        @param id           The primary key (long) of the customer to update
        @return             The complete customer object that was updated
     */
    Customer update(Customer customer, long id);

    //Deletes the customer record and their orders from the database based off of the provided primary key
    //@param id The primary key (long) of the customer to delete
    void delete(long id);
}
