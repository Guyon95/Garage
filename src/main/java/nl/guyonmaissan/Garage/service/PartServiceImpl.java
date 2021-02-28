package nl.guyonmaissan.Garage.service;

import nl.guyonmaissan.Garage.dbmodel.Part;
import nl.guyonmaissan.Garage.model.ReturnObject;
import nl.guyonmaissan.Garage.model.WorkorderRow;
import nl.guyonmaissan.Garage.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;

@Service
public class PartServiceImpl implements PartService {

    @Autowired
    PartRepository partRepository;

    @Override
    public ReturnObject createPart(Part part) {
        ReturnObject returnObject = new ReturnObject();

        Part dbPart = partRepository.findByPartNumberOrDescription(part.getPartNumber(), part.getDescription());

        if(dbPart == null){

            Part newPart = new Part();
            newPart.setDescription(part.getDescription());
            newPart.setPartNumber(part.getPartNumber());
            newPart.setPrice(part.getPrice());
            newPart.setStock(part.getStock());
            newPart.setCreated(LocalDateTime.now());
            newPart.setModified(LocalDateTime.now());

            partRepository.save(newPart);

            returnObject.setMessage("Succesfully added a part to the database.");
            returnObject.setObject(newPart);

            return returnObject;
        }
        returnObject.setMessage("Part already exists!");

        return returnObject;
    }

    @Override
    public Part getRow(int partNumber){
        Part part = partRepository.findByPartNumber(partNumber);

        return part;
    }

    @Override
    public void changeStock(WorkorderRow workorderRow) {
        Part part = partRepository.findByDescription(workorderRow.getDescription());

        if(part != null){
            part.setStock(part.getStock() - workorderRow.getAmount());
            part.setModified(LocalDateTime.now());
            partRepository.save(part);

        }
    }
}
