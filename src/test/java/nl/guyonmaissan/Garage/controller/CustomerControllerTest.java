package nl.guyonmaissan.Garage.controller;


import nl.guyonmaissan.Garage.dbmodel.Customer;
import nl.guyonmaissan.Garage.dbmodel.Vehicle;
import nl.guyonmaissan.Garage.model.ReturnObject;
import nl.guyonmaissan.Garage.repository.CustomerRepository;
import nl.guyonmaissan.Garage.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import java.util.ArrayList;
import java.util.List;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @MockBean
    private CustomerRepository customerRepository;

    @InjectMocks
    CustomerController controller;

    List<Customer> customers;

    Customer cust1;

    ReturnObject returnObject;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        customers = new ArrayList<>();

        cust1 = new Customer();
        cust1.setId(1L);
        cust1.setFirstName("Peter");
        customers.add(cust1);

        Customer cust2 = new Customer();
        cust2.setFirstName("Henk");
        cust2.setId(1L);
        customers.add(cust2);

        Vehicle vehicle1 = new Vehicle();
        vehicle1.setCustomer(cust1);
        vehicle1.setLicensePlate("AA-20-AA");

        returnObject = new ReturnObject();
        returnObject.setObject(vehicle1);
        returnObject.setMessage("Succes!");


        mockMvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    void getCustomers() throws Exception {
        when(customerService.getAllCustomers()).thenReturn(customers);

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk());

        assertEquals(2,customers.stream().count());
    }

    @Test
    @WithMockUser
    void getCustomerByLicensePlate() throws Exception {
        //arrange
        String licensePlate = "AA-20-AA";

        //act
        when(customerService.getCustomerByLicensePlate(licensePlate)).thenReturn(returnObject);
        mockMvc.perform(get("/customers/licenseplate/AA-20-AA"))
                .andExpect(status().isOk());

        Vehicle vehicle = (Vehicle) returnObject.getObject();

        //arrange
        assertEquals(licensePlate,vehicle.getLicensePlate());
    }
}