package nl.guyonmaissan.Garage.service;

import nl.guyonmaissan.Garage.dbmodel.Vehicle;

import nl.guyonmaissan.Garage.model.Customer;
import nl.guyonmaissan.Garage.model.ReturnObject;
import nl.guyonmaissan.Garage.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Autowired
    CustomerService customerService;

    @Override
    public ReturnObject createVehicle(Customer customer) {
        ReturnObject returnObject = new ReturnObject();

        if(customer.getFirstname().equals("") || customer.getLastname().equals("") || customer.getPhoneNumber().equals("") || customer.getLicensePlate().equals("")){

            returnObject.setMessage("Pleas insert a valid value for firstname, lastname, phonenumber and license plate.");
            return returnObject;
        }
        nl.guyonmaissan.Garage.dbmodel.Customer dbCustomer = customerService.createCustomer(customer.getFirstname(), customer.getLastname(), customer.getPhoneNumber());

        Vehicle vehicle = new Vehicle();
        vehicle.setCustomer(dbCustomer);
        vehicle.setLicensePlate(customer.getLicensePlate());
        vehicle.setCreated(LocalDateTime.now());
        vehicle.setModified(LocalDateTime.now());

        vehicleRepository.save(vehicle);

        returnObject.setMessage("Succesfully created a vehicle and customer.");
        returnObject.setObject(vehicle);

        return  returnObject;
    }
}
