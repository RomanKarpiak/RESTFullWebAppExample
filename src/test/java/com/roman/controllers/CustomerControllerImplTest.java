package com.roman.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roman.config.SpringConfig;
import com.roman.controllers.Impl.CustomerControllerImpl;
import com.roman.entity.Address;
import com.roman.entity.Cart;
import com.roman.entity.Customer;
import com.roman.service.ShopService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static com.jayway.jsonassert.impl.matcher.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
@WebAppConfiguration
@ActiveProfiles(value = "local")
public class CustomerControllerImplTest {

    @Mock
    private ShopService<Customer> service;

    @InjectMocks
    CustomerControllerImpl controller;


    private MockMvc mockMvc;


    private List<Customer> customers = new ArrayList<>();

    @Before
    public void initCustomers() throws Exception {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
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
    public void createNewCustomerTest() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders
                .post("/customers")
                .content(asJsonString(customers.get(0)))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").exists());


    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getListCustomersTest() throws Exception {
        when(service.findAll()).thenReturn(customers);
        mockMvc.perform(MockMvcRequestBuilders
                .get("/customers")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[*]", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1));

    }

    @Test
    public void getCustomerByIdTest() throws Exception {
        when(service.findById(1L)).thenReturn(customers.get(0));
        mockMvc.perform(MockMvcRequestBuilders
                .get("/customers/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Alex"));

    }

    @Test
    public void deleteCustomerTest() throws Exception {
        doNothing().when(service).delete(customers.get(0));
        mockMvc.perform(MockMvcRequestBuilders
                .delete("/customers/{id}", 1))
                .andExpect(status().isNoContent());
    }

    @Test
    public void updateCustomerTest() throws Exception {

        Customer updatedCustomer = new Customer();
        updatedCustomer.setId(1L);
        updatedCustomer.setName("Alex");
        updatedCustomer.setPhone("12345");
        updatedCustomer.setEmail("alexNewEmail@sd");
        updatedCustomer.setCart(new Cart());
        updatedCustomer.setAddress(new Address("Russia", "Moscow", "New"));

        mockMvc.perform(MockMvcRequestBuilders
                .put("/customers")
                .content(asJsonString(updatedCustomer))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value("1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Alex"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.phone").value("12345"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("alexNewEmail@sd"));
    }


}


