package com.allcoolstore.service;

import com.allcoolstore.model.Cart;
import com.allcoolstore.model.Order;
import com.allcoolstore.model.Product;
import com.allcoolstore.model.User;
import com.allcoolstore.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService  {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final UserService userService;
    private final ProductService productService;

    public OrderService(OrderRepository orderRepository, CartService cartService, UserService userService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.cartService = cartService;
        this.userService = userService;
        this.productService = productService;
    }


    public List<Order> getAllOrdersCurrentUser() {
        User user = userService.getLoggedUser();
        return orderRepository.findAllOrdersCurrentUser(user.getId());
    }
    public List <Order> getAllOrders(){
        return orderRepository.findAll();
    }

    public void createOrder(Order order) {
        User user = userService.getLoggedUser();
        order.setProducts(getAllProductsFromCart());
        order.setTotal(getTotalPriceFromCart());
        order.setLocalDate(LocalDate.now());
        order.setUser(user);
        //  order.setTotal(cartService.getTotalPrice());
        orderRepository.save(order);
        cartService.clearShoppingCart(user.getId());
    }
    private double getTotalPriceFromCart(){
        List <Product> productList= getAllProductsFromCart();
        double totalPrice = 25;
        for(Product p : productList){
            totalPrice+=p.getPrice();
        }return totalPrice;
    }


    public List<Product> getAllProductsFromCart(){
        User user = userService.getLoggedUser();
        List <Cart> cartList = cartService.getAllProductsCurrentUser(user.getId());
        List <Product> productList = new ArrayList<>();
        for (Cart c: cartList){
            productList.add(c.getProduct());
        }return productList;
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
