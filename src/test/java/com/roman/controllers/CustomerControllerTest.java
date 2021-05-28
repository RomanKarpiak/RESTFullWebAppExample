package com.roman.controllers;


import com.roman.dao.CrudDAO;
import com.roman.entity.Address;
import com.roman.entity.Cart;
import com.roman.entity.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.*;

public class CustomerControllerTest {

    @Mock
    private CrudDAO<Customer,Long> customerDAO;
    @InjectMocks
    CustomerController controller = new CustomerController();


    private List<Customer> customers = new ArrayList<>();

    @Before
    public void initCustomers() {
        MockitoAnnotations.openMocks(this);
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
        customers.add(customerOne);
        customers.add(customerTwo);
    }

    @Test
    public void getListCustomersTest() {
        when(customerDAO.findAll()).thenReturn(customers);

        ReflectionTestUtils.setField(controller, "customerDAO", customerDAO);
        ExtendedModelMap uiModel = new ExtendedModelMap();
        uiModel.addAttribute("customers", controller.getListCustomers());
        ResponseEntity<List<Customer>> model = (ResponseEntity<List<Customer>>) uiModel.get("customers");

        Assert.assertEquals(200, model.getStatusCodeValue());
        Assert.assertEquals(2, Objects.requireNonNull(model.getBody()).size());
    }


    @Test()
    public void getCustomerByIdTest() {
        when(customerDAO.findById(2L)).thenReturn(customers.get(1));

        ReflectionTestUtils.setField(controller, "customerDAO", customerDAO);
        ExtendedModelMap uiModel = new ExtendedModelMap();
        uiModel.addAttribute("customer", controller.getCustomerById(2L));
        ResponseEntity<Customer> model = (ResponseEntity<Customer>) uiModel.get("customer");

        Assert.assertEquals(200, model.getStatusCodeValue());
        Assert.assertEquals("Sergey", Objects.requireNonNull(model.getBody()).getName());
    }

    @Test
    public void whenCreateCustomerGetStatus201() {
        Customer customer = new Customer();
        customer.setId(2L);
        customer.setName("Fedor");
        customer.setPhone("111");
        customer.setEmail("fedor@sd");
        customer.setCart(new Cart());
        customer.setAddress(new Address("Russia", "Moscow", "New"));

        doAnswer((Answer<Customer>) invocationOnMock -> {
            customers.add(customer);
            return customer;
        }).when(customerDAO).create(customer);

        ReflectionTestUtils.setField(controller, "customerDAO", customerDAO);
        ResponseEntity<Customer> responseEntity = controller.createCustomer(customer);

        Assert.assertEquals(201, responseEntity.getStatusCodeValue());
        Assert.assertEquals("Fedor", Objects.requireNonNull(responseEntity.getBody()).getName());
    }

    @Test
    public void whenUpdateCustomerNameThenReturnStatus200AndUpdateCustomer() {
        Customer updatedCustomer1 = new Customer();
        updatedCustomer1.setId(1L);
        updatedCustomer1.setName("Leonid");
        updatedCustomer1.setPhone("555555");
        updatedCustomer1.setEmail("leonid@sd");
        updatedCustomer1.setCart(new Cart());
        updatedCustomer1.setAddress(new Address("Russia", "Moscow", "New"));

        when(customerDAO.findById(1L)).thenReturn(customers.get(0));

        ReflectionTestUtils.setField(controller, "customerDAO", customerDAO);

        ResponseEntity<Customer> responseEntity = controller.update(1L, updatedCustomer1);
        Assert.assertEquals(200, responseEntity.getStatusCodeValue());
        Assert.assertEquals("Leonid", Objects.requireNonNull(responseEntity.getBody()).getName());
        Assert.assertEquals("leonid@sd", Objects.requireNonNull(responseEntity.getBody()).getEmail());
        Assert.assertEquals("555555", Objects.requireNonNull(responseEntity.getBody()).getPhone());
    }

    @Test
    public void whenDeleteCustomerThenCustomersSize1AndStatus200() {
        when(customerDAO.findById(1L)).thenReturn(customers.get(0));
        doAnswer((Answer<Long>) invocationOnMock -> {
            Customer customer = invocationOnMock.getArgument(0);
            customers.remove(0);
            return customer.getId();
        }).when(customerDAO).delete(customers.get(0));

        ReflectionTestUtils.setField(controller, "customerDAO", customerDAO);

        ResponseEntity<Long> responseEntity = controller.delete(1L);
        Assert.assertEquals(200, responseEntity.getStatusCodeValue());
        Assert.assertEquals(1L, Objects.requireNonNull(responseEntity.getBody()).longValue());
        Assert.assertEquals(1,customers.size());
    }

}