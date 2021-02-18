package nl.guyonmaissan.Garage.repository;

import nl.guyonmaissan.Garage.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
}
