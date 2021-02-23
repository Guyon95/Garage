package nl.guyonmaissan.Garage.model;

import lombok.Getter;
import lombok.Setter;

public class Labor {

    @Getter
    @Setter
    private int laborNumber;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private double price;
}
