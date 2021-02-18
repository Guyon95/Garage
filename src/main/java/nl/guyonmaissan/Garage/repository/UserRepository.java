package nl.guyonmaissan.Garage.repository;

import nl.guyonmaissan.Garage.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
