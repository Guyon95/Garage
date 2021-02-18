package nl.guyonmaissan.Garage.service;

import nl.guyonmaissan.Garage.model.Parts;

import java.util.Collection;
import java.util.Map;

public interface PartsService {

    Collection<Parts> getAllParts();
    Parts getPartsById(Long id);
    Parts getParts(String description);
    long createParts(Parts parts);
    void updateParts(Long id, Parts parts);
    void partialUpdateParts(Long id, Map<String, String> fields);
    void deleteParts(Long id);

}
