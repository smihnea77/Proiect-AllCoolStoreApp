package com.allcoolstore.service;

import com.allcoolstore.model.Cart;
import com.allcoolstore.model.Product;
import com.allcoolstore.model.User;
import com.allcoolstore.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final UserService userService;
    private final ProductService productService;

    public CartService(CartRepository cartRepository, UserService userService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.productService = productService;
    }


    public Cart addToCart(Long id) {
        User user = userService.getLoggedUser();
        Product product = productService.getByProductId(id);
        Cart cart = new Cart(product, user);
        return cartRepository.save(cart);
    }

    public void deleteFromCart(Long id) {
        cartRepository.deleteByProductId(id);
    }

    public List<Cart> getAllProductsAddedToCart() {
        return cartRepository.findAll();
    }

    public List<Cart> getAllProductsCurrentUser(Long id) {
        return cartRepository.findAllCurrentUser(id);
    }

    public int getProductCartSize(List<Cart> cartList) {
        return cartList.size();
    }

    public double getTotalPrice(List<Cart> cartList) {
        productService.priceWithTva();
        double totalPrice = 25;
        for (Cart c : cartList) {
            c.getTotalPrice();
            totalPrice += c.getProduct().getPrice();
        }
        return totalPrice;
    }

    public void clearShoppingCart(Long id){
        cartRepository.deleteByUsertId(id);
    }
}

