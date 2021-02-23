package nl.guyonmaissan.Garage.model;

import lombok.Getter;
import lombok.Setter;
import nl.guyonmaissan.Garage.dbmodel.Vehicle;
import nl.guyonmaissan.Garage.dbmodel.Workorder;

@Getter
@Setter
public class WorkorderVehicle {

    public Workorder workorder;

    public Vehicle vehicle;
}
