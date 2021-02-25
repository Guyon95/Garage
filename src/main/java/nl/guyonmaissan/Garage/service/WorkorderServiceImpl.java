package nl.guyonmaissan.Garage.service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfWriter;
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


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
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
    public void deleteWorkorder(Long id) {
        if (!workorderRepository.existsById(id)) {
            throw new RecordNotFoundException();
        }
        workorderRepository.deleteById(id);
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

            if(lastInvoiceNumber != null){
                newInvoiceNumber = lastInvoiceNumber.getInvoiceNumber() +1;
            }

                Invoice invoice = new Invoice();

                invoice.setWorkorder(workorder);
                invoice.setWorkorderRows(workorderRows);
                invoice.setInvoiceNumber(newInvoiceNumber);
                invoice.setTotal(total);

                returnObject.setObject(invoice);
                returnObject.setMessage("Created succesfully a invoice.");

                workorder.setStatus(EWorkorderStatus.INVOICED);
                workorderRepository.save(workorder);

                return returnObject;
            }

            returnObject.setMessage("Couldn't find a workorder with the given WO number: " + woNumber);

            return returnObject;

        }

        returnObject.setMessage("Please insert a valid value for WO number! ");
        return returnObject;
    }
}

