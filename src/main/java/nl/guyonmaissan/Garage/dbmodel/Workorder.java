package nl.guyonmaissan.Garage.dbmodel;

import lombok.Getter;
import lombok.Setter;
import nl.guyonmaissan.Garage.dbmodel.Vehicle;
import nl.guyonmaissan.Garage.model.EWorkorderStatus;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
public class Workorder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column(nullable = false)
    private Long woNumber;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    @Getter
    @Setter
    private Vehicle vehicle;

    @Getter
    @Setter
    @Column(nullable = false)
    private LocalDateTime appointment;

    @Getter
    @Setter
    @Column(nullable = false)
    private LocalDateTime created;

    @Getter
    @Setter
    @Column(nullable = false)
    private LocalDateTime modified;

    @Getter
    @Setter
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EWorkorderStatus status;

    @Getter
    @Setter
    private Long invoiceNumber;








}
