package nl.guyonmaissan.Garage.service;

import nl.guyonmaissan.Garage.model.Workorder;

import java.util.Collection;
import java.util.Map;

public interface WorkorderService {

    Collection<Workorder> getAllWorkorders();
    Workorder getWorkorderById(Long id);
    Workorder getWorkorderByWoNummer(Long woNummer);
    long createWorkorder(Workorder woNummer);
    void updateWorkorder(Long id, Workorder woNummer);
    void partialUpdateWorkorder(Long id, Map<String, String> fields);
    void deleteWorkorder(Long id);
}
