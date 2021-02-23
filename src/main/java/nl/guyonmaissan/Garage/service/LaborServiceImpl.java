package nl.guyonmaissan.Garage.service;

import nl.guyonmaissan.Garage.dbmodel.Labor;
import nl.guyonmaissan.Garage.dbmodel.Part;
import nl.guyonmaissan.Garage.model.ReturnObject;
import nl.guyonmaissan.Garage.repository.LaborRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
    public ReturnObject createLabor(nl.guyonmaissan.Garage.model.Labor labor) {
        ReturnObject returnObject = new ReturnObject();

        Labor dbLabor = laborRepository.findByLaborNumberOrDescription(labor.getLaborNumber(), labor.getDescription());

        if(dbLabor == null){

            Labor newLabor = new Labor();
            newLabor.setDescription(labor.getDescription());
            newLabor.setLaborNumber(labor.getLaborNumber());
            newLabor.setPrice(labor.getPrice());
            newLabor.setCreated(LocalDateTime.now());
            newLabor.setModified(LocalDateTime.now());

            laborRepository.save(newLabor);

            returnObject.setMessage("Succesfully added a labor to the database.");
            returnObject.setObject(newLabor);

            return returnObject;
        }
        returnObject.setMessage("Labor already exists!");

        return returnObject;
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
