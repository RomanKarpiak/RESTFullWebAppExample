package com.roman.controllers;

import com.roman.entity.Product;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public interface ProductController {

    @GetMapping()
    List<Product> findAll();

    @GetMapping(value = "/{id}")
    Product findById(@PathVariable Long id);

    @PostMapping()
    Product create(@RequestBody Product product);

    @PutMapping()
    Product update(@RequestBody Product newProduct);

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    void deleteById(@PathVariable Long id);
}
