package com.allcoolstore.service;

import com.allcoolstore.model.Cart;
import com.allcoolstore.model.Product;
import com.allcoolstore.model.User;
import com.allcoolstore.repository.CartRepository;
import com.allcoolstore.repository.ProductRepository;
import com.allcoolstore.repository.UserRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, UserRepository userRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }


    public Cart addToCart(Long id) {
        String username = null;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            username = authentication.getName();
        }
        Product product = productRepository.findById(id).get();
        User user = userRepository.findByUsername(username);
        Cart cart = new Cart(product, user);
        return cartRepository.save(cart);
    }

    public List<Cart> getAllProductsAddedToCart() {
        return cartRepository.findAll();
    }

//    public List<Cart> getAllProductsAddedToCartCurrentUser() {
//        String username = null;
//        List<Cart> currentUserCart = new ArrayList<>();
//        List<Cart> allProducts = cartRepository.findAll();
//
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (!(authentication instanceof AnonymousAuthenticationToken)) {
//            username = authentication.getName();
//        }
//        User user = userRepository.findByUsername(username);
//        for (Cart c : allProducts) {
//            if (c.getUser().equals(user.getId())) {
//                currentUserCart.add(c);
//            }
//        }
//        return currentUserCart;
//    }


    public int getProductCartSize(List<Cart> cartList) {
        int cartSize = 0;
        for (Cart c : cartList) {
            cartSize++;
        }
        return cartSize;
    }

    public int getTotalPrice(List<Cart> cartList) {
        int totalPrice = 0;
        for (Cart c : cartList) {
            c.getTotalPrice();
            totalPrice += c.getTotalPrice();
        }
        return totalPrice;
    }
}

