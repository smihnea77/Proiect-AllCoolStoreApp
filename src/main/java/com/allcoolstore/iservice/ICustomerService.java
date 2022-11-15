package com.allcoolstore.iservice;
import com.allcoolstore.model.Customer;
import java.util.List;


public interface ICustomerService {
    List<Customer> getAllCustomers();
    void createCustomer(Customer customer);
    void deleteCustomer(Long id);
    void updateCustomer(Long id, Customer customer);

}
