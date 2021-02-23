package nl.guyonmaissan.Garage.repository;

import nl.guyonmaissan.Garage.dbmodel.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
