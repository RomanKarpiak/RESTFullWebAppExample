package com.roman.controllers;


import com.fasterxml.jackson.annotation.JsonView;
import com.roman.dao.CustomerDAO;
import com.roman.entity.Customer;
import com.roman.views.Views;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/customers", produces = {MediaType.APPLICATION_JSON_VALUE})
public class CustomerController {

    private final CustomerDAO customerDAO;

    @Autowired
    public CustomerController(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @GetMapping()
    @JsonView(Views.Private.class)
    public ResponseEntity<List<Customer>> getListCustomers() {
        List<Customer> customers = customerDAO.findAll();
        return !customers.isEmpty()
                ? new ResponseEntity<>(customers,HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/get-customer/{id}")
    @JsonView(Views.Private.class)
    public ResponseEntity<Customer> getCustomerById(@PathVariable String id) {
        Customer customer = customerDAO.findById(Long.parseLong(id));
        return customer.getId() != null ?
                new ResponseEntity<>(customer, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/create-customer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        customerDAO.create(customer);
        return new ResponseEntity<>(customer,HttpStatus.CREATED);

    }

    @PutMapping(value = "/update-customer/{id}")
    public Customer update(@PathVariable String id, @RequestBody Customer newCustomer) {
        Customer customer = customerDAO.findById(Long.parseLong(id));
        BeanUtils.copyProperties(newCustomer, customer, "id");
        customerDAO.update(customer);
        return newCustomer;
    }

    @DeleteMapping("/delete-customer/{id}")
    public void delete(@PathVariable String id, Customer customer) {
        customerDAO.delete(customer);
    }

}
