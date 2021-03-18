package com.lambdaschool.getorders.repositories;

import com.lambdaschool.getorders.models.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

//The CRUD Repository connecting Customer to rest of the application
public interface CustomersRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByCustnameContainingIgnoreCase(String name);
}
