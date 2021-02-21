package nl.guyonmaissan.Garage.repository;

import nl.guyonmaissan.Garage.model.Labor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaborRepository extends JpaRepository<Labor,Long> {

    Labor findByDescription(String description);

    Labor findByLaborNumber(int laborNumber);
}
