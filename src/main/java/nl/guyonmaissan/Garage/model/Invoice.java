package nl.guyonmaissan.Garage.model;


import lombok.Getter;
import lombok.Setter;
import nl.guyonmaissan.Garage.dbmodel.Workorder;

import java.util.List;

public class Invoice {

    @Getter
    @Setter
    public Long invoiceNumber;

    @Getter
    @Setter
    public Workorder workorder;

    @Getter
    @Setter
    public List<WorkorderRow> workorderRows;

    @Getter
    @Setter
    public double total;
}
