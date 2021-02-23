package nl.guyonmaissan.Garage.dbmodel;

import lombok.Getter;
import lombok.Setter;
import nl.guyonmaissan.Garage.model.ERole;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.List;

@Entity(name = "Role")
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Column(columnDefinition = "serial")
    @Getter
    @Setter
    private long id;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private ERole name;

    public Role() {
    }
}
