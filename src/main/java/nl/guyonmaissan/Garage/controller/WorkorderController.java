package nl.guyonmaissan.Garage.controller;


import nl.guyonmaissan.Garage.model.AddWorkorderRow;
import nl.guyonmaissan.Garage.model.OtherAction;
import nl.guyonmaissan.Garage.model.ReturnObject;
import nl.guyonmaissan.Garage.model.WorkorderVehicle;
import nl.guyonmaissan.Garage.payload.response.MessageResponse;
import nl.guyonmaissan.Garage.service.WorkorderService;
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
@RequestMapping(value = "/workorder")
@PreAuthorize("hasRole('ADMINISTRATOR') or hasRole('MECHANIC')")
public class WorkorderController {

    @Autowired
    WorkorderService workorderService;



    @GetMapping(value = "")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<Object> getCustomers() {
        return ResponseEntity.ok().body(workorderService.getAllWorkorders());
    }


    @PostMapping(value = "/create")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<Object> createWorkorder(@RequestBody WorkorderVehicle workorderVehicle) {
        ReturnObject returnObject = workorderService.createWorkorder(workorderVehicle);

        return ResponseEntity.ok().body(returnObject);
    }

    @PostMapping(value = "/update")
    @PreAuthorize("hasRole('MECHANIC')")
    public ResponseEntity<MessageResponse> updateWorkorder(@RequestBody AddWorkorderRow addWorkorderRow) {
        return ResponseEntity.ok(new MessageResponse(workorderService.updateWorkorder(addWorkorderRow)));
    }

    @PostMapping(value = "/other")
    @PreAuthorize("hasRole('MECHANIC')")
    public ResponseEntity<MessageResponse> addOtherAction(@RequestBody OtherAction otherAction) {
        return ResponseEntity.ok(new MessageResponse(workorderService.addOtherAction(otherAction)));
    }

    @GetMapping(value = "/excute/{woNumber}")
    @PreAuthorize("hasRole('MECHANIC')")
    public ResponseEntity<MessageResponse> repairsExcuted(@PathVariable ("woNumber") Long woNumber) {
        return ResponseEntity.ok().body(new MessageResponse(workorderService.repairsExcuted(woNumber)));
    }

    @GetMapping(value = "/call")
    @PreAuthorize("hasRole('ADMINISTRATOR')")
    public ResponseEntity<Object> getFinishedWOs() {
        return ResponseEntity.ok().body(workorderService.getFinishedWos());
    }

}

