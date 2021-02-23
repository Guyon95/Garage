package nl.guyonmaissan.Garage.model;

import lombok.Getter;
import lombok.Setter;
import nl.guyonmaissan.Garage.dbmodel.Workorder;

import java.util.List;

@Getter
@Setter
public class AddWorkorderRow {

    public Workorder workorder;

    public List<AddLabor> addLabors;

    public List<AddPart> addParts;

}
