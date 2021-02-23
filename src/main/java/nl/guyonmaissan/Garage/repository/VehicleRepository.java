package nl.guyonmaissan.Garage.repository;

import nl.guyonmaissan.Garage.dbmodel.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {

    Vehicle findByLicensePlate(String licensePlate);
}
