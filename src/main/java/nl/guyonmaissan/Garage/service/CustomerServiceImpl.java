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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
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
            Customer customer = vehicle.getCustomer();

            returnObject.setObject(customer);
            returnObject.setMessage("Found a vehicle with this license plate!");
            return returnObject;
        }

        returnObject.setMessage("The vehicle with given license plate doesn't exists. Please create a new customer.");
        return returnObject;
    }

    @Override
    public Customer createCustomer(String firstname, String lastname, String phoneNumber) {

        Customer dbCustomer = new Customer();
        dbCustomer.setFirstName(firstname);
        dbCustomer.setLastName(lastname);
        dbCustomer.setPhoneNumber(phoneNumber);
        dbCustomer.setCreated(LocalDateTime.now());
        dbCustomer.setModified(LocalDateTime.now());

        Customer storedCustomer = customerRepository.save(dbCustomer);

        return storedCustomer;
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
    public ResponseEntity<MessageResponse> addCarPapers(MultipartFile file, String licensePlate) {
        if (null == file.getOriginalFilename()) {
            return ResponseEntity.ok(new MessageResponse("Failed to add the car papers to the car."));
        }
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(file.getOriginalFilename());
            Files.write(path, bytes);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return ResponseEntity.ok(new MessageResponse("Added Car papers!"));
    }


    @Override
    public void deleteCustomer(Long id) {
        if (!customerRepository.existsById(id)) { throw new RecordNotFoundException(); }
        customerRepository.deleteById(id);
    }
}
