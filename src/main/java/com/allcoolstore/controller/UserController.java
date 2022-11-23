package com.allcoolstore.controller;

import com.allcoolstore.model.Product;
import com.allcoolstore.model.User;
import com.allcoolstore.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping(path = "/register-user")
    public void registerUser(@RequestBody User user) {
        userService.registerUser(user);
    }

    @PostMapping (path = "/update-user/{id}")
    public ModelAndView ModelAndView(@PathVariable Long id, @ModelAttribute("userUpdateForm") User user) {
        userService.updateUser(id, user);
        return new ModelAndView("redirect:/");
    }
    @GetMapping("update-user/{id}")
    public ModelAndView updateUserPage(@PathVariable Long id){
        ModelAndView modelAndView = new ModelAndView("updateUser");
        modelAndView.addObject(userService.getByUserId(id));
        return modelAndView;
   }

//    @PostMapping(path = "update-product/{id}")
//    public ModelAndView updateProduct(@PathVariable Long id, @ModelAttribute("productUpdateForm") Product product) {
//        productService.updateProduct(id, product);
//        return new ModelAndView("redirect:/");
//    }
//
//
//    @GetMapping("update-product/{id}")
//    public ModelAndView updateProductPage(@PathVariable Long id) {
//        ModelAndView modelAndView = new ModelAndView("updateProduct");
//        modelAndView.addObject(productService.getByProductId(id));
//        return modelAndView;
//    }

    @DeleteMapping(path = "{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
