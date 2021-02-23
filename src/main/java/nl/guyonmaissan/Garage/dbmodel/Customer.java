package nl.guyonmaissan.Garage.dbmodel;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.List;


@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;

    @Getter
    @Setter
    @Column(name = "firstName")
    private String firstName;

    @Getter
    @Setter
    @Column(name = "lastName")
    private String lastName;

    @Getter
    @Setter
    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Getter
    @Setter
    private LocalDateTime created;

    @Getter
    @Setter
    private LocalDateTime modified;



}
