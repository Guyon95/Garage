package nl.guyonmaissan.Garage.repository;

import nl.guyonmaissan.Garage.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
