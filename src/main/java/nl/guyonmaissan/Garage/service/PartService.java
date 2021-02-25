package nl.guyonmaissan.Garage.service;

import nl.guyonmaissan.Garage.dbmodel.Part;
import nl.guyonmaissan.Garage.model.ReturnObject;
import nl.guyonmaissan.Garage.model.WorkorderRow;

import java.util.Collection;
import java.util.Map;

public interface PartService {

    Collection<Part> getAllParts();
    Part getPartsById(Long id);
    Part getParts(String description);
    ReturnObject createPart(nl.guyonmaissan.Garage.model.Part part);
    void updateParts(Long id, Part part);

    void deleteParts(Long id);
    Part getRow(int partNumber);

    void changeStock(WorkorderRow workorderRow);

}
