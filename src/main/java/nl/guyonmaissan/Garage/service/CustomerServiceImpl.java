package nl.guyonmaissan.Garage.service;

import nl.guyonmaissan.Garage.exceptions.RecordNotFoundException;
import nl.guyonmaissan.Garage.model.Customer;
import nl.guyonmaissan.Garage.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public Collection<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getCustomerById(Long id) {
        if (!customerRepository.existsById(id)) { throw new RecordNotFoundException(); }
        return customerRepository.findById(id).orElse(null);
    }

    @Override
    public List<Customer> getCustomers(Customer customer) {
        List<Customer> customers = customerRepository.findAll();

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
        return customers;
    }

    @Override
    public long createCustomer(Customer customer) {
        Customer storedCustomer = customerRepository.save(customer);
        return storedCustomer.getId();
    }

    @Override
    public void updateCustomer(Long id, Customer customer) {
        // ToDo
    }

    @Override
    public void partialUpdateCustomer(Long id, Map<String, String> fields) {
        // ToDo
    }

    @Override
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) { throw new RecordNotFoundException(); }
        customerRepository.deleteById(id);
    }
}
