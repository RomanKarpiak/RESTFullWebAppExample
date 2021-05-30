package com.roman.dao.daoImpl;

import com.roman.dao.CrudDAO;
import com.roman.entity.Customer;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Repository
@Transactional
public class CustomerDAOImpl implements CrudDAO<Customer, Long> {


    private final SessionFactory factory;

    @Autowired
    public CustomerDAOImpl(SessionFactory factory) {
        this.factory = factory;
    }

    @Override
    public void create(Customer customer) {
        factory.getCurrentSession().save(customer);
    }

    @Override
    public Customer findById(Long id) {
        final Customer result = factory.getCurrentSession().get(Customer.class, id);
        return result != null ? result : new Customer();
    }

    @Override
    public void update(Customer customer) {
        factory.getCurrentSession().update(customer);
    }


    @Override
    public void delete(Customer customer) {
        factory.getCurrentSession().remove(customer);
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> customerList = factory.getCurrentSession().createQuery("select c from Customer c", Customer.class).list();
        if (!customerList.isEmpty()) {
            return customerList;
        } else {
            return Collections.emptyList();
        }
    }
}
