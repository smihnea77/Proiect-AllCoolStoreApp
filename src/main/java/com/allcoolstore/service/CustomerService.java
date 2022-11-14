package com.allcoolstore.service;

import com.allcoolstore.repository.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final OrderRepository orderRepository;

    public CustomerService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
}
