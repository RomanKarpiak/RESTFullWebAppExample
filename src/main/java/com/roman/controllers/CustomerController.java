package com.roman.controllers;


import com.roman.dao.CrudDAO;
import com.roman.entity.Customer;
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

    private CrudDAO<Customer,Long> customerDAO;

    public CustomerController() {
    }

    @Autowired
    public CustomerController(CrudDAO<Customer,Long> customerDAO) {
        this.customerDAO = customerDAO;
    }

    @GetMapping()
    public ResponseEntity<List<Customer>> getListCustomers() {
        List<Customer> customers = customerDAO.findAll();
        return !customers.isEmpty()
                ? new ResponseEntity<>(customers, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/get-customer/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerDAO.findById(id);
        return customer.getId() != null ?
                new ResponseEntity<>(customer, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/create-customer")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        customerDAO.create(customer);
        return new ResponseEntity<>(customer, HttpStatus.CREATED);

    }

    @PutMapping(value = "/update-customer/{id}")
    public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody Customer newCustomer) {
        Customer customer = customerDAO.findById(id);
        if (customer.getId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        BeanUtils.copyProperties(newCustomer, customer, "id");
        customerDAO.update(customer);
        return new ResponseEntity<>(newCustomer, HttpStatus.OK);
    }

    @DeleteMapping("/delete-customer/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        Customer customer = customerDAO.findById(id);
        if (customer.getId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        customerDAO.delete(customer);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }

}
