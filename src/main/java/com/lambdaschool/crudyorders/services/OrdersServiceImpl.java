package com.lambdaschool.crudyorders.services;

import com.lambdaschool.crudyorders.models.Order;
import com.lambdaschool.crudyorders.models.Payment;
import com.lambdaschool.crudyorders.repositories.CustomersRepository;
import com.lambdaschool.crudyorders.repositories.OrdersRepository;
import com.lambdaschool.crudyorders.repositories.PaymentRepository;
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

    //Connects this service to the payments table
    @Autowired
    private PaymentRepository payrepos;

    //Connects this service to the payments table
    @Autowired
    private CustomersRepository custrepos;

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
    @Transactional
    @Override
    public Order save(Order order){

        Order newOrder = new Order();

        if(order.getOrdnum() != 0){
            ordersrepos.findById(order.getOrdnum())
                .orElseThrow(()-> new EntityNotFoundException("Order " + order.getOrdnum() + " Not Found"));
            newOrder.setOrdnum(order.getOrdnum());
        }

        newOrder.setOrdamount(order.getOrdamount());
        newOrder.setAdvanceamount(order.getAdvanceamount());
        newOrder.setOrderdescription(order.getOrderdescription());
        newOrder.setCustomer(custrepos.findById(order.getCustomer()
        .getCustcode())
        .orElseThrow(()-> new EntityNotFoundException("Customer " + order.getCustomer()
            .getCustcode() + " Not Found")));

        newOrder.getPayments().clear();
        //payments must already exist
        for(Payment p : order.getPayments()){
            Payment newPayment = payrepos.findById(p.getPaymentid())
                .orElseThrow(() -> new EntityNotFoundException("Payment " + p.getPaymentid() + " Not Found"));
            newOrder.getPayments().add(newPayment);
        }
        return ordersrepos.save(newOrder);
    }

    @Override
    public void delete(long id){
        if(ordersrepos.findById(id).isPresent()){
            ordersrepos.deleteById(id);
        }else{
            throw new EntityNotFoundException("Order " + id + " Not Found");
        }
    }
}
