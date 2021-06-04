package com.roman.controllers;


public class CustomerControllerImplTest {

//    @Mock
//    private ShopService service;
//    @InjectMocks
//    CustomerControllerImpl controller;
//
//
//    private List<Customer> customers = new ArrayList<>();
//
//    @Before
//    public void initCustomers() {
//        MockitoAnnotations.openMocks(this);
//        Customer customerOne = new Customer();
//        customerOne.setId(1L);
//        customerOne.setName("Alex");
//        customerOne.setPhone("111");
//        customerOne.setEmail("alex@sd");
//        customerOne.setCart(new Cart());
//        customerOne.setAddress(new Address("Russia", "Moscow", "New"));
//
//        Customer customerTwo = new Customer();
//        customerTwo.setId(2L);
//        customerTwo.setName("Sergey");
//        customerTwo.setPhone("222");
//        customerTwo.setEmail("seerg@sd");
//        customerTwo.setCart(new Cart());
//        customerTwo.setAddress(new Address("Russia", "Peterburg", "Old"));
//        customers.add(customerOne);
//        customers.add(customerTwo);
//    }
//
//    @Test
//    public void getListCustomersTest() {
//        when(service.findAll()).thenReturn(customers);
//
//        ReflectionTestUtils.setField(controller, "customerDAO", service);
//        ExtendedModelMap uiModel = new ExtendedModelMap();
//        uiModel.addAttribute("customers", controller.findAll());
//        ResponseEntity<List<Customer>> model = (ResponseEntity<List<Customer>>) uiModel.get("customers");
//
//        Assert.assertEquals(200, model.getStatusCodeValue());
//        Assert.assertEquals(2, Objects.requireNonNull(model.getBody()).size());
//    }
//
//
//    @Test()
//    public void getCustomerByIdTest() {
//        when(service.findById(2L)).thenReturn(customers.get(1));
//
//        ReflectionTestUtils.setField(controller, "customerDAO", service);
//        ExtendedModelMap uiModel = new ExtendedModelMap();
//        uiModel.addAttribute("customer", controller.findById(2L));
//        ResponseEntity<Customer> model = (ResponseEntity<Customer>) uiModel.get("customer");
//
//        Assert.assertEquals(200, model.getStatusCodeValue());
//        Assert.assertEquals("Sergey", Objects.requireNonNull(model.getBody()).getName());
//    }

//    @Test
//    public void whenCreateCustomerGetStatus201() {
//        Customer customer = new Customer();
//        customer.setId(2L);
//        customer.setName("Fedor");
//        customer.setPhone("111");
//        customer.setEmail("fedor@sd");
//        customer.setCart(new Cart());
//        customer.setAddress(new Address("Russia", "Moscow", "New"));
//
//        doAnswer((Answer<Customer>) invocationOnMock -> {
//            customers.add(customer);
//            return customer;
//        }).when(service).create(customer);
//
//        ReflectionTestUtils.setField(controller, "customerDAO", service);
//        ResponseEntity<Customer> responseEntity = controller.create(customer);
//
//        Assert.assertEquals(201, responseEntity.getStatusCodeValue());
//        Assert.assertEquals("Fedor", Objects.requireNonNull(responseEntity.getBody()).getName());
//    }

//    @Test
//    public void whenUpdateCustomerNameThenReturnStatus200AndUpdateCustomer() {
//        Customer updatedCustomer1 = new Customer();
//        updatedCustomer1.setId(1L);
//        updatedCustomer1.setName("Leonid");
//        updatedCustomer1.setPhone("555555");
//        updatedCustomer1.setEmail("leonid@sd");
//        updatedCustomer1.setCart(new Cart());
//        updatedCustomer1.setAddress(new Address("Russia", "Moscow", "New"));
//
//        when(service.findById(1L)).thenReturn(customers.get(0));
//
//        ReflectionTestUtils.setField(controller, "customerDAO", service);
//
//        ResponseEntity<Customer> responseEntity = controller.update(updatedCustomer1);
//        Assert.assertEquals(200, responseEntity.getStatusCodeValue());
//        Assert.assertEquals("Leonid", Objects.requireNonNull(responseEntity.getBody()).getName());
//        Assert.assertEquals("leonid@sd", Objects.requireNonNull(responseEntity.getBody()).getEmail());
//        Assert.assertEquals("555555", Objects.requireNonNull(responseEntity.getBody()).getPhone());
//    }

//    @Test
//    public void whenDeleteCustomerThenCustomersSize1AndStatus200() {
//        when(service.findById(1L)).thenReturn(customers.get(0));
//        doAnswer((Answer<Long>) invocationOnMock -> {
//            Customer customer = invocationOnMock.getArgument(0);
//            customers.remove(0);
//            return customer.getId();
//        }).when(service).delete(customers.get(0));
//
//        ReflectionTestUtils.setField(controller, "customerDAO", service);
//
//        ResponseEntity<Long> responseEntity = controller.deleteById(1L);
//        Assert.assertEquals(200, responseEntity.getStatusCodeValue());
//        Assert.assertEquals(1L, Objects.requireNonNull(responseEntity.getBody()).longValue());
//        Assert.assertEquals(1,customers.size());
//    }

}