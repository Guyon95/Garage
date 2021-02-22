package nl.guyonmaissan.Garage.service;

import nl.guyonmaissan.Garage.model.AddWorkorderRow;
import nl.guyonmaissan.Garage.model.OtherAction;
import nl.guyonmaissan.Garage.model.ReturnObject;
import nl.guyonmaissan.Garage.model.Workorder;
import nl.guyonmaissan.Garage.model.WorkorderVehicle;


import java.util.List;


public interface WorkorderService {

    List<Workorder> getAllWorkorders();
    Workorder getWorkorderById(Long id);
    Workorder getWorkorderByWoNumber(Long woNumber);
    ReturnObject createWorkorder(WorkorderVehicle workorderVehicle);

    String updateWorkorder(AddWorkorderRow addWorkorderRow);

    String addOtherAction(OtherAction otherAction);

    void deleteWorkorder(Long id);
}
