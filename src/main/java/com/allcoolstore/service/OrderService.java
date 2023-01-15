package com.allcoolstore.service;

import com.allcoolstore.model.Order;
import com.allcoolstore.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService  {

    private final OrderRepository orderRepository;
    private final CartService cartService;

    public OrderService(OrderRepository orderRepository, CartService cartService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
    }


    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public void createOrder(Order order) {
       // order.setProducts(cartService.getAllProductsAddedToCart());
      //  order.setTotal(cartService.getTotalPrice());
        orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        boolean orderExists = orderRepository.existsById(id);
        if (!orderExists) {
            throw new IllegalStateException(String.format("Order with id %s does not exist.", id));
        }
        orderRepository.deleteById(id);
    }

    public void updateOrder(Long id, Order order) {
        Order orderToUpdate = orderRepository.findById(id).orElseThrow(() ->
                new IllegalStateException(String.format("Order with id %s does not exist.", id)));
        // orderToUpdate.setStatus(order.getStatus());
        orderToUpdate.setTotal(order.getTotal());
        orderToUpdate.setPaymentMethod(order.getPaymentMethod());
        orderToUpdate.setLocalDate(orderToUpdate.getLocalDate());
        orderToUpdate.setProducts(order.getProducts());
        orderRepository.save(orderToUpdate);
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(new Order());
    }
}
