package nl.guyonmaissan.Garage.repository;

import nl.guyonmaissan.Garage.dbmodel.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer,Long> {

}
