package com.allcoolstore.controller;

import com.allcoolstore.service.CartService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/cart")
public class CartController {
    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping()
    public ModelAndView getAllProductsAddedToCart() {
        ModelAndView modelAndView = new ModelAndView("cart");
        modelAndView.addObject("productListAddedToCart", cartService.getAllProductsAddedToCart());
        modelAndView.addObject("totalItems", cartService.getProductCartSize());
        modelAndView.addObject("totalPrice", cartService.getTotalPrice());
        return modelAndView;
    }

    @GetMapping("/add-product-to-cart/{name}")
    public ModelAndView addProductToCart(@PathVariable String name){
        cartService.addProductToCart(name);
        return new ModelAndView("/redirect:/");
    }

}
