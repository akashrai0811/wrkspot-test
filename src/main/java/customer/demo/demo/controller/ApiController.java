package customer.demo.demo.controller;
import customer.demo.demo.entity.Address;
import customer.demo.demo.entity.Customer;
import customer.demo.demo.service.AddressService;
import customer.demo.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApiController {
    @Autowired
    private CustomerService customerService;

    @Autowired
    private AddressService addressService;

    @PostMapping("/customers")
    public ResponseEntity<String> createCustomerAndAddress(@RequestBody Map<String, Object> requestBody) {
        ObjectMapper objectMapper = new ObjectMapper();
        Customer customer = objectMapper.convertValue(requestBody.get("customer"), Customer.class);

        // Create the customer and addresses
        Customer createdCustomer = customerService.createCustomer(customer);
        List<Address> addresses = createdCustomer.getAddresses();

        for (Address address : addresses) {
            address.setCustomer(createdCustomer);
            addressService.createAddress(address);
        }
        return new ResponseEntity<>("Customer created successfully", HttpStatus.CREATED);
    }
        @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getCustomers(@RequestParam(required = false) String firstName,
                                                       @RequestParam(required = false) String city,
                                                       @RequestParam(required = false) String state) {
        List<Customer> customers = customerService.getCustomers(firstName, city, state);
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

}

