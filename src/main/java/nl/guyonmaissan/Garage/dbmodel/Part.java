package nl.guyonmaissan.Garage.dbmodel;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Part {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    private int partNumber;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private double price;

    @Getter
    @Setter
    private LocalDateTime created;

    @Getter
    @Setter
    private LocalDateTime modified;
}
