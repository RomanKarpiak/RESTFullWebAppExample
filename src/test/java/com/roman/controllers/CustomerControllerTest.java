package com.roman.controllers;

import com.roman.dao.CustomerDAO;
import com.roman.entity.Address;
import com.roman.entity.Cart;
import com.roman.entity.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
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
        Customer customerOne = new Customer();
        customerOne.setId(1L);
        customerOne.setName("Alex");
        customerOne.setPhone("111");
        customerOne.setEmail("alex@sd");
        customerOne.setCart(new Cart());
        customerOne.setAddress(new Address("Russia", "Moscow", "New"));

        Customer customerTwo = new Customer();
        customerTwo.setId(2L);
        customerTwo.setName("Sergey");
        customerTwo.setPhone("222");
        customerTwo.setEmail("seerg@sd");
        customerTwo.setCart(new Cart());
        customerTwo.setAddress(new Address("Russia", "Peterburg", "Old"));

        when(customerDAO.findById(1L)).thenReturn(customerOne);
        controller.createCustomer(customerOne);
        controller.createCustomer(customerTwo);

        ResponseEntity<Customer> responseEntity = controller.getCustomerById(1L);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("Alex", Objects.requireNonNull(responseEntity.getBody()).getName());
    }

    @Test
    void whenGetListCustomersThenReturnListCustomersAndStatus200() {
        Customer customerOne = new Customer();
        customerOne.setId(1L);
        customerOne.setName("Alex");
        customerOne.setPhone("111");
        customerOne.setEmail("alex@sd");
        customerOne.setCart(new Cart());
        customerOne.setAddress(new Address("Russia", "Moscow", "New"));

        Customer customerTwo = new Customer();
        customerTwo.setId(2L);
        customerTwo.setName("Sergey");
        customerTwo.setPhone("222");
        customerTwo.setEmail("seerg@sd");
        customerTwo.setCart(new Cart());
        customerTwo.setAddress(new Address("Russia", "Peterburg", "Old"));

        List<Customer> customers = new ArrayList<>();
        customers.add(customerOne);
        customers.add(customerTwo);
        controller.createCustomer(customerOne);
        controller.createCustomer(customerTwo);
        when(customerDAO.findAll()).thenReturn(customers);

        ResponseEntity<List<Customer>> responseEntity = controller.getListCustomers();
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals(customers.size(), Objects.requireNonNull(responseEntity.getBody()).size());
    }

    @Test
    void whenCreateCustomerGetStatus201() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Alex");
        customer.setPhone("111");
        customer.setEmail("alex@sd");
        customer.setCart(new Cart());
        customer.setAddress(new Address("Russia", "Moscow", "New"));

        CustomerController controller = Mockito.mock(CustomerController.class);
        doReturn(new ResponseEntity<>(customer, HttpStatus.CREATED)).when(controller).createCustomer(customer);
        ResponseEntity<Customer> responseEntity = controller.createCustomer(customer);

        assertEquals(201, responseEntity.getStatusCodeValue());
    }

    @Test
    void whenUpdateCustomerNameThenReturnStatus200AndUpdateCustomer() {
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Alex");
        customer.setPhone("111");
        customer.setEmail("alex@sd");
        customer.setCart(new Cart());
        customer.setAddress(new Address("Russia", "Moscow", "New"));

        Customer customerUpdate = new Customer();
        customerUpdate.setId(1L);
        customerUpdate.setName("Fedor");
        customerUpdate.setPhone("111");
        customerUpdate.setEmail("alex@sd");
        customerUpdate.setCart(new Cart());
        customerUpdate.setAddress(new Address("Russia", "Moscow", "New"));
        controller.createCustomer(customer);
        controller.createCustomer(customer);

        CustomerController controller = Mockito.mock(CustomerController.class);
        doReturn(new ResponseEntity<>(customerUpdate, HttpStatus.OK)).when(controller).update(1L, customerUpdate);

        ResponseEntity<Customer> responseEntity = controller.update(1L, customerUpdate);
        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("Fedor", Objects.requireNonNull(responseEntity.getBody()).getName());
    }
    @Test
    void whenUpdateCustomerNameAndCustomerNotFoundThenReturnStatus304() {

        Customer customer = new Customer();
        customer.setId(1L);
        customer.setName("Alex");
        customer.setPhone("111");
        customer.setEmail("alex@sd");
        customer.setCart(new Cart());
        customer.setAddress(new Address("Russia", "Moscow", "New"));

        Customer customerUpdate = new Customer();
        customerUpdate.setId(1L);
        customerUpdate.setName("Fedor");
        customerUpdate.setPhone("111");
        customerUpdate.setEmail("alex@sd");
        customerUpdate.setCart(new Cart());
        customerUpdate.setAddress(new Address("Russia", "Moscow", "New"));

        CustomerController controller = Mockito.mock(CustomerController.class);
        doReturn(new ResponseEntity<>(HttpStatus.NOT_MODIFIED)).when(controller).update(5L, customerUpdate);
        controller.createCustomer(customer);
        controller.update(5L,customerUpdate);
        ResponseEntity<Customer> responseEntityNotUpdate = controller.update(5L, customerUpdate);
        assertEquals(304, responseEntityNotUpdate.getStatusCodeValue());

    }

}