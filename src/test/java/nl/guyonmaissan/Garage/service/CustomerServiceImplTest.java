package nl.guyonmaissan.Garage.service;

import nl.guyonmaissan.Garage.dbmodel.Customer;
import nl.guyonmaissan.Garage.dbmodel.Vehicle;
import nl.guyonmaissan.Garage.model.ReturnObject;
import nl.guyonmaissan.Garage.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceImplTest {

    Customer customer;
    List<Customer> customers;
    Vehicle vehicle;
    List<Vehicle> vehicles;


    @BeforeEach
    public void setUp() {
        customer = new Customer();
        customers = new ArrayList<>();
        vehicle = new Vehicle();
        vehicles = new ArrayList<>();
    }

    @Test
    void getAllCustomers() {
        customers.add(customer);

        assertEquals(1,customers.stream().count());

    }


    @Test
    void getCustomers() {

        //arrange
        customer.setLastName("Bruggen");

        Customer cust1 = new Customer();
        cust1.setFirstName("Peter");
        cust1.setLastName("Janssen");
        customers.add(cust1);

        Customer cust2 = new Customer();
        cust2.setFirstName("Peter");
        cust2.setLastName("Bruggen");
        customers.add(cust2);

        //act
        if(customer.getPhoneNumber() != null){
            customers = customers.stream()
                    .filter(x -> x.getPhoneNumber().equalsIgnoreCase(customer.getPhoneNumber()))
                    .collect(Collectors.toList());
        }

        if(customer.getFirstName() != null){
            customers = customers.stream()
                    .filter(x -> x.getFirstName().equalsIgnoreCase(customer.getFirstName()))
                    .collect(Collectors.toList());
        }

        if(customer.getLastName() != null){
            customers = customers.stream()
                    .filter(x -> x.getLastName().equalsIgnoreCase(customer.getLastName()))
                    .collect(Collectors.toList());
        }

        //assert
        assertEquals(1,customers.stream().count());

    }

    @Test
    void getCustomerByLicensePlate() {

        //Arrange
        Vehicle car1 = new Vehicle();
        car1.setLicensePlate("AA-20-AA");
        car1.setCustomer(customer);
        vehicles.add(car1);

        Vehicle car2 = new Vehicle();
        car2.setLicensePlate("AA-21-AA");
        vehicles.add(car2);

        //Act
        ReturnObject returnObject = new ReturnObject();

        Vehicle vehicle = vehicles.stream().filter(x -> x.getLicensePlate().equals("AA-20-AA")).findFirst().orElse(null);

        if(vehicle !=null){
            Customer customer = vehicle.getCustomer();

            returnObject.setObject(customer);
            returnObject.setMessage("Found a vehicle with this license plate!");

        }

        returnObject.setMessage("The vehicle with given license plate doesn't exists. Please create a new customer.");


        //Assert
        assertEquals(customer,returnObject.getObject());
    }

    @Test
    void createCustomer() {

        //arrange
        String firstname = "Peter";
        String lastname = "Janssen";
        String phoneNumber = "0612345678";

        //act
        Customer dbCustomer = new Customer();
        dbCustomer.setFirstName(firstname);
        dbCustomer.setLastName(lastname);
        dbCustomer.setPhoneNumber(phoneNumber);
        dbCustomer.setCreated(LocalDateTime.now());
        dbCustomer.setModified(LocalDateTime.now());

        //assert
        assertEquals(firstname,dbCustomer.getFirstName());
        assertEquals(lastname,dbCustomer.getLastName());
        assertEquals(phoneNumber,dbCustomer.getPhoneNumber());
    }

    @Test
    void updateCustomer() {
        //arrange
        Customer updateCustomer = new Customer();
        updateCustomer.setFirstName("Henk");
        updateCustomer.setLastName("Hielkema");
        updateCustomer.setPhoneNumber("0647896348");

        customer.setFirstName("Peter");
        customer.setLastName("Jansen");
        customer.setPhoneNumber("0612345678");

        //act
        updateCustomer.setFirstName(customer.getFirstName());
        updateCustomer.setLastName((customer.getLastName()));
        updateCustomer.setPhoneNumber(customer.getPhoneNumber());
        updateCustomer.setModified(LocalDateTime.now());

        //assert
        assertEquals(updateCustomer.getFirstName(), customer.getFirstName());
        assertEquals(updateCustomer.getLastName(), customer.getLastName());
        assertEquals(updateCustomer.getPhoneNumber(), customer.getPhoneNumber());

    }

}