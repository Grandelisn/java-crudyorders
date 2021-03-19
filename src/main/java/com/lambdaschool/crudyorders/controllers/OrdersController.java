package com.lambdaschool.crudyorders.controllers;

import com.lambdaschool.crudyorders.models.Order;
import com.lambdaschool.crudyorders.services.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

//The entry point for clients to accesss Orders data
@RestController
@RequestMapping("/orders")
public class OrdersController {

    //The entry point for orders to access Order data
    @Autowired
    OrdersService ordersService;

    /*
        Returns all orders with their customers that have an advancemount greater than 0
        <br>Example: <a href="http://localhost:2019/orders/advanceamount">http://localhost:2019/orders/advanceamount</a>

        @return JSON List of all orders with their customers that have an advanceamount greater than 0
     */
    @GetMapping(value = "/advanceamount")
    public ResponseEntity<?> getOrdersWithAdvanceAmount(){
        List<Order> myOrderList = ordersService.findOrdersWithAdvanceAmount();
        return new ResponseEntity<>(myOrderList,
            HttpStatus.OK);
    }

    /*
        Returns a single order based off of an ordernum
        <br>Example: <a href="http://localhost:2019/orders/order/50">http://localhost:2019/orders/order/50</a>

        @param ordernum The ordernum for the order you seek
        @return JSON of the order you seek with a status of OK
     */
    @GetMapping(value = "/order/{ordernum}", produces = {"application/json"})
    public ResponseEntity<?> getOrderByNumber(@PathVariable long ordernum){
        Order o = ordersService.findOrdersById(ordernum);
        return new ResponseEntity<>(o, HttpStatus.OK);
    }

    /*
        After generating a new primary key(long), saves a complete order record to the database
        <br>Example: <a href="http://localhost:2019/orders/order">POST http://localhost:2019/orders/order</a>

        @param newOrder     A complete new order record to be added to the database. Customer must already exist
        @return No body, at status of CREATED, and in the location header a link to the newly created order record
                including the new order record's primary key
        @see OrdersService#save(Order)  OrdersService,save(Order)
     */
    @PostMapping(value = "/order",
        consumes = {"application/json"},
        produces = {"application/json"})
    public ResponseEntity<?> addNewOrder(
        @Valid
        @RequestBody
            Order newOrder
    ){
        newOrder.setOrdnum(0);
        newOrder = ordersService.save(newOrder);

        //set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newOrderURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{ordernum}")
            .buildAndExpand(newOrder.getOrdnum())
            .toUri();
        responseHeaders.setLocation(newOrderURI);

        return new ResponseEntity<>(null,
                responseHeaders,
                HttpStatus.CREATED);
    }

    /*
        Given a complete Order Object
        Given the order primary key is in the order table,
        replace the order record referenced by this order id
        <br>Example: <a href="http://localhost:2019/orders/order/19">PUT http://localhost:2019/orders/order/19</a>

        @param updateOrder          A complete order record to replace the one targeted
        @param id                   The primary key (long) of the Order to replaced
        @return A status code OK
        @see OrdersService#save(Order)  OrdersService.save(Order)
     */
    @PutMapping(value = "/order/{id}",
        consumes = {"application/json"},
        produces = {"application/json"})
    public ResponseEntity<?> updateOrder(
        @RequestBody
            Order updateOrder,
        @PathVariable
            long id
    ){

        updateOrder.setOrdnum(id);
        ordersService.save(updateOrder);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*
        Delete the given order record
        <br>Example: <a href="http://localhost:2019/orders/order/19">DELETE http://localhost:2019/orders/order/19</a>

        @param id The primary key of the order to delete
        @return No body is returned. A status of OK is returned if the deletion is successful
        @see OrdersService#delete(long)     OrdersService.delete(long)
     */
    @DeleteMapping(value = "/order/{id}")
    public ResponseEntity<?> deleteCustomerById(
        @PathVariable
            long id
    ){

        ordersService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
