package nl.guyonmaissan.Garage.service;

import nl.guyonmaissan.Garage.dbmodel.Customer;
import nl.guyonmaissan.Garage.model.ReturnObject;
import nl.guyonmaissan.Garage.payload.response.MessageResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Collection;
import java.util.List;

public interface CustomerService {

    List<Customer> getAllCustomers();

    List<Customer> getCustomers(Customer customer);

    ReturnObject getCustomerByLicensePlate(String licensePlate);

    Customer createCustomer(String firstname, String lastname, String phoneNumber);

    ResponseEntity<MessageResponse> updateCustomer(Customer customer);

    ResponseEntity<MessageResponse> addCarPapers(MultipartFile file, String licensePlate);

    void deleteCustomer(Long id);
}
