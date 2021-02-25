package nl.guyonmaissan.Garage.service;


import nl.guyonmaissan.Garage.model.AddWorkorderRow;
import nl.guyonmaissan.Garage.model.OtherAction;
import nl.guyonmaissan.Garage.model.ReturnObject;
import nl.guyonmaissan.Garage.dbmodel.Workorder;
import nl.guyonmaissan.Garage.model.WorkorderVehicle;


import java.util.List;


public interface WorkorderService {

    List<Workorder> getAllWorkorders();

    ReturnObject createWorkorder(WorkorderVehicle workorderVehicle);

    String updateWorkorder(AddWorkorderRow addWorkorderRow);

    String addOtherAction(OtherAction otherAction);

    String repairsExcuted(Long woNumber);

    List<Workorder> getFinishedWos();

    ReturnObject createInvoice(Long woNumber);

    void deleteWorkorder(Long id);

}
