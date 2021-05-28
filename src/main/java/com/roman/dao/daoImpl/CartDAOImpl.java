package com.roman.dao.daoImpl;

import com.roman.dao.CrudDAO;
import com.roman.entity.Cart;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Transactional
@Repository
public class CartDAOImpl implements CrudDAO<Cart, Long> {
    @Autowired
    private SessionFactory factory;

    @Override
    public void create(Cart cart) {
        factory.getCurrentSession().save(cart);
    }

    @Override
    public Cart findById(Long id) {
        Cart cart = factory.getCurrentSession().get(Cart.class, id);
        return cart != null ? cart : new Cart();
    }

    @Override
    public void update(Cart cart) {
        factory.getCurrentSession().update(cart);
    }

    @Override
    public void delete(Cart cart) {
        factory.getCurrentSession().delete(cart);
    }

    @Override
    public List<Cart> findAll() {
        List<Cart> cartList = factory.getCurrentSession().createQuery("select c from Cart c", Cart.class).list();
        if (!cartList.isEmpty()) {
            return cartList;
        } else {
            System.out.println("Data base is empty!");
            return Collections.emptyList();
        }
    }
}
