package com.allcoolstore.controller;

import com.allcoolstore.model.Cart;
import com.allcoolstore.model.Order;
import com.allcoolstore.model.User;
import com.allcoolstore.service.CartService;
import com.allcoolstore.service.OrderService;
import com.allcoolstore.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {
    private final OrderService orderService;
    private final CartService cartService;
    private final UserService userService;

    public OrderController(OrderService orderService, CartService cartService, UserService userService) {
        this.orderService = orderService;
        this.cartService = cartService;
        this.userService = userService;
    }

    @GetMapping("/orders")
    public ModelAndView getAllOrders() {
        ModelAndView modelAndView = new ModelAndView("orders");
        List<Order> orderList = orderService.getAllOrders();
        modelAndView.addObject("orderList", orderList);
        return modelAndView;
    }

    @GetMapping("/orders-user")
    public ModelAndView getAllOrdersCurrentUser() {
        ModelAndView modelAndView = new ModelAndView("ordersUser");
        List<Order> orderList = orderService.getAllOrdersCurrentUser();
        modelAndView.addObject("orderList", orderList);
        return modelAndView;
    }

    @GetMapping("/create-order")
    public ModelAndView createOrderPage() {
        ModelAndView modelAndView = new ModelAndView("createOrder");
        User user = userService.getLoggedUser();
        List<Cart> productList = cartService.getAllProductsCurrentUser(user.getId());
        modelAndView.addObject("productListAddedToCart", productList);
        modelAndView.addObject("user", user);
        modelAndView.addObject("totalPrice", cartService.getTotalPrice(productList));
        modelAndView.addObject(new Order());
        return modelAndView;
    }

    @PostMapping("/create-order")
    public ModelAndView createOrder(@ModelAttribute Order order) {
        orderService.createOrder(order);
        return new ModelAndView("redirect:/users/tkyou");
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

    @DeleteMapping(path = "{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }

    @GetMapping(path = "/delete-order/{id}")
    public ModelAndView deleteOrder(@PathVariable("id") Long id, Model model) {
        orderService.deleteOrder(id);
        return new ModelAndView("redirect:/orders/orders");
    }
}
