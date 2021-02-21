package nl.guyonmaissan.Garage.service;

import nl.guyonmaissan.Garage.model.Labor;
import nl.guyonmaissan.Garage.repository.LaborRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
public class LaborServiceImpl implements LaborService{

    @Autowired
    private LaborRepository laborRepository;

    @Override
    public Collection<Labor> getAllLabor() {
        return null;
    }

    @Override
    public Labor getLaborById(Long id) {
        return null;
    }

    @Override
    public Labor getLaborByDescription(String description) {
        return null;
    }

    @Override
    public long createLabor(Labor labor) {
        return 0;
    }

    @Override
    public void updateLabor(Long id, Labor labor) {

    }

    @Override
    public void partialUpdateLabor(Long id, Map<String, String> fields) {

    }

    @Override
    public void deleteLabor(Long id) {

    }

    @Override
    public Labor getCheckCar(){
        Labor labor = laborRepository.findByDescription("Check the car");

        return labor;
    }

    @Override
    public Labor getRow(int laborNumber){
        Labor labor = laborRepository.findByLaborNumber(laborNumber);

        return labor;
    }
}
