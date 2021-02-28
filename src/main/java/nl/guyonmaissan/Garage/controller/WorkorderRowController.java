package nl.guyonmaissan.Garage.controller;

import nl.guyonmaissan.Garage.model.Approve;

import nl.guyonmaissan.Garage.payload.response.MessageResponse;
import nl.guyonmaissan.Garage.service.WorkorderRowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/workorderrow")
@PreAuthorize("hasRole('MECHANIC')")
public class WorkorderRowController {

    @Autowired
    WorkorderRowService workorderRowService;

    @GetMapping(value = "/{woNumber}")
    @PreAuthorize("hasRole('MECHANIC')")
    public ResponseEntity<Object> getWorkorderRows(@PathVariable("woNumber") Long woNumber) {
        return ResponseEntity.ok().body(workorderRowService.getWorkorderRowByWoNummer(woNumber));
    }

    @PostMapping(value = "/approve")
    @PreAuthorize("hasRole('MECHANIC')")
    public ResponseEntity<MessageResponse> approveWorkorderRows(@RequestBody Approve approve) {
        return ResponseEntity.ok(new MessageResponse(workorderRowService.approveWorkorderRows(approve)));
    }
}
