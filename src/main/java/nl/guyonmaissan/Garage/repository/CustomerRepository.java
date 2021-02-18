package nl.guyonmaissan.Garage.repository;

import nl.guyonmaissan.Garage.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Long> {

}
