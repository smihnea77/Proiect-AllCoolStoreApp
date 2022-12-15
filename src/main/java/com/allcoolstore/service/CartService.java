package com.allcoolstore.service;

import com.allcoolstore.model.Product;
import com.allcoolstore.repository.ProductRepository;
import com.allcoolstore.repository.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final List<Product> productListAddedToCart;

    public CartService(CartRepository cartRepository, ProductRepository productRepository, List<Product> productListAddedToCart) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.productListAddedToCart = productListAddedToCart;
    }

    public void addProductToCart(String name) {
        Optional<Product> productToFind = productRepository.findByName(name);
        productToFind.ifPresent(product -> productListAddedToCart.add(product));
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (Product p: productListAddedToCart){
            totalPrice+= p.getPrice();
        }return totalPrice;
    }

    public int getProductCartSize(){
        return productListAddedToCart.size();
    }
    public List <Product> getAllProductsAddedToCart(){
        return productListAddedToCart;
    }

    public void removeProductFromCart(String name){
         productListAddedToCart.remove(name);
    }


}
