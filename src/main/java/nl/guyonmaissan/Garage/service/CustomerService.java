package nl.guyonmaissan.Garage.service;

import nl.guyonmaissan.Garage.model.Customer;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface CustomerService {

    Collection<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    List<Customer> getCustomers(Customer customer);

    //Collection<Customer> getCustomers(String name);
    long createCustomer(Customer customer);
    void updateCustomer(Long id, Customer customer);
    void partialUpdateCustomer(Long id, Map<String, String> fields);
    void deleteCustomer(Long id);
}
