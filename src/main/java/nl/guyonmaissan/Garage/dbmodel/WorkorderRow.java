package nl.guyonmaissan.Garage.dbmodel;

import lombok.Getter;
import lombok.Setter;
import nl.guyonmaissan.Garage.dbmodel.Workorder;
import nl.guyonmaissan.Garage.model.ETypeWorkorderRow;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
public class WorkorderRow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @OneToOne
    @Getter
    @Setter
    @JoinColumn(name = "workorder_id", referencedColumnName = "id")
    private Workorder workorder;

    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    private ETypeWorkorderRow type;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private double price;

    @Getter
    @Setter
    private int amount;

    @Getter
    @Setter
    private LocalDateTime created;

    @Getter
    @Setter
    private LocalDateTime modified;

    @Getter
    @Setter
    private Boolean customerAgreed;
}
