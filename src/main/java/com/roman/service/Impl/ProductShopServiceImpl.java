package com.roman.service.Impl;

import com.roman.dao.CrudDAO;
import com.roman.entity.Product;
import com.roman.exceptions.ProductNotFoundException;
import com.roman.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ProductShopServiceImpl implements ShopService<Product> {

    private final CrudDAO<Product> productDAO;

    @Autowired
    public ProductShopServiceImpl(CrudDAO<Product> productDAO) {
        this.productDAO = productDAO;
    }

    @Override
    public void create(Product product) {
        productDAO.create(product);
    }

    @Override
    public Product findById(Long id) {
        if (productDAO.findById(id).getId() == null) {
            throw new ProductNotFoundException(id);
        }
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
