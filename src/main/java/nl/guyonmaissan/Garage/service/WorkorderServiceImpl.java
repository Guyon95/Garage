package nl.guyonmaissan.Garage.service;


import nl.guyonmaissan.Garage.dbmodel.WorkorderRow;
import nl.guyonmaissan.Garage.exceptions.RecordNotFoundException;
import nl.guyonmaissan.Garage.model.AddLabor;
import nl.guyonmaissan.Garage.model.AddPart;
import nl.guyonmaissan.Garage.model.AddWorkorderRow;
import nl.guyonmaissan.Garage.model.EWorkorderStatus;
import nl.guyonmaissan.Garage.model.Invoice;
import nl.guyonmaissan.Garage.model.OtherAction;
import nl.guyonmaissan.Garage.model.ReturnObject;
import nl.guyonmaissan.Garage.dbmodel.Vehicle;
import nl.guyonmaissan.Garage.dbmodel.Workorder;
import nl.guyonmaissan.Garage.model.WorkorderVehicle;
import nl.guyonmaissan.Garage.repository.VehicleRepository;
import nl.guyonmaissan.Garage.repository.WorkorderRepository;
import nl.guyonmaissan.Garage.repository.WorkorderRowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class WorkorderServiceImpl implements WorkorderService {

    @Autowired
    private WorkorderRepository workorderRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    WorkorderRowRepository workorderRowRepository;

    @Autowired
    private WorkorderRowService workorderRowService;

    @Autowired
    private PartService partService;

    @Override
    public List<Workorder> getAllWorkorders() {
        return workorderRepository.findAll();
    }


    @Override
    public ReturnObject createWorkorder(WorkorderVehicle workorderVehicle) {
        ReturnObject returnObject = new ReturnObject();
        Vehicle vehicle = vehicleRepository.findByLicensePlate(workorderVehicle.getVehicle().getLicensePlate());

        if (vehicle != null) {
            Workorder lastWorkorder = workorderRepository.findTopByOrderByCreatedDesc();
            Long newWoNumber = 10000L;

            if (lastWorkorder != null) {
                newWoNumber = lastWorkorder.getWoNumber() + 1;
            }

            Workorder workorder = workorderVehicle.getWorkorder();
            workorder.setWoNumber(newWoNumber);
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

        if (addWorkorderRow.getWorkorder().getWoNumber() != null) {
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

        if (otherAction.getWorkorder().getWoNumber() != null) {
            Workorder workorder = workorderRepository.findByWoNumber(otherAction.getWorkorder().getWoNumber());

            if (workorder != null) {
                workorderRowService.createWorkorderRow(otherAction, workorder);
                return "Addded other action to the workorder!";
            }
            return "Couldn't find the workorder.";
        }

        return "Please fill the wo number.";
    }

    @Override
    public String repairsExcuted(Long woNumber) {
        Workorder workorder = workorderRepository.findByWoNumber(woNumber);

        if (workorder != null) {
            workorder.setStatus(EWorkorderStatus.REPAIRS_EXCUTED);
            workorderRepository.save(workorder);

            return "Repairs has been succesfully excuted!";
        }
        return "Couldn't find any workorders with the WO number: " + woNumber.toString();
    }

    @Override
    public List<Workorder> getFinishedWos() {

        List<Workorder> workorders = new ArrayList<>();

        workorders.addAll(workorderRepository.findWorkordersByStatus(EWorkorderStatus.DO_NOT_EXCUTE));
        workorders.addAll(workorderRepository.findWorkordersByStatus(EWorkorderStatus.REPAIRS_EXCUTED));

        return workorders;
    }

    @Override
    public ReturnObject createInvoice(Long woNumber) {
        ReturnObject returnObject = new ReturnObject();

        if (woNumber != 0) {
            Workorder workorder = workorderRepository.findByWoNumber(woNumber);

            if(workorder != null){
            List<WorkorderRow> dbWorkorderRows = workorderRowRepository.findWorkorderRowByWorkorder(workorder);

            List<nl.guyonmaissan.Garage.model.WorkorderRow> workorderRows = new ArrayList<>();

            double total = 0;

            for(WorkorderRow dbWorkorderRow : dbWorkorderRows){

                nl.guyonmaissan.Garage.model.WorkorderRow workorderRow = workorderRowService.CreateInvoiceRow(dbWorkorderRow);
                workorderRows.add(workorderRow);

                total += workorderRow.getPrice();

            }

            Workorder lastInvoiceNumber = workorderRepository.findTopByOrderByInvoiceNumberDesc();

            Long newInvoiceNumber = 1000L;

            if(lastInvoiceNumber.getInvoiceNumber() != null){
                newInvoiceNumber = lastInvoiceNumber.getInvoiceNumber() +1;
            }

                Invoice invoice = new Invoice();

                invoice.setWorkorder(workorder);
                invoice.setWorkorderRows(workorderRows);
                invoice.setInvoiceNumber(newInvoiceNumber);
                invoice.setTotal(total);


                //change stock value
                for(nl.guyonmaissan.Garage.model.WorkorderRow stockRow : workorderRows){
                    partService.changeStock(stockRow);
                }

                returnObject.setObject(invoice);
                returnObject.setMessage("Created succesfully a invoice.");

                workorder.setStatus(EWorkorderStatus.INVOICED);
                workorder.setInvoiceNumber(newInvoiceNumber);
                workorderRepository.save(workorder);

                return returnObject;
            }

            returnObject.setMessage("Couldn't find a workorder with the given WO number: " + woNumber);

            return returnObject;

        }

        returnObject.setMessage("Please insert a valid value for WO number! ");
        return returnObject;
    }

    @Override
    public String updateAppoinment(Workorder workorder) {
        Workorder updateWO = workorderRepository.findByWoNumber(workorder.getWoNumber());

        if (updateWO != null) {
            if(!workorder.getAppointment().isBefore(LocalDateTime.now())){
                updateWO.setAppointment(workorder.getAppointment());
                workorderRepository.save(updateWO);

                return "Appointment has been succesfully updated!";
            }


            return "Couldn't update appointment because it's in the past!";
        }
        return "Couldn't find any workorders with the WO number: " + workorder.getWoNumber().toString();
    }

    @Override
    public String customerPaid(Long woNumber) {
        if(woNumber != 0){
            Workorder workorder = workorderRepository.findByWoNumber(woNumber);

            if(workorder != null){
                workorder.setStatus(EWorkorderStatus.PAID);
                workorderRepository.save(workorder);

                return "The customer paid the workorder.";
            }

            return "Couldn't find a workorder with the given WO number: " +woNumber;
        }

        return "Please insert a valid WO number.";
    }
}

