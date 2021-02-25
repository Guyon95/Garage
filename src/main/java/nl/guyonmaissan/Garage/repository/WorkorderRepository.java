package nl.guyonmaissan.Garage.repository;

import nl.guyonmaissan.Garage.dbmodel.Workorder;
import nl.guyonmaissan.Garage.model.EWorkorderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkorderRepository extends JpaRepository<Workorder,Long> {
    Workorder findByWoNumber(Long woNumber);

    Workorder findTopByOrderByCreatedDesc();

    Workorder findTopByOrderByInvoiceNumberDesc();

    List<Workorder> findWorkordersByStatus(EWorkorderStatus status);

}
