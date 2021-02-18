package nl.guyonmaissan.Garage.service;

import nl.guyonmaissan.Garage.model.Labor;

import java.util.Collection;
import java.util.Map;

public interface LaborService {

    Collection<Labor> getAllLabor();
    Labor getLaborById(Long id);
    Labor getLaborByDescription(String description);
    long createLabor(Labor labor);
    void updateLabor(Long id, Labor labor);
    void partialUpdateLabor(Long id, Map<String, String> fields);
    void deleteLabor(Long id);

}
