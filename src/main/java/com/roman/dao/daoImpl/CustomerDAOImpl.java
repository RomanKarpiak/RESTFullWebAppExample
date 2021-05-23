package com.roman.dao.daoImpl;

import com.roman.dao.CustomerDAO;
import com.roman.entity.Customer;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CustomerDAOImpl implements CustomerDAO {

    @Autowired
    private SessionFactory factory;


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
            System.out.println("Data base is empty!");
            return null;
        }
    }
}
