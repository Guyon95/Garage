package nl.guyonmaissan.Garage.model;

import lombok.Getter;
import lombok.Setter;

public class Part {

    @Getter
    @Setter
    private int partNumber;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private double price;
}
