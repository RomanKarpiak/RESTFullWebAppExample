package com.roman.dao.daoImpl;

import com.roman.dao.ProductDAO;
import com.roman.entity.Product;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductDAOImpl implements ProductDAO {

    @Autowired
    private SessionFactory factory;

    @Override
    public void create(Product product) {
        factory.getCurrentSession().save(product);
    }

    @Override
    public Product findById(Long id) {
        Product result = factory.getCurrentSession().get(Product.class, id);
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
            System.out.println("Data base is empty!");
            return null;
        }
    }

}
