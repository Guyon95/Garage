package nl.guyonmaissan.Garage.service;

import nl.guyonmaissan.Garage.dbmodel.Customer;
import nl.guyonmaissan.Garage.dbmodel.Vehicle;
import nl.guyonmaissan.Garage.dbmodel.Workorder;
import nl.guyonmaissan.Garage.model.EWorkorderStatus;
import nl.guyonmaissan.Garage.model.ReturnObject;
import nl.guyonmaissan.Garage.model.WorkorderVehicle;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WorkorderServiceImplTest {

    Workorder workorder;
    List<Workorder> workorders;

    Vehicle vehicle;

    @BeforeEach
    public void setUp() {
        workorder = new Workorder();
        workorders = new ArrayList<>();
        vehicle = new Vehicle();
    }

    @Test
    void getAllWorkorders() {
        workorders.add(workorder);

        assertEquals(1,workorders.stream().count());
    }

    @Test
    void createWorkorder() {
        //arrange
        ReturnObject returnObject = new ReturnObject();
        WorkorderVehicle workorderVehicle = new WorkorderVehicle();
        workorderVehicle.setWorkorder(workorder);



        //act
        if (vehicle != null) {
            Workorder lastWorkorder = null;

            Long newWoNumber = 10000L;

            if (lastWorkorder != null) {
                newWoNumber = lastWorkorder.getWoNumber() + 1;
            }

            Workorder workorder = workorderVehicle.getWorkorder();

            workorder.setWoNumber(newWoNumber);
            workorder.setVehicle(vehicle);
            workorder.setCreated(LocalDateTime.now());
            workorder.setModified(LocalDateTime.now());


            returnObject.setMessage("Created succesfull a workorder!");


        }

        //assert
        assertEquals(10000L,workorder.getWoNumber());
    }


    @Test
    void customerPaid() {
        //arrange
        String message;
        Long woNumber = 1L;
        workorder.setWoNumber(1L);
        workorders.add(workorder);

        //act
        if(woNumber != 0){
            Workorder workorder = workorders.stream().filter(x -> x.getWoNumber().equals(woNumber)).findFirst().orElse(null);

            if(workorder != null){
                workorder.setStatus(EWorkorderStatus.PAID);

                message = "The customer paid the workorder.";
            }

            message =  "Couldn't find a workorder with the given WO number: " + woNumber;
        }

        message = "Please insert a valid WO number.";

        //assert
        assertEquals(EWorkorderStatus.PAID,workorder.getStatus());


    }
}