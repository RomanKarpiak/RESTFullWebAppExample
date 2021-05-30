package com.roman.controllers.Impl;


import com.roman.controllers.CustomerController;
import com.roman.entity.Customer;
import com.roman.exceptions.CustomerNotFoundException;
import com.roman.service.CustomerService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerControllerImpl implements CustomerController {

    private final CustomerService service;

    @Autowired
    public CustomerControllerImpl(CustomerService service) {
        this.service = service;
    }

    @Override
    public List<Customer> findAll() {
        return service.findAll();
    }

    @Override
    public Customer findById(Long id) {
        Customer customer = service.findById(id);
        if (customer.getId() == null) {
            throw new CustomerNotFoundException(id);
        }
        return customer;
    }

    @Override
    public Customer create(Customer customer) {
        service.create(customer);
        return customer;
    }

    @Override
    public Customer update(Customer newCustomer) {
        Customer customer = service.findById(newCustomer.getId());
        if (customer.getId() == null) {
            throw new CustomerNotFoundException(newCustomer.getId());
        }
        BeanUtils.copyProperties(newCustomer, customer, "id");
        service.update(customer);
        return newCustomer;
    }

    @Override
    public void deleteById(Long id) {
        Customer customer = service.findById(id);
        service.delete(customer);
    }

}
