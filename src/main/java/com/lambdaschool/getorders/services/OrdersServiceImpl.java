package com.lambdaschool.getorders.services;

import com.lambdaschool.getorders.models.Order;
import com.lambdaschool.getorders.repositories.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

//Implements the OrdersService Interface
@Transactional
@Service(value = "ordersService")
public class OrdersServiceImpl implements OrdersService{

    //Connects this service to the orders table
    @Autowired
    private OrdersRepository ordersrepos;

    @Override
    public List<Order> findOrdersWithAdvanceAmount(){
        return ordersrepos.findAllByAdvanceamountGreaterThan(0.00);
    }

    @Override
    public Order findOrdersById(long id)
        throws EntityNotFoundException {

        return ordersrepos.findById(id)
            .orElseThrow(() -> new EntityNotFoundException(("Order " + id + " Not Found")));
    }
}
