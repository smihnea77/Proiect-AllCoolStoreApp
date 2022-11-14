package com.allcoolstore.controller;

import com.allcoolstore.model.Order;
import com.allcoolstore.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping
    public List<Order> getAllOrders(){
        return orderService.getAllOrders();
    }

    @PostMapping(path = "/create-order")
    public void createOrder(@RequestBody Order order){
        orderService.createOrder(order);
    }

    @PutMapping(path = "/update-order/{id}")
    public void updateOrder(@PathVariable Long id, @RequestBody Order order){
        orderService.updateOrder(id, order);
    }

    @DeleteMapping(path = "{id}")
    public void deleteOrder(@PathVariable Long id, @RequestBody Order order){
        orderService.deleteOrder(id, order);
    }



}
