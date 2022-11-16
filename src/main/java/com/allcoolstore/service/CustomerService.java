package com.allcoolstore.service;

import com.allcoolstore.model.Customer;
import com.allcoolstore.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }


    public void createCustomer(Customer customer) {
        validateEmail(customer.getEmail());
        customerRepository.save(customer);
    }

    public void deleteCustomer(Long id) {
        boolean customerExists = customerRepository.existsById(id);
        if (!customerExists) {
            throw new IllegalStateException(String.format("Customer with id %s does not exist.", id));
        }
        customerRepository.deleteById(id);
    }

    public void updateCustomer(Long id, Customer customer) {
        Customer customerToUpdate = customerRepository.findById(id).orElseThrow(() ->
                new IllegalStateException(String.format("Customer with id %s does not exist.", id)));
        validateEmail(customer.getEmail());
        customerToUpdate.setFirstName(customer.getFirstName());
        customerToUpdate.setLastName(customer.getLastName());
        customerToUpdate.setEmail(customer.getEmail());
        customerToUpdate.setPassword(customer.getPassword());
        customerToUpdate.setAge(customer.getAge());
        customerToUpdate.setPhone(customer.getPhone());
        customerToUpdate.setAddress(customer.getAddress());
        customerRepository.save(customerToUpdate);
    }


    public Optional<Customer> getCustomerByEmailAddress(String emailAddress) {
        return customerRepository.findByEmail(emailAddress);
    }

        private void validateEmail (String email){
            Optional<Customer> customerOptional = getCustomerByEmailAddress(email);
            if (customerOptional.isPresent()) {
                throw new IllegalStateException(String.format("Email address %s already exists.", email));
            }
        }


    }
