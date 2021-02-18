package nl.guyonmaissan.Garage.service;

import nl.guyonmaissan.Garage.model.Vehicle;

import java.util.Collection;
import java.util.Map;

public interface VehicleService {

    Collection<Vehicle> getAllVehicles();
    Vehicle getVehicleById(Long id);
    Vehicle getVehicleByKenteken(String kenteken);
    long createVehicle(Vehicle vehicle);
    void updateVehicle(Long id, Vehicle vehicle);
    void partialUpdateVehicle(Long id, Map<String, String> fields);
    void deleteVehicle(Long id);
}
