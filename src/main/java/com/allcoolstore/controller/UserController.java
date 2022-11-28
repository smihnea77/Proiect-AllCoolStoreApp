package com.allcoolstore.controller;

import com.allcoolstore.model.User;
import com.allcoolstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView getAllUsers() {
        ModelAndView modelAndView = new ModelAndView("users");
        List<User> userList = userService.getAllUsers();
        modelAndView.addObject("userList", userList);
        return modelAndView;
    }

//    @GetMapping("/login-user")
//    public ModelAndView getLoginPage(){
//        ModelAndView modelAndView = new ModelAndView("loginUser");
//        modelAndView.addObject(new User());
//        return modelAndView;
//    }
//
//    @PostMapping("/login-user")
//    public ModelAndView loginUser(@ModelAttribute User user, Model model) {
//        if (userService
//                .verifyEmailAndPassword(user.getEmail(), user.getPassword())
//                .isPresent()) {
//            return new ModelAndView("redirect:/products");
//        }
//        return new ModelAndView("redirect:/login");
//    }



        @GetMapping("/register-user")
    public ModelAndView registerUserPage() {
        ModelAndView modelAndView = new ModelAndView("registerUser");
        modelAndView.addObject(new User());
        return modelAndView;
    }

    @PostMapping("/register-user")
    public void  registerUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.registerUser(user);
/*        return new ModelAndView("redirect:/");*/
    }

    @PostMapping(path = "/update-user/{id}")
    public ModelAndView updateUser(@PathVariable Long id, @ModelAttribute("userUpdateForm") User user) {
        userService.updateUser(id, user);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("update-user/{id}")
    public ModelAndView updateUserPage(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("updateUser");
        modelAndView.addObject(userService.getByUserId(id));
        return modelAndView;
    }

    @DeleteMapping(path = "{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping(path = "/delete-user/{id}")
    public ModelAndView deleteUser(@PathVariable ("id") Long id, Model model) {
        userService.deleteUser(id);
        return new ModelAndView("redirect:/users");
    }
}
