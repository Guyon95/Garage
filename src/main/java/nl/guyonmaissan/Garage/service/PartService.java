package nl.guyonmaissan.Garage.service;

import nl.guyonmaissan.Garage.model.Part;

import java.util.Collection;
import java.util.Map;

public interface PartService {

    Collection<Part> getAllParts();
    Part getPartsById(Long id);
    Part getParts(String description);
    long createParts(Part part);
    void updateParts(Long id, Part part);
    void partialUpdateParts(Long id, Map<String, String> fields);
    void deleteParts(Long id);
    Part getRow(int partNumber);

}
