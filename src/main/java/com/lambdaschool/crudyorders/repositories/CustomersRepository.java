package com.lambdaschool.crudyorders.repositories;

import com.lambdaschool.crudyorders.models.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//The CRUD Repository connecting Customer to rest of the application
public interface CustomersRepository extends CrudRepository<Customer, Long> {

    /*
        List of customers whose name contains a given substring. Case insensitive search

        @param name The substring to search names for.
        @return List of customers whose name contains the given substring
     */
    List<Customer> findByCustnameContainingIgnoreCase(String name);

    /*
        Returns the first associated customer for the given agent. If no customers are associated with this agent, returns null

        @param agentid  The agent you are seeking a customer for
        @return         The first customer record who is associated with this agent
                        if no customers are associated with this agent, returns null
     */
    Customer findFirstByAgent_Agentcode(long agentid);
}
