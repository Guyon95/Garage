package nl.guyonmaissan.Garage.service;

import nl.guyonmaissan.Garage.model.AddLabor;
import nl.guyonmaissan.Garage.model.AddPart;
import nl.guyonmaissan.Garage.model.ETypeWorkorderRow;
import nl.guyonmaissan.Garage.model.OtherAction;
import nl.guyonmaissan.Garage.model.Workorder;
import nl.guyonmaissan.Garage.model.WorkorderRow;
import nl.guyonmaissan.Garage.repository.WorkorderRowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;

@Service
public class WorkorderRowServiceImpl implements WorkorderRowService {

    @Autowired
    private WorkorderRowRepository workorderRowRepository;

    @Autowired
    LaborService laborService;

    @Autowired
    PartService partService;

    @Override
    public Collection<WorkorderRow> getAllWorkorderRows() {
        return null;
    }

    @Override
    public WorkorderRow getWorkorderRowById(Long id) {
        return null;
    }

    @Override
    public Collection<WorkorderRow> getWorkorderRowByWoNummer(String description) {
        return null;
    }

    @Override
    public void createWorkorderRow(OtherAction otherAction, Workorder workorder) {
        WorkorderRow workorderRow = new WorkorderRow();

        workorderRow.setWorkorder(workorder);
        workorderRow.setAmount(otherAction.getAmount());
        workorderRow.setCustomerAgreed(false);
        workorderRow.setDescription(otherAction.getDescription());
        workorderRow.setPrice(otherAction.getPrice());
        workorderRow.setModified(LocalDateTime.now());
        workorderRow.setCreated(LocalDateTime.now());
        workorderRow.setType(ETypeWorkorderRow.LABOR);

        workorderRowRepository.save(workorderRow);
    }

    @Override
    public void updateWorkorderRow(Long id, WorkorderRow woNummer) {

    }

    @Override
    public void deleteWorkorderRow(Long id) {

    }

    @Override
    public void AddCarCheck(Workorder workorder){

        WorkorderRow workorderRow = new WorkorderRow();
        workorderRow.setWorkorder(workorder);
        workorderRow.setAmount(1);
        workorderRow.setCustomerAgreed(true);
        workorderRow.setCreated(LocalDateTime.now());
        workorderRow.setModified(LocalDateTime.now());
        workorderRow.setDescription(laborService.getCheckCar().getDescription());
        workorderRow.setPrice(laborService.getCheckCar().getPrice());
        workorderRow.setType(ETypeWorkorderRow.LABOR);

        workorderRowRepository.save(workorderRow);
    }

    @Override
    public void AddLabor(AddLabor labor, Workorder workorder){

        WorkorderRow workorderRow = new WorkorderRow();
        workorderRow.setWorkorder(workorder);
        workorderRow.setAmount(labor.getAmount());
        workorderRow.setCustomerAgreed(false);
        workorderRow.setCreated(LocalDateTime.now());
        workorderRow.setModified(LocalDateTime.now());
        workorderRow.setDescription(laborService.getRow(labor.getLaborNumber()).getDescription());
        workorderRow.setPrice(laborService.getRow(labor.getLaborNumber()).getPrice());
        workorderRow.setType(ETypeWorkorderRow.LABOR);

        workorderRowRepository.save(workorderRow);
    }

    @Override
    public void AddPart(AddPart part, Workorder workorder){

        WorkorderRow workorderRow = new WorkorderRow();

        workorderRow.setWorkorder(workorder);
        workorderRow.setAmount(part.getAmount());
        workorderRow.setCustomerAgreed(false);
        workorderRow.setCreated(LocalDateTime.now());
        workorderRow.setModified(LocalDateTime.now());
        workorderRow.setDescription(partService.getRow(part.getPartNumber()).getDescription());
        workorderRow.setPrice(partService.getRow(part.getPartNumber()).getPrice());
        workorderRow.setType(ETypeWorkorderRow.PART);

        workorderRowRepository.save(workorderRow);
    }

}
