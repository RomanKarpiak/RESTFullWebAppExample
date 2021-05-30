package com.roman.controllers.Impl;

import com.roman.controllers.ProductController;
import com.roman.entity.Product;
import com.roman.exceptions.ProductNotFoundException;
import com.roman.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductControllerImpl implements ProductController {

    private final ProductService service;

    @Autowired
    public ProductControllerImpl(ProductService service) {
        this.service = service;
    }

    @Override
    public List<Product> findAll() {
        return service.findAll();
    }

    @Override
    public Product findById(Long id) {
        Product product = service.findById(id);
        if (product.getId() == null) {
            throw new ProductNotFoundException(id);
        }
        return product;

    }

    @Override
    public Product create(Product product) {
        service.create(product);
        return product;

    }

    @Override
    public Product update(Product newProduct) {
        Product product = service.findById(newProduct.getId());
        if (product.getId() == null) {
            throw new ProductNotFoundException(newProduct.getId());
        }
        BeanUtils.copyProperties(newProduct, product, "id");
        service.update(product);
        return newProduct;
    }

    @Override
    public void deleteById(Long id) {
        Product product = service.findById(id);
        service.delete(product);
    }
}
