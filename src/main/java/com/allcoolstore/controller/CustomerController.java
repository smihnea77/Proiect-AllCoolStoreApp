package com.allcoolstore.controller;

import com.allcoolstore.model.Customer;
import com.allcoolstore.service.CustomerService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping(path = "/create-customer")
    public void createCustomer(@RequestBody Customer customer) {
        customerService.createCustomer(customer);
    }

    @PutMapping(path = "/update-customer/{id}")
    public void updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
        customerService.updateCustomer(id, customer);
    }

    @DeleteMapping(path = "{id}")
    public void deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
    }
}
