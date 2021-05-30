package com.roman.service.Impl;

import com.roman.dao.CrudDAO;
import com.roman.entity.Customer;
import com.roman.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CrudDAO<Customer, Long> customerDAO;

    @Autowired
    public CustomerServiceImpl(CrudDAO<Customer, Long> customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public void create(Customer customer) {
        customerDAO.create(customer);
    }

    @Override
    public Customer findById(Long id) {
        return customerDAO.findById(id);
    }

    @Override
    public void update(Customer customer) {
        customerDAO.update(customer);
    }

    @Override
    public void delete(Customer customer) {
        customerDAO.delete(customer);
    }

    @Override
    public List<Customer> findAll() {
        return customerDAO.findAll();
    }
}
