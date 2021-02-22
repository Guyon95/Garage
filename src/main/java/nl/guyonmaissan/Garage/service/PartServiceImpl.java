package nl.guyonmaissan.Garage.service;

import nl.guyonmaissan.Garage.model.Labor;
import nl.guyonmaissan.Garage.model.Part;
import nl.guyonmaissan.Garage.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Map;

@Service
public class PartServiceImpl implements PartService {

    @Autowired
    PartRepository partRepository;

    @Override
    public Collection<Part> getAllParts() {
        return null;
    }

    @Override
    public Part getPartsById(Long id) {
        return null;
    }

    @Override
    public Part getParts(String description) {
        return null;
    }

    @Override
    public long createParts(Part part) {
        return 0;
    }

    @Override
    public void updateParts(Long id, Part part) {

    }

    @Override
    public void partialUpdateParts(Long id, Map<String, String> fields) {

    }

    @Override
    public void deleteParts(Long id) {

    }

    @Override
    public Part getRow(int partNumber){
        Part part = partRepository.findByPartNumber(partNumber);

        return part;
    }
}
