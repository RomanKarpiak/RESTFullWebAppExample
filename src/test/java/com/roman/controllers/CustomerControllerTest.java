package com.roman.controllers;

import com.roman.dao.CustomerDAO;
import com.roman.entity.Address;
import com.roman.entity.Cart;
import com.roman.entity.Customer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class CustomerControllerTest {

    @InjectMocks
    CustomerController controller;
    @Mock
    CustomerDAO customerDAO;

    @Test
    public void getCustomerById() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Alex");
        customer.setPhone("111");
        customer.setEmail("alex@sd");
        customer.setCart(new Cart());
        customer.setAddress(new Address("Russia","Moscow","New"));

        when(customerDAO.findById(1L)).thenReturn(customer);
        controller.createCustomer(customer);

        ResponseEntity<Customer> responseEntity = controller.getCustomerById(1L);
        Assertions.assertEquals(200,responseEntity.getStatusCodeValue());
        Assertions.assertEquals("Alex",responseEntity.getBody().getName());
    }

    @Test
    void getList() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Alex");
        customer.setPhone("111");
        customer.setEmail("alex@sd");
        customer.setCart(new Cart());
        customer.setAddress(new Address("Russia","Moscow","New"));
        Customer customer1 = new Customer();
        customer.setId(2L);
        customer.setName("Sergey");
        customer.setPhone("222");
        customer.setEmail("seerg@sd");
        customer.setCart(new Cart());
        customer.setAddress(new Address("Russia","Peterburg","Old"));
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        customers.add(customer1);
        controller.createCustomer(customer);
        controller.createCustomer(customer1);
        when(customerDAO.findAll()).thenReturn(customers);

        ResponseEntity<List<Customer>> responseEntity = controller.getListCustomers();
        Assertions.assertEquals(200,responseEntity.getStatusCodeValue());
        Assertions.assertEquals(customers.size(),responseEntity.getBody().size());






    }
}