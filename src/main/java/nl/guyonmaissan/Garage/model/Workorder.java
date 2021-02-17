package nl.guyonmaissan.Garage.model;

import jdk.jfr.StackTrace;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
    private Long woNumber;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", referencedColumnName = "id")
    @Getter
    @Setter
    private Vehicle vehicle;

    @Getter
    @Setter
    private LocalDateTime created;

    @Getter
    @Setter
    private LocalDateTime modified;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private EWorkorderStatus status;

    @Getter
    @Setter
    private Long invoiceNumber;

    @Getter
    @Setter
    private Boolean customerAgreed;






}
