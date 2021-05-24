package com.roman.controllers;


import com.roman.dao.CustomerDAO;
import com.roman.entity.Address;
import com.roman.entity.Cart;
import com.roman.entity.Customer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.stubbing.Answer;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.ui.ExtendedModelMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.*;

public class CustomerControllerTest {


    private final List<Customer> customers = new ArrayList<>();

    @Before
    public void initCustomers() {
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
    public void testList(){
        CustomerDAO customerDAO = mock(CustomerDAO.class);
        when(customerDAO.findAll()).thenReturn(customers);

        CustomerController controller = new CustomerController();

        ReflectionTestUtils.setField(controller, "customerDAO", customerDAO);
        ExtendedModelMap uiModel = new ExtendedModelMap();
        uiModel.addAttribute("customers", controller.getListCustomers());
        ResponseEntity<List<Customer>> model = (ResponseEntity<List<Customer>>) uiModel.get("customers");
        Assert.assertEquals(200, model.getStatusCodeValue());
        Assert.assertEquals(2, Objects.requireNonNull(model.getBody()).size());
    }


    @Test
    public void getCustomerById() {
        CustomerDAO customerDAO = mock(CustomerDAO.class);
        when(customerDAO.findById(2L)).thenReturn(customers.get(1));

        CustomerController controller = new CustomerController();

        ReflectionTestUtils.setField(controller, "customerDAO", customerDAO);
        ExtendedModelMap uiModel = new ExtendedModelMap();
        uiModel.addAttribute("customer", controller.getCustomerById(2L));
        ResponseEntity<Customer> model = (ResponseEntity<Customer>) uiModel.get("customer");
        Assert.assertEquals(200, model.getStatusCodeValue());
        Assert.assertEquals("Sergey", Objects.requireNonNull(model.getBody()).getName());
    }

    @Test
    public void whenCreateCustomerGetStatus201() {
        final Customer customer = new Customer();
        customer.setId(2L);
        customer.setName("Fedor");
        customer.setPhone("111");
        customer.setEmail("alex@sd");
        customer.setCart(new Cart());
        customer.setAddress(new Address("Russia", "Moscow", "New"));
        CustomerDAO customerDAO = mock(CustomerDAO.class);
        doAnswer((Answer<Customer>) invocationOnMock -> {
            customers.add(customer);
            return customer;
        }).when(customerDAO).create(customer);


        CustomerController controller = new CustomerController();
        ReflectionTestUtils.setField(controller, "customerDAO", customerDAO);
        ResponseEntity<Customer> responseEntity = controller.createCustomer(customer);
        Assert.assertEquals(201, responseEntity.getStatusCodeValue());
        Assert.assertEquals("Fedor", Objects.requireNonNull(responseEntity.getBody()).getName());
    }

//    @Test
//    void whenUpdateCustomerNameThenReturnStatus200AndUpdateCustomer() {
//        Customer customer = new Customer();
//        customer.setId(1L);
//        customer.setName("Alex");
//        customer.setPhone("111");
//        customer.setEmail("alex@sd");
//        customer.setCart(new Cart());
//        customer.setAddress(new Address("Russia", "Moscow", "New"));
//
//        Customer customerUpdate = new Customer();
//        customerUpdate.setId(1L);
//        customerUpdate.setName("Fedor");
//        customerUpdate.setPhone("111");
//        customerUpdate.setEmail("alex@sd");
//        customerUpdate.setCart(new Cart());
//        customerUpdate.setAddress(new Address("Russia", "Moscow", "New"));
//        controller.createCustomer(customer);
//        controller.createCustomer(customer);
//
//        CustomerController controller = Mockito.mock(CustomerController.class);
//        doReturn(new ResponseEntity<>(customerUpdate, HttpStatus.OK)).when(controller).update(1L, customerUpdate);
//
//        ResponseEntity<Customer> responseEntity = controller.update(1L, customerUpdate);
//        assertEquals(200, responseEntity.getStatusCodeValue());
//        assertEquals("Fedor", Objects.requireNonNull(responseEntity.getBody()).getName());
//    }
//    @Test
//    void whenUpdateCustomerNameAndCustomerNotFoundThenReturnStatus304() {
//
//        Customer customer = new Customer();
//        customer.setId(1L);
//        customer.setName("Alex");
//        customer.setPhone("111");
//        customer.setEmail("alex@sd");
//        customer.setCart(new Cart());
//        customer.setAddress(new Address("Russia", "Moscow", "New"));
//
//        Customer customerUpdate = new Customer();
//        customerUpdate.setId(1L);
//        customerUpdate.setName("Fedor");
//        customerUpdate.setPhone("111");
//        customerUpdate.setEmail("alex@sd");
//        customerUpdate.setCart(new Cart());
//        customerUpdate.setAddress(new Address("Russia", "Moscow", "New"));
//
//        CustomerController controller = Mockito.mock(CustomerController.class);
//        doReturn(new ResponseEntity<>(HttpStatus.NOT_MODIFIED)).when(controller).update(5L, customerUpdate);
//        controller.createCustomer(customer);
//        controller.update(5L,customerUpdate);
//        ResponseEntity<Customer> responseEntityNotUpdate = controller.update(5L, customerUpdate);
//        assertEquals(304, responseEntityNotUpdate.getStatusCodeValue());
//
//    }

}