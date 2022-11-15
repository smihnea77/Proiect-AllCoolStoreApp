package com.allcoolstore.service;

import com.allcoolstore.iservice.ICustomerService;
import com.allcoolstore.model.Customer;
import com.allcoolstore.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
@Service
public class CustomerService implements ICustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public void createCustomer(Customer customer) {
        validateEmail(customer.getEmail());
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        boolean customerExists = customerRepository.existsById(id);
        if (!customerExists) {
            throw new IllegalStateException(String.format("Customer with id %s does not exist.", id));
        }
        customerRepository.deleteById(id);
    }

    @Override
    public void updateCustomer(Long id, Customer customer) {
        Customer customerToUpdate = customerRepository.findById(id).orElseThrow(() ->
                new IllegalStateException(String.format("Customer with id %s does not exist.", id)));
        validateEmail(customer.getEmail());
        customerToUpdate.setFirstName(customer.getFirstName());
        customerToUpdate.setLastName(customer.getLastName());
        customerToUpdate.setEmail(customer.getEmail());
        customerToUpdate.setPass(customer.getPass());
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
