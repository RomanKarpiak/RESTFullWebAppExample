package com.roman.controllers.Impl;


import com.roman.controllers.CustomerController;
import com.roman.entity.Customer;
import com.roman.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerControllerImpl implements CustomerController {

    private final ShopService<Customer> service;

    @Autowired
    public CustomerControllerImpl(ShopService<Customer> service) {
        this.service = service;
    }

    @Override
    public List<Customer> findAll() {
        return service.findAll();
    }

    @Override
    public Customer findById(Long id) {
        return service.findById(id);
    }

    @Override
    public Customer create(Customer customer) {
        service.create(customer);
        return customer;
    }

    @Override
    public Customer update(Customer newCustomer) {
        Customer customer = service.findById(newCustomer.getId());
        service.update(customer);
        return newCustomer;
    }

    @Override
    public void deleteById(Long id) {
        Customer customer = service.findById(id);
        service.delete(customer);
    }

}
