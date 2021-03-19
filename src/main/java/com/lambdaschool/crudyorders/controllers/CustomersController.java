package com.lambdaschool.crudyorders.controllers;


import com.lambdaschool.crudyorders.models.Customer;
import com.lambdaschool.crudyorders.services.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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

    /*
        After generating a new primary key (long), saves a complete customer record including orders to the database
        <br> Example: <a href="http://localhost:2019/customers/customer">POST http://localhost:2019/customers/customer</a>

        @param newCustomer A complete customer record including orders. The assigned agent must already exist

        @return No Body, at status of CREATED, and in the location header a link to the newly created customer record
        including the new customer record's primary key

        @see CustomersService#save(Customer) CustomersService.save(Customer)
    */
    @PostMapping(value = "/customer",
        consumes = {"application/json"},
        produces = {"application/json"})
    public ResponseEntity<?> addNewCustomer(
        @Valid
        @RequestBody
            Customer newCustomer
    ){

        newCustomer.setCustcode(0);
        newCustomer = customersService.save(newCustomer);

        //set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newCustomerURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{custid}")
            .buildAndExpand((newCustomer.getCustcode()))
            .toUri();
        responseHeaders.setLocation(newCustomerURI);
        return new ResponseEntity<>(newCustomer,
            responseHeaders,
            HttpStatus.CREATED);
    }

    /*
        Given a complete Customer Object
        Given the customer primary key is in the customer table
        replace the Customer record and orders referenced by this customer
        <br> Example: <a href="http://localhost:2019/customers/customer/5">
                                PUT http://localhost:2019/customers/customer/5</a>

        @param updateCustomer   A complete customer including all orders to be used to replace the customer
        @param custid           The primary key of the customer you wish to replace

        @return status of ok

        @see CustomersService#save(Customer) CustomersService.save(Customer)
     */
    @PutMapping(value = "/customer/{custid}",
        consumes = {"application/json"},
        produces = {"application/json"})
    public ResponseEntity<?> replaceCustomer(
        @RequestBody
            Customer updateCustomer,
        @PathVariable
            long custid
    ){

        updateCustomer.setCustcode(custid);
        updateCustomer = customersService.save(updateCustomer);

        return new ResponseEntity<>(updateCustomer, HttpStatus.OK);
    }

    /*
        Updates the customer record associated with the given id with the provided data.
        Only the provided fields are affected
        Orders are added to the customer record. Deleting, modifying orders are different endpoints
        <br> Example: <a href="http://localhost:2019/customers/customer/5">
                                PATCH http://localhost:2019/customers/customer/5</a>

        @param updateCustomer   An object containing values for just the fields that are being updated.
                                All other fields are left NULL
        @param custid           The primary key of the customer you wish to update
        @return                 A status of OK
        @see CustomersService#update(Customer, long)    CustomersService.update(Customer, long)
     */
    @PatchMapping(value = "/customer/{custid}",
        consumes = {"application/json"},
        produces = {"application/json"})
    public ResponseEntity<?> updateCustomer(
        @RequestBody
            Customer updateCustomer,
        @PathVariable
            long custid
    ){
        customersService.update(updateCustomer, custid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    /*
        Delete the given customer record with its associated orders
        <br> Example: <a href="http://localhost:2019/customers/customer/5">
                               DELETE http://localhost:2019/customers/customer/5</a>

        @param custid The primary key (long) of the customer to delete
        @return No body is returned. A status of OK is returned if the deletion is successful
        @see CustomersService#delete(long)  CustomersService.delete(long)
     */

    @DeleteMapping(value = "/customer/{custid}")
    public ResponseEntity<?> deleteCustomerById(
        @PathVariable
            long custid
    ){
        customersService.delete(custid);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
