package com.allcoolstore.controller;

import com.allcoolstore.model.Cart;
import com.allcoolstore.model.Product;
import com.allcoolstore.service.CartService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @GetMapping("/add-to-cart/{id}")
    public Cart addToCart(@PathVariable(name = "id")Long id){
      return  cartService.addToCart(id);
    }

    @GetMapping()
    public ModelAndView getAllProductsAddedToCart() {
        ModelAndView modelAndView = new ModelAndView("cart");
        List<Cart> productList = cartService.getAllProductsAddedToCart();
        modelAndView.addObject("productListAddedToCart", productList);
        modelAndView.addObject("totalItems", cartService.getProductCartSize(productList));
        modelAndView.addObject("totalPrice", cartService.getTotalPrice(productList));
        return modelAndView;
    }






}
