package nl.guyonmaissan.Garage.service;

import nl.guyonmaissan.Garage.dbmodel.Labor;
import nl.guyonmaissan.Garage.model.ReturnObject;

import java.util.Collection;
import java.util.Map;

public interface LaborService {

    ReturnObject createLabor(Labor labor);
    Labor getCheckCar();
    Labor getRow(int rowNumber);

}
