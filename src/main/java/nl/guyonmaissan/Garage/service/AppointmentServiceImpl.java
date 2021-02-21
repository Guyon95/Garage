package nl.guyonmaissan.Garage.service;

import nl.guyonmaissan.Garage.exceptions.RecordNotFoundException;
import nl.guyonmaissan.Garage.model.Appointment;
import nl.guyonmaissan.Garage.model.ReturnObject;
import nl.guyonmaissan.Garage.model.Vehicle;
import nl.guyonmaissan.Garage.payload.response.MessageResponse;
import nl.guyonmaissan.Garage.repository.AppointmentRepository;
import nl.guyonmaissan.Garage.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService{

    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    VehicleRepository vehicleRepository;

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public Appointment getAppointmentById(Long id) {
        if (!appointmentRepository.existsById(id)) { throw new RecordNotFoundException(); }
        return appointmentRepository.findById(id).orElse(null);
    }

    @Override
    public ReturnObject getAppointmentByLicensePlate(String licensePlate) {
        ReturnObject returnObject = new ReturnObject();

        Vehicle vehicle = vehicleRepository.findByLicensePlate(licensePlate);

        if(vehicle !=null){
            List<Appointment> appointments = appointmentRepository.findAll();
            Appointment appointment = appointments.stream().filter(x -> x.getVehicle().equals(vehicle)).findAny().orElse(null);

            returnObject.setObject(appointment);
            returnObject.setMessage("Found a appointment for a car with the license plate: ${licensePlate} !");
            return returnObject;
        }

        returnObject.setMessage("The vehicle with license plate: ${licensePlate} doesn't exists.");
        return returnObject;
    }

    @Override
    public ReturnObject createAppointment(Appointment appointment) {
        ReturnObject returnObject = new ReturnObject();
        Appointment storedAppointment = appointmentRepository.save(appointment);

        returnObject.setObject(storedAppointment);
        returnObject.setMessage("Appointment is saved!");

        return returnObject;
    }

    @Override
    public ResponseEntity<MessageResponse> updateCustomer(Appointment appointment) {
        return null;
    }

    @Override
    public void deleteAppointment(Long id) {
        if (!appointmentRepository.existsById(id)) { throw new RecordNotFoundException(); }
        appointmentRepository.deleteById(id);
    }
}
