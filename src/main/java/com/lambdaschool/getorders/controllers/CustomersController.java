package com.lambdaschool.getorders.controllers;


import com.lambdaschool.getorders.models.Customer;
import com.lambdaschool.getorders.services.CustomersService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomersController {

    //Using the customers service to process customer data.
    @Autowired
    private CustomersService customersService;

    /*
        Returns a list of all Customers with their orders
        <br>Example: <a href="http://localhost:2019/customers/orders">http://localhost:2019/customers/orders</a>

        @return JSON list of all Customers with their orders with a status of OK
        @see CustomersService#findAllCustomers() CustomersService.findAllCustomer()
     */
    @GetMapping(value = "/orders", produces = {"application/json"})
    public ResponseEntity<?> listAllCustomers(){
        List<Customer> myCustomers = customersService.findAllCustomers();
        return new ResponseEntity<>(myCustomers, HttpStatus.OK);
    }

    /*
        Returns a single customer based off a customer id number
        <br> Example: <a href="http://localhost:2019/customers/customer/5">http://localhost:2019/customers/customer/5</a>

        @param custid The primary key number of the customer you seek
        @return JSON of the customer you seek with a status of OK
        @see CustomersService#findCustomersById(long) CustomersService.findCustomersById(long)
     */
    @GetMapping(value = "/customer/{custid}", produces = {"application/json"})
    public ResponseEntity<?> getCustomerById(@PathVariable long custid){
       Customer c = customersService.findCustomersById(custid);
       return new ResponseEntity<>(c, HttpStatus.OK);
    }

    /*
        Returns a list of customers whose name containes the given substring
        <br> Example: <a href="http://localhost:2019/customers/namelike/sun">http://localhost:2019/customers/namelike/sun</a>

        @param custname The substring in the customers' names that you seek
        @return JSON list of the customers found with the given substring in their name with a status of OK
        @see CustomersService#findByCustomerName(String) CustomersService.findByCustomerName(String)
     */
    @GetMapping(value = "/namelike/{custname}", produces = {"application/json"})
    public ResponseEntity<?> findCustomerByName(@PathVariable
                                                String custname){
        List<Customer> myCustomerList = customersService.findByCustomerName(custname);
        return new ResponseEntity<>(myCustomerList, HttpStatus.OK);
    }
}
