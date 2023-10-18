package customer.demo.demo.service;

import customer.demo.demo.entity.Customer;
import customer.demo.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository; // Inject the CustomerRepository

    @Override
    public Customer createCustomer(Customer customer) {
        // Implement the logic to create a customer (e.g., validation, saving to the repository)
        return customerRepository.save(customer);
    }

    @Override
    public List<Customer> getCustomers(String firstName, String city, String state) {
        // Implement the logic to retrieve customers based on the provided filters
        if (firstName != null && city != null && state != null) {
            return customerRepository.findAll();
        } else if (firstName != null) {
            return customerRepository.findByFirstName(firstName);
        } else if (city != null) {
            return customerRepository.findByAddressesCity(city);
        } else if (state != null) {
            return customerRepository.findByAddressesState(state);
        } else {
            return customerRepository.findAll();
        }
    }
}
