package nl.guyonmaissan.Garage.service;

import nl.guyonmaissan.Garage.dbmodel.Part;
import nl.guyonmaissan.Garage.model.ReturnObject;
import nl.guyonmaissan.Garage.model.WorkorderRow;

import java.util.Collection;


public interface PartService {

    ReturnObject createPart(Part part);

    Part getRow(int partNumber);

    void changeStock(WorkorderRow workorderRow);

}
