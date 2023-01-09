package com.allcoolstore.controller;

import com.allcoolstore.model.Order;
import com.allcoolstore.service.OrderService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public ModelAndView getAllOrders() {
        ModelAndView modelAndView = new ModelAndView("orders");
        List<Order> orderList = orderService.getAllOrders();
        modelAndView.addObject("orderList", orderList);
        return modelAndView;
    }

    @GetMapping("/create-order")
    public ModelAndView createOrderPage() {
        ModelAndView modelAndView = new ModelAndView("createOrder");
        modelAndView.addObject(new Order());
        return modelAndView;
    }

    @PostMapping("/create-order")
    public ModelAndView createOrder(@ModelAttribute Order order) {
        orderService.createOrder(order);
        return new ModelAndView("redirect:/createOrder");
    }

    @PostMapping(path = "/update-order/{id}")
    public ModelAndView updateOrder(@PathVariable Long id, @ModelAttribute("orderUpdateForm") Order order) {
        orderService.updateOrder(id, order);
        return new ModelAndView("redirect:/orders/orders");
    }

    @GetMapping("/update-order/{id}")
    public ModelAndView updateOrderPage(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("updateOrder");
        modelAndView.addObject(orderService.getOrderById(id));
        return modelAndView;
    }
}
