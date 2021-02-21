package nl.guyonmaissan.Garage.service;

import nl.guyonmaissan.Garage.model.AddLabor;
import nl.guyonmaissan.Garage.model.AddPart;
import nl.guyonmaissan.Garage.model.Workorder;
import nl.guyonmaissan.Garage.model.WorkorderRow;

import java.util.Collection;
import java.util.Map;

public interface WorkorderRowService {
    Collection<WorkorderRow> getAllWorkorderRows();
    WorkorderRow getWorkorderRowById(Long id);
    Collection<WorkorderRow> getWorkorderRowByWoNummer(String description);
    long createWorkorderRow(WorkorderRow woNummer);
    void updateWorkorderRow(Long id, WorkorderRow woNummer);
    void partialUpdateWorkorderRow(Long id, Map<String, String> fields);
    void deleteWorkorderRow(Long id);

    void AddCarCheck(Workorder workorder);
    void AddLabor(AddLabor labor, Workorder workorder);
    void AddPart(AddPart part, Workorder workorder);
}
