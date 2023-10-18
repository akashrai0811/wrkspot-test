package customer.demo.demo.service;

import customer.demo.demo.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer createCustomer(Customer customer);
    List<Customer> getCustomers(String firstName, String city, String state);
}
