package nl.guyonmaissan.Garage.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Setter
@Getter
public class AddPart {


    @NotBlank(message = "amount must be filled.")
    public int amount;

    @NotBlank(message = "part number must be filled.")
    public int partNumber;
}
