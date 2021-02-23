package nl.guyonmaissan.Garage.repository;

import nl.guyonmaissan.Garage.dbmodel.Workorder;
import nl.guyonmaissan.Garage.dbmodel.WorkorderRow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkorderRowRepository extends JpaRepository<WorkorderRow,Long> {

    WorkorderRow findWorkorderRowByWorkorderAndDescription(Workorder workorder, String description);

    List<WorkorderRow> findWorkorderRowByWorkorder(Workorder workorder);
}
