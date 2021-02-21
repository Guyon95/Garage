package nl.guyonmaissan.Garage.repository;

import nl.guyonmaissan.Garage.model.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part,Long> {

    Part findByPartNumber ( int partNumber);
}
