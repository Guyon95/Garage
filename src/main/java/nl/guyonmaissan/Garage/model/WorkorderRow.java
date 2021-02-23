package nl.guyonmaissan.Garage.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WorkorderRow {

    private ETypeWorkorderRow type;

    private String description;

    private double price;

    private int amount;

    private Boolean customerAgreed;
}
