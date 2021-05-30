package com.roman.controllers;

import com.roman.entity.Customer;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public interface CustomerController {

    @PostMapping()
    Customer create(@RequestBody Customer customer);

    @PutMapping()
    Customer update(@RequestBody Customer newCustomer);

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long id);

    @GetMapping(value = "/{id}")
    Customer findById(@PathVariable Long id);

    @GetMapping()
    List<Customer> findAll();
}
