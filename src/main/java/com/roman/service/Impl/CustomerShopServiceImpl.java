package com.roman.service.Impl;

import com.roman.dao.CrudDAO;
import com.roman.entity.Customer;
import com.roman.exceptions.CustomerNotFoundException;
import com.roman.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class CustomerShopServiceImpl implements ShopService<Customer> {

    private final CrudDAO<Customer> customerDAO;

    @Autowired
    public CustomerShopServiceImpl(CrudDAO<Customer> customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public void create(Customer customer) {
        customerDAO.create(customer);
    }

    @Override
    public Customer findById(Long id) {
        if (customerDAO.findById(id).getId() == null) {
            throw new CustomerNotFoundException(id);
        }
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
