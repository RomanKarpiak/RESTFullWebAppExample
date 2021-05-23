package com.roman.controllers;

import com.fasterxml.jackson.annotation.JsonView;
import com.roman.dao.ProductDAO;
import com.roman.entity.Product;
import com.roman.views.Views;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/products", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ProductController {

    private final ProductDAO productDAO;

    @Autowired
    public ProductController(ProductDAO productDAO) {
        this.productDAO = productDAO;
    }

    @GetMapping()
    @JsonView(Views.Private.class)
    public ResponseEntity<List<Product>> getListProducts() {
        List<Product> products = productDAO.findAll();
        return !products.isEmpty()
                ? new ResponseEntity<>(products, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/get-product/{id}")
    @JsonView(Views.Private.class)
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Product product = productDAO.findById(Long.parseLong(id));
        return product.getId() != null ?
                new ResponseEntity<>(product, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value = "/create-product")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        productDAO.create(product);
        return new ResponseEntity<>(product, HttpStatus.CREATED);

    }

    @PutMapping(value = "/update-product/{id}")
    public ResponseEntity<Product> update(@PathVariable String id, @RequestBody Product newProduct) {
        Product product = productDAO.findById(Long.parseLong(id));
        if (product.getId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
        BeanUtils.copyProperties(newProduct, product, "id");
        productDAO.update(product);
        return new ResponseEntity<>(newProduct, HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping("/delete-product/{id}")
    public ResponseEntity<Long> delete(@PathVariable Long id) {
        Product product = productDAO.findById(id);
        if (product.getId() == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productDAO.delete(product);
        return new ResponseEntity<>(id, HttpStatus.OK);
    }
}
