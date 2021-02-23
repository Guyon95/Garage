package nl.guyonmaissan.Garage.controller;

import nl.guyonmaissan.Garage.dbmodel.Customer;
import nl.guyonmaissan.Garage.payload.response.MessageResponse;
import nl.guyonmaissan.Garage.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/customers")
@PreAuthorize("hasRole('ADMINISTRATOR')")
public class CustomerController {

    @Autowired
    CustomerService customerService;


    @GetMapping(value = "")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<Object> getCustomers() {
        return ResponseEntity.ok().body(customerService.getAllCustomers());
    }

    @GetMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<Object> getCustomer(@PathVariable("id") long id) {
        return ResponseEntity.ok().body(customerService.getCustomerById(id));
    }

    @GetMapping(value = "/licenseplate/{licensePlate}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<Object> getCustomer(@PathVariable("licensePlate") String licensePlate) {
        return ResponseEntity.ok().body(customerService.getCustomerByLicensePlate(licensePlate));
    }

    @PostMapping(value = "")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<Object> getCustomer(@RequestBody Customer customer) {
        return ResponseEntity.ok().body(customerService.getCustomers(customer));
    }


    @PostMapping(value = "create")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<Object> createCustomer(@RequestBody Customer customer) {
        long newId = customerService.createCustomer(customer);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body(location);
    }

    @PostMapping("/update")
    public ResponseEntity<MessageResponse> updateCustomer(@RequestBody Customer customer) {
        return customerService.updateCustomer(customer);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteCourse(@PathVariable("id") long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}
