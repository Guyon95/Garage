package nl.guyonmaissan.Garage.model;

import lombok.Getter;
import lombok.Setter;
import nl.guyonmaissan.Garage.payload.response.MessageResponse;
import org.springframework.http.ResponseEntity;

@Getter
@Setter
public class ReturnObject {

    public String message;

    public Object object;



}
