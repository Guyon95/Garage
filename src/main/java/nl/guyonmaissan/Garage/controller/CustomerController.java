package nl.guyonmaissan.Garage.controller;

import nl.guyonmaissan.Garage.dbmodel.Customer;
import nl.guyonmaissan.Garage.model.ReturnObject;
import nl.guyonmaissan.Garage.payload.response.MessageResponse;
import nl.guyonmaissan.Garage.service.CustomerService;
import nl.guyonmaissan.Garage.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;



@RestController
@RequestMapping(value = "/customers")
@PreAuthorize("hasRole('ADMINISTRATOR')")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    VehicleService vehicleService;


    @GetMapping(value = "")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<Object> getCustomers() {
        return ResponseEntity.ok().body(customerService.getAllCustomers());
    }


    @GetMapping(value = "/licenseplate/{licensePlate}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<Object> getCustomerByLicensePlate(@PathVariable("licensePlate") String licensePlate) {
        return ResponseEntity.ok().body(customerService.getCustomerByLicensePlate(licensePlate));
    }

    @PostMapping(value = "")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<Object> getCustomerMultipleValues(@RequestBody Customer customer) {
        return ResponseEntity.ok().body(customerService.getCustomers(customer));
    }


    @PostMapping(value = "create")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<Object> createVehicle(@RequestBody nl.guyonmaissan.Garage.model.Customer customer) {
        ReturnObject returnObject = vehicleService.createVehicle(customer);

        return ResponseEntity.ok().body(returnObject);
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

    @PostMapping("/upload")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<MessageResponse> uploadFile(@RequestPart("file") MultipartFile file, @RequestPart("licensePlate") String licensePlate) {
        return customerService.addCarPapers(file,licensePlate);
    }
}
