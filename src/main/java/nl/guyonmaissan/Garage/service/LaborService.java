package nl.guyonmaissan.Garage.service;

import nl.guyonmaissan.Garage.dbmodel.Labor;
import nl.guyonmaissan.Garage.model.ReturnObject;

import java.util.Collection;
import java.util.Map;

public interface LaborService {

    Collection<Labor> getAllLabor();
    Labor getLaborById(Long id);
    Labor getLaborByDescription(String description);
    ReturnObject createLabor(nl.guyonmaissan.Garage.model.Labor labor);
    void updateLabor(Long id, Labor labor);
    void partialUpdateLabor(Long id, Map<String, String> fields);
    void deleteLabor(Long id);

    Labor getCheckCar();
    Labor getRow(int rowNumber);

}
