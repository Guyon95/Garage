package nl.guyonmaissan.Garage.service;

import nl.guyonmaissan.Garage.exceptions.RecordNotFoundException;
import nl.guyonmaissan.Garage.dbmodel.Customer;
import nl.guyonmaissan.Garage.model.ReturnObject;
import nl.guyonmaissan.Garage.dbmodel.Vehicle;
import nl.guyonmaissan.Garage.payload.response.MessageResponse;
import nl.guyonmaissan.Garage.repository.CustomerRepository;
import nl.guyonmaissan.Garage.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    VehicleRepository vehicleRepository;

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
    public ReturnObject getCustomerByLicensePlate(String licensePlate) {

        ReturnObject returnObject = new ReturnObject();

        Vehicle vehicle = vehicleRepository.findByLicensePlate(licensePlate);

        if(vehicle !=null){
            Customer customer = customerRepository.findById(vehicle.getCustomer().getId()).orElse(null);


            if(customer == null){
                returnObject.setMessage("Couldn't find any appointmonts with he license plate: ${licensePlate}.");
                return returnObject;
            }
            returnObject.setObject(customer);
            returnObject.setMessage("Found a customer with this license plate!");
            return returnObject;
        }

        returnObject.setMessage("The vehicle with license plate: ${licensePlate} doesn't exists.");
        return returnObject;
    }

    @Override
    public long createCustomer(Customer customer) {
        Customer storedCustomer = customerRepository.save(customer);
        return storedCustomer.getId();
    }

    @Override
    public ResponseEntity<MessageResponse> updateCustomer(Customer customer) {
        Customer updateCustomer = customerRepository.findById(customer.getId()).orElse(null);

        if(updateCustomer != null){

            updateCustomer.setFirstName(customer.getFirstName());
            updateCustomer.setLastName((customer.getLastName()));
            updateCustomer.setPhoneNumber(customer.getPhoneNumber());
            updateCustomer.setModified(LocalDateTime.now());

            customerRepository.save(updateCustomer);
            return ResponseEntity.ok(new MessageResponse("Customer has been updated!"));
        }
        return ResponseEntity.ok(new MessageResponse("No, customer found!"));
    }


    @Override
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) { throw new RecordNotFoundException(); }
        customerRepository.deleteById(id);
    }
}
