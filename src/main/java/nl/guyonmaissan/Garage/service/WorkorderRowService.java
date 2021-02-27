package nl.guyonmaissan.Garage.service;

import nl.guyonmaissan.Garage.model.AddLabor;
import nl.guyonmaissan.Garage.model.AddPart;
import nl.guyonmaissan.Garage.model.Approve;
import nl.guyonmaissan.Garage.model.OtherAction;
import nl.guyonmaissan.Garage.dbmodel.Workorder;
import nl.guyonmaissan.Garage.dbmodel.WorkorderRow;

import java.util.Collection;
import java.util.List;

public interface WorkorderRowService {
    List<nl.guyonmaissan.Garage.model.WorkorderRow> getWorkorderRowByWoNummer(Long woNumber);

    void createWorkorderRow(OtherAction otherAction, Workorder workorder);

    String approveWorkorderRows (Approve approve);

    void AddCarCheck(Workorder workorder);
    void AddLabor(AddLabor labor, Workorder workorder);
    void AddPart(AddPart part, Workorder workorder);

    nl.guyonmaissan.Garage.model.WorkorderRow CreateApiModel (WorkorderRow dbWorkorderRow);

    nl.guyonmaissan.Garage.model.WorkorderRow CreateInvoiceRow(WorkorderRow dbWorkorderRow);


}
