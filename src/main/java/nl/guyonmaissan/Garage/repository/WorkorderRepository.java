package nl.guyonmaissan.Garage.repository;

import nl.guyonmaissan.Garage.model.Workorder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkorderRepository extends JpaRepository<Workorder,Long> {
    Workorder findByWoNumber(Long woNumber);

}
