package nl.guyonmaissan.Garage.controller;

import nl.guyonmaissan.Garage.model.ReturnObject;
import nl.guyonmaissan.Garage.service.LaborService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/labor")
@PreAuthorize("hasRole('BACKOFFICE')")
public class LaborController {

    @Autowired
    LaborService laborService;

    @PostMapping(value = "/create")
    @PreAuthorize("hasRole('BACKOFFICE')")
    public ResponseEntity<Object> createPart(@RequestBody nl.guyonmaissan.Garage.model.Labor labor) {

        ReturnObject returnObject = laborService.createLabor(labor);

        return ResponseEntity.ok().body(returnObject);
    }
}
