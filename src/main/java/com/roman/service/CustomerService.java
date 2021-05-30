package com.roman.service;

import com.roman.entity.Customer;

import java.util.List;

public interface CustomerService {

    void create(Customer customer);

    Customer findById(Long id);

    void update(Customer customer);

    void delete(Customer customer);

    List<Customer> findAll();
}
