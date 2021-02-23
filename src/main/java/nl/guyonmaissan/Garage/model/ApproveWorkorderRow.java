package nl.guyonmaissan.Garage.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ApproveWorkorderRow {

    private String description;

    @Getter
    @Setter
    private boolean customerAgreed;

}
