package com.roman.service.Impl;

import com.roman.dao.CrudDAO;
import com.roman.entity.Product;
import com.roman.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final CrudDAO<Product, Long> productDAO;

    @Autowired
    public ProductServiceImpl(CrudDAO<Product, Long> productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public void create(Product product) {
        productDAO.create(product);
    }

    @Override
    public Product findById(Long id) {
        return productDAO.findById(id);
    }

    @Override
    public void update(Product product) {
        productDAO.update(product);
    }

    @Override
    public void delete(Product product) {
        productDAO.delete(product);
    }

    @Override
    public List<Product> findAll() {
        return productDAO.findAll();
    }
}
