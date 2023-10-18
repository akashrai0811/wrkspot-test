package customer.demo.demo.repository;

import customer.demo.demo.entity.Customer;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    List<Customer> findByFirstName(String firstName);
    List<Customer> findByAddressesCity(String city);
    List<Customer> findByAddressesState(String state);

    @NonNull
    List<Customer> findAll();

}

