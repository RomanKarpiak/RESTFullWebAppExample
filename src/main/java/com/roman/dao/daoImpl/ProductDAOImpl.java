package com.roman.dao.daoImpl;

import com.roman.dao.CrudDAO;
import com.roman.entity.Product;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class ProductDAOImpl implements CrudDAO<Product> {

    private final SessionFactory factory;

    @Autowired
    public ProductDAOImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(Product product) {
        factory.getCurrentSession().save(product);
    }

    @Override
    public Product findById(Long id) {
        final Product result = factory.getCurrentSession().get(Product.class, id);
        return result != null ? result : new Product();

    }

    @Override
    public void update(Product product) {
        factory.getCurrentSession().update(product);
    }

    @Override
    public void delete(Product product) {
        factory.getCurrentSession().delete(product);
    }

    @Override
    public List<Product> findAll() {
        List<Product> productList = factory.getCurrentSession().createQuery("select p from Product p", Product.class).list();
        if (!productList.isEmpty()) {
            return productList;
        } else {
            return Collections.emptyList();
        }
    }

}
