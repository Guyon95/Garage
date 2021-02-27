package nl.guyonmaissan.Garage.service;

import nl.guyonmaissan.Garage.dbmodel.Customer;
import nl.guyonmaissan.Garage.model.ReturnObject;
import nl.guyonmaissan.Garage.payload.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

public interface CustomerService {

    Collection<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    List<Customer> getCustomers(Customer customer);

    ReturnObject getCustomerByLicensePlate(String licensePlate);

    //Collection<Customer> getCustomers(String name);
    long createCustomer(nl.guyonmaissan.Garage.model.Customer customer);
    ResponseEntity<MessageResponse> updateCustomer(Customer customer);

    ResponseEntity<MessageResponse> addCarPapers(MultipartFile file, String licensePlate);

    void deleteCustomer(Long id);
}
