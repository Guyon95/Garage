package nl.guyonmaissan.Garage.service;

import nl.guyonmaissan.Garage.model.AddLabor;
import nl.guyonmaissan.Garage.model.AddPart;
import nl.guyonmaissan.Garage.model.Approve;
import nl.guyonmaissan.Garage.model.ETypeWorkorderRow;
import nl.guyonmaissan.Garage.model.EWorkorderStatus;
import nl.guyonmaissan.Garage.model.OtherAction;
import nl.guyonmaissan.Garage.dbmodel.Workorder;
import nl.guyonmaissan.Garage.dbmodel.WorkorderRow;
import nl.guyonmaissan.Garage.repository.WorkorderRepository;
import nl.guyonmaissan.Garage.repository.WorkorderRowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorkorderRowServiceImpl implements WorkorderRowService {

    @Autowired
    private WorkorderRowRepository workorderRowRepository;

    @Autowired
    LaborService laborService;

    @Autowired
    PartService partService;

    @Autowired
    private WorkorderRepository workorderRepository;

    @Override
    public List<nl.guyonmaissan.Garage.model.WorkorderRow> getWorkorderRowByWoNummer(Long woNumber) {

        List<nl.guyonmaissan.Garage.model.WorkorderRow> workorderRows = new ArrayList<>();

        if(woNumber !=0){
            Workorder workorder = workorderRepository.findByWoNumber(woNumber);

            if(workorder != null){
                List<WorkorderRow> dbWorkorderRows = workorderRowRepository.findWorkorderRowByWorkorder(workorder);

                for(WorkorderRow dbWorkorderRow : dbWorkorderRows){
                    workorderRows.add(CreateApiModel(dbWorkorderRow));
                }

                return workorderRows;
            }
        }
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
    public String approveWorkorderRows(Approve approve) {
        if(approve.getWoNumber() != null){
            Workorder workorder = workorderRepository.findByWoNumber(approve.getWoNumber());

            if(workorder != null) {

                for (nl.guyonmaissan.Garage.model.WorkorderRow workorderRow : approve.getWorkorderRows()) {

                    if(workorderRow.getDescription() != null || workorderRow.getDescription() != "") {
                        WorkorderRow updateRow = workorderRowRepository.findWorkorderRowByWorkorderAndDescription(workorder,workorderRow.getDescription());

                        if(updateRow != null) {

                            if (workorderRow.getCustomerAgreed().equals(true)){
                                updateRow.setCustomerAgreed(workorderRow.getCustomerAgreed());

                                workorderRowRepository.save(updateRow);
                            }
                            else{

                                workorderRowRepository.deleteById(updateRow.getId());
                            }

                        }
                        else{
                            return "Couldn't find any workorderRows with the description: " + workorderRow.getDescription();
                        }

                    }
                }

                List<WorkorderRow> workorderRows = workorderRowRepository.findWorkorderRowByWorkorder(workorder);

                if(workorderRows.stream().count() > 1) {
                    workorder.setStatus(EWorkorderStatus.READY_TO_START);
                }
                else{
                    workorder.setStatus(EWorkorderStatus.DO_NOT_EXCUTE);
                }

                workorderRepository.save(workorder);

                return "Succesfully updated the workorder rows.";
            }

            return "Couldn't find any workorder with the ginen wo number.";
        }

        return "Please insert a wo number.";
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

    @Override
    public nl.guyonmaissan.Garage.model.WorkorderRow CreateApiModel(WorkorderRow dbWorkorderRow) {

        nl.guyonmaissan.Garage.model.WorkorderRow workorderRow = new nl.guyonmaissan.Garage.model.WorkorderRow();
        workorderRow.setType(dbWorkorderRow.getType());
        workorderRow.setPrice(dbWorkorderRow.getPrice());
        workorderRow.setDescription(dbWorkorderRow.getDescription());
        workorderRow.setAmount(dbWorkorderRow.getAmount());
        workorderRow.setCustomerAgreed(dbWorkorderRow.getCustomerAgreed());


        return workorderRow;
    }

    @Override
    public nl.guyonmaissan.Garage.model.WorkorderRow CreateInvoiceRow(WorkorderRow dbWorkorderRow) {

        nl.guyonmaissan.Garage.model.WorkorderRow workorderRow = new nl.guyonmaissan.Garage.model.WorkorderRow();

        workorderRow.setType(dbWorkorderRow.getType());
        workorderRow.setPrice(dbWorkorderRow.getPrice() * 1.21);
        workorderRow.setDescription(dbWorkorderRow.getDescription());
        workorderRow.setAmount(dbWorkorderRow.getAmount());
        workorderRow.setCustomerAgreed(dbWorkorderRow.getCustomerAgreed());


        return workorderRow;
    }

}
