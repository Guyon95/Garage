package nl.guyonmaissan.Garage.repository;

import nl.guyonmaissan.Garage.dbmodel.Part;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartRepository extends JpaRepository<Part,Long> {

    Part findByPartNumber ( int partNumber);

    Part findByPartNumberOrDescription(int partNumber, String description);

    Part findByDescription(String description);
}
