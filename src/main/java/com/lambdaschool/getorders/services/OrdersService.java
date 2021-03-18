package com.lambdaschool.getorders.services;

import com.lambdaschool.getorders.models.Order;

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
}
