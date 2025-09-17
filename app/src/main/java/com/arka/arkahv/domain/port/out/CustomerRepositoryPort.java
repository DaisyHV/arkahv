package com.arka.arkahv.domain.port.out;

import com.arka.arkahv.domain.model.Customer;

import java.util.List;

public interface CustomerRepositoryPort {

    List<Customer> findAll();
    Customer findById(int id);
    Customer save(Customer user);
    Customer update(Customer user);
    Customer delete(int id);

}
