package com.roman.controllers.Impl;

import com.roman.controllers.ProductController;
import com.roman.entity.Product;
import com.roman.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductControllerImpl implements ProductController {

    private final ShopService<Product> service;

    @Autowired
    public ProductControllerImpl(ShopService<Product> service) {
        this.service = service;
    }

    @Override
    public List<Product> findAll() {
        return service.findAll();
    }

    @Override
    public Product findById(Long id) {
        return service.findById(id);

    }

    @Override
    public Product create(Product product) {
        service.create(product);
        return product;

    }

    @Override
    public Product update(Product newProduct) {
        Product product = service.findById(newProduct.getId());
        service.update(product);
        return newProduct;
    }

    @Override
    public void deleteById(Long id) {
        Product product = service.findById(id);
        service.delete(product);
    }
}
