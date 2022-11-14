package com.allcoolstore.service;

import com.allcoolstore.model.Order;
import com.allcoolstore.repository.OrderRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class OrderService implements IOrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders(){
       return orderRepository.findAll();
    }

    public void createOrder(Order order){
        orderRepository.save(order);
    }
    public void deleteOrder(Order order){
        orderRepository.delete(order);
    }

    @Override
    public void updateOrder(Long id, Order order){
        Order orderToUpdate = orderRepository.findById(id).orElseThrow(()->
        new IllegalStateException(String.format("Order with id%s does not exist.",id)));
        orderToUpdate.setStatus(order.getStatus());
        orderToUpdate.setTotal(order.getTotal());
        orderToUpdate.setPaymentMethod(order.getPaymentMethod());
        orderToUpdate.setLocalDate(orderToUpdate.getLocalDate());
       orderToUpdate.setProducts(order.getProducts());
       orderRepository.save(orderToUpdate);
    }
}
