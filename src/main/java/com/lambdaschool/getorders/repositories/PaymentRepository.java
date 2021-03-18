package com.lambdaschool.getorders.repositories;

import com.lambdaschool.getorders.models.Payment;
import org.springframework.data.repository.CrudRepository;

public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
