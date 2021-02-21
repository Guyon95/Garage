package nl.guyonmaissan.Garage.controller;

import nl.guyonmaissan.Garage.model.Appointment;
import nl.guyonmaissan.Garage.model.Customer;
import nl.guyonmaissan.Garage.model.ReturnObject;
import nl.guyonmaissan.Garage.payload.response.MessageResponse;
import nl.guyonmaissan.Garage.service.AppointmentService;
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
@RequestMapping(value = "/appointment")
@PreAuthorize("hasRole('ADMINISTRATOR')")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @GetMapping(value = "")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<Object> getAppointments() {
        return ResponseEntity.ok().body(appointmentService.getAllAppointments());
    }

    @GetMapping(value = "/licenseplate/{licensePlate}")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<Object> getCustomer(@PathVariable("licensePlate") String licensePlate) {
        return ResponseEntity.ok().body(appointmentService.getAppointmentByLicensePlate(licensePlate));
    }

    @PostMapping(value = "create")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<Object> createCustomer(@RequestBody Appointment appointment) {
        ReturnObject newId = appointmentService.createAppointment(appointment);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newId).toUri();

        return ResponseEntity.created(location).body(location);
    }

    @PostMapping("/update")
    public ResponseEntity<MessageResponse> updateCustomer(@RequestBody Appointment appointment) {
        return appointmentService.updateCustomer(appointment);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteCourse(@PathVariable("id") long id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
