package nl.guyonmaissan.Garage.repository;

import nl.guyonmaissan.Garage.model.ERole;
import nl.guyonmaissan.Garage.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole name);

}