package com.roman.dao;

import com.roman.entity.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO extends CrudDAO<Product, Long> {

}
