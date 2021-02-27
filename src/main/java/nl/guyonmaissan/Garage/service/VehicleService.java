package nl.guyonmaissan.Garage.service;


import nl.guyonmaissan.Garage.model.Customer;
import nl.guyonmaissan.Garage.model.ReturnObject;


public interface VehicleService {

    ReturnObject createVehicle(Customer customer);
}
