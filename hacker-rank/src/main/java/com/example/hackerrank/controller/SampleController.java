package com.example.hackerrank.controller;

import com.example.hackerrank.entities.Customer;
import com.example.hackerrank.repository.CustomerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/endpoint")
public class SampleController {

    @Autowired
    private CustomerRepository customerRepository;

    @PostMapping("endpoint/insert")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer customerData = customerRepository.save(customer);
        return new ResponseEntity<>(customerData, HttpStatus.CREATED);
    }

    @GetMapping("endpoint/select")
    public ResponseEntity<List<Customer>> getCustomers() {
        return new ResponseEntity<>(customerRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("endpoint/select/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Integer id) {
        return new ResponseEntity<>(customerRepository.findById(id).get(), HttpStatus.OK);
    }

    @DeleteMapping("endpoint/delete/{id}")
    public ResponseEntity<Void> deleteCustomerById(@PathVariable Integer id) {
        Optional<Customer> customerOptional = customerRepository.findById(id);
        if (!customerOptional.isPresent())
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        customerRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
