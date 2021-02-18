package nl.guyonmaissan.Garage.repository;

import nl.guyonmaissan.Garage.model.Parts;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartsRepository extends JpaRepository<Parts,Long> {
}
