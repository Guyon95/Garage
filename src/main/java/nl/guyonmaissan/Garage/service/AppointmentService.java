package nl.guyonmaissan.Garage.service;

import nl.guyonmaissan.Garage.dbmodel.Appointment;
import nl.guyonmaissan.Garage.model.ReturnObject;
import nl.guyonmaissan.Garage.payload.response.MessageResponse;
import org.springframework.http.ResponseEntity;


import java.util.List;

public interface AppointmentService {

    List<Appointment> getAllAppointments();
    Appointment getAppointmentById(Long id);
    ReturnObject getAppointmentByLicensePlate(String licensePlate);
    ReturnObject createAppointment(Appointment appointment);
    ResponseEntity<MessageResponse> updateCustomer(Appointment appointment);
    void deleteAppointment(Long id);
}
