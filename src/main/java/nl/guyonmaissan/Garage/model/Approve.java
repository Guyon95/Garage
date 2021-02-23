package nl.guyonmaissan.Garage.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Approve {

    private Long woNumber;

    private List<WorkorderRow> workorderRows;
}
