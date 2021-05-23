package com.roman.dao;

import com.roman.entity.Customer;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDAO extends CrudDAO<Customer, Long> {

}

