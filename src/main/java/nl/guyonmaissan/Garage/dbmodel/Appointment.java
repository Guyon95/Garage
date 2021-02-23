package nl.guyonmaissan.Garage.dbmodel;

import lombok.Getter;
import lombok.Setter;
import nl.guyonmaissan.Garage.dbmodel.Vehicle;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    public Long id;

    @Getter
    @Setter
    public LocalDateTime appointment;

    @OneToOne
    @Getter
    @Setter
    public Vehicle vehicle;

    @Getter
    @Setter
    public LocalDateTime created;

    @Getter
    @Setter
    public LocalDateTime modified;

}
