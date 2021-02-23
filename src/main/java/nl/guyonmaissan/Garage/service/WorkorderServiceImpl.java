package nl.guyonmaissan.Garage.service;

import nl.guyonmaissan.Garage.exceptions.RecordNotFoundException;
import nl.guyonmaissan.Garage.model.AddLabor;
import nl.guyonmaissan.Garage.model.AddPart;
import nl.guyonmaissan.Garage.model.AddWorkorderRow;
import nl.guyonmaissan.Garage.model.EWorkorderStatus;
import nl.guyonmaissan.Garage.model.OtherAction;
import nl.guyonmaissan.Garage.model.ReturnObject;
import nl.guyonmaissan.Garage.dbmodel.Vehicle;
import nl.guyonmaissan.Garage.dbmodel.Workorder;
import nl.guyonmaissan.Garage.model.WorkorderVehicle;
import nl.guyonmaissan.Garage.repository.VehicleRepository;
import nl.guyonmaissan.Garage.repository.WorkorderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.List;


@Service
public class WorkorderServiceImpl implements WorkorderService {

    @Autowired
    private WorkorderRepository workorderRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private WorkorderRowService workorderRowService;

    @Override
    public List<Workorder> getAllWorkorders() {
        return workorderRepository.findAll();
    }

    @Override
    public Workorder getWorkorderById(Long id) {
        return workorderRepository.findById(id).orElse(null);
    }

    @Override
    public Workorder getWorkorderByWoNumber(Long woNumber) {
        return workorderRepository.findByWoNumber(woNumber); //.orElse(null);
    }

    @Override
    public ReturnObject createWorkorder(WorkorderVehicle workorderVehicle) {
        ReturnObject returnObject = new ReturnObject();
        Vehicle vehicle = vehicleRepository.findByLicensePlate(workorderVehicle.getVehicle().getLicensePlate());

        if(vehicle != null) {
            Workorder workorder = workorderVehicle.getWorkorder();
            workorder.setVehicle(vehicle);
            workorder.setCreated(LocalDateTime.now());
            workorder.setModified(LocalDateTime.now());

            Workorder storedWorkorder = workorderRepository.save(workorder);

            workorderRowService.AddCarCheck(storedWorkorder);

            returnObject.setObject(storedWorkorder);
            returnObject.setMessage("Created succesfull a workorder!");

            return returnObject;
        }

        returnObject.setMessage("Couldn't find the given license plate. Please create a vehicle with a customer attached.");

        return returnObject;
    }

    @Override
    public String updateWorkorder(AddWorkorderRow addWorkorderRow) {

        if(addWorkorderRow.getWorkorder().getWoNumber() != null) {
            Workorder workorder = workorderRepository.findByWoNumber(addWorkorderRow.getWorkorder().getWoNumber());

            //ToDo null afvangen
            if (addWorkorderRow.getAddParts() != null) {
                for (AddPart part : addWorkorderRow.addParts) {
                    workorderRowService.AddPart(part, workorder);
                }
            }

            if (addWorkorderRow.getAddLabors() != null) {
                for (AddLabor labor : addWorkorderRow.addLabors) {
                    workorderRowService.AddLabor(labor, workorder);
                }
            }

            workorder.setStatus(EWorkorderStatus.AWAITING_APPROVAL);
            workorderRepository.save(workorder);

            Long count = addWorkorderRow.getAddLabors().stream().count() + addWorkorderRow.getAddParts().stream().count();

            return "Succesfully added " + count + " row(s).";

        }
        return "Couldn't find a workorder.";
    }

    @Override
    public String addOtherAction(OtherAction otherAction) {

        if(otherAction.getWorkorder().getWoNumber() != null){
            Workorder workorder = workorderRepository.findByWoNumber(otherAction.getWorkorder().getWoNumber());

            if(workorder != null){
                workorderRowService.createWorkorderRow(otherAction,workorder);
                return "Addded other action to the workorder!";
            }
            return "Couldn't find the workorder.";
        }

        return "Please fill the wo number.";
    }



    @Override
    public void deleteWorkorder(Long id) {
        if (!workorderRepository.existsById(id)) { throw new RecordNotFoundException(); }
        workorderRepository.deleteById(id);
    }


}
