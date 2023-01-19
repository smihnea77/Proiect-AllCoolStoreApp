package com.allcoolstore.service;

import com.allcoolstore.model.Cart;
import com.allcoolstore.model.Product;
import com.allcoolstore.model.User;
import com.allcoolstore.repository.CartRepository;
import com.allcoolstore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserService userService;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, UserService userService) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.userService = userService;
    }


    public Cart addToCart(Long id) {
        String username = userService.getLoggedUser();
        Product product = productRepository.findById(id).get();
        User user = userService.findByUsername(username);
        Cart cart = new Cart(product,user);
        return cartRepository.save(cart);
    }
    public void deleteFromCart(Long id){
        cartRepository.deleteByProductId(id);
    }

    public List<Cart> getAllProductsAddedToCart() {
        return cartRepository.findAll();
    }
    public List <Cart> getAllProductsCurrentUser(Long id){
       return cartRepository.findAllCurrentUser(id);
    }

    public int getProductCartSize(List<Cart> cartList) {
        return cartList.size();
    }

    public int getTotalPrice(List<Cart> cartList) {
        int totalPrice = 25;
        for (Cart c : cartList) {
            c.getTotalPrice();
            totalPrice += c.getProduct().getPrice();
        }
        return totalPrice;
    }
}

