package com.roman.service;

import com.roman.entity.Product;

import java.util.List;

public interface ProductService {

    void create(Product product);

    Product findById(Long id);

    void update(Product product);

    void delete(Product product);

    List<Product> findAll();
}
