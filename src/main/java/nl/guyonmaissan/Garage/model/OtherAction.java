package nl.guyonmaissan.Garage.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtherAction {

    private Workorder workorder;

    private String description;

    private double price;

    private int amount;
}
