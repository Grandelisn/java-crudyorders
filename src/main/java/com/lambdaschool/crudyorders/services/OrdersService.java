package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Order;

import java.util.List;

//The service that works with the Orders model
public interface OrdersService {

    //A list of all orders with advanceamounts greater than 0.0
    //@return List of all orders with advanceamounts greater than 0.0 - Empty list if none found.
    List<Order> findOrdersWithAdvanceAmount();

    //Returns the order with the given primary key
    //@param id The primary key (long) of the order you seek
    //@return The given order or throws an exception if not found
    Order findOrdersById(long id);

    /*
        Given a complete order object, save the order in the database
        If a primary key is provided, the record is completely replaced
        If no primary key is provided, one is automatically generated and the record is added to the database

        @param order    The order object to be saved
        @return         The save order object including any automatically generated fields
     */
    Order save(Order order);

    //Deletes the order record from the database based off of the provided primary key
    //@param id The primary key (long) of the order to delete
    void delete(long id);

}
