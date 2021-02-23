package nl.guyonmaissan.Garage.model;

import lombok.Getter;
import lombok.Setter;
import nl.guyonmaissan.Garage.dbmodel.Workorder;

@Getter
@Setter
public class OtherAction {

    private Workorder workorder;

    private String description;

    private double price;

    private int amount;
}
