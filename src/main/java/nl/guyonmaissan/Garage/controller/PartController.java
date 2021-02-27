package nl.guyonmaissan.Garage.controller;

import nl.guyonmaissan.Garage.dbmodel.Part;
import nl.guyonmaissan.Garage.model.ReturnObject;
import nl.guyonmaissan.Garage.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/part")
@PreAuthorize("hasRole('BACKOFFICE')")
public class PartController {

    @Autowired
    PartService partService;

    @PostMapping(value = "/create")
    @PreAuthorize("hasRole('BACKOFFICE')")
    public ResponseEntity<Object> createPart(@RequestBody Part part) {

        ReturnObject returnObject = partService.createPart(part);

        return ResponseEntity.ok().body(returnObject);
    }
}
