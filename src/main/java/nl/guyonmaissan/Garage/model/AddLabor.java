package nl.guyonmaissan.Garage.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class AddLabor {

    @NotBlank(message = "amount must be filled.")
    public int amount;

    @NotBlank(message = "labor number must be filled.")
    public int laborNumber;
}
