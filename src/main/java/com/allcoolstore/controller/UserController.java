package com.allcoolstore.controller;

import com.allcoolstore.model.User;
import com.allcoolstore.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


import java.sql.Date;
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

    @GetMapping("/users")
    public ModelAndView getAllUsers() {
        ModelAndView modelAndView = new ModelAndView("users");
        List<User> userList = userService.getAllUsers();
        modelAndView.addObject("userList", userList);
        return modelAndView;
    }

    @GetMapping("/settingAdmin")
    public ModelAndView getCurrentAdminForSettings() {
        ModelAndView modelAndView = new ModelAndView("settingAdmin");
        User user = userService.getLoggedUser();
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @GetMapping("/settingUser")
    public ModelAndView getCurrentUserForSettings() {
        ModelAndView modelAndView = new ModelAndView("settingUser");
        User user = userService.getLoggedUser();
        modelAndView.addObject("user", user);
        return modelAndView;
    }

//    @GetMapping("/user/{id}")
//    public ModelAndView getAdminById(@PathVariable Long id) {
//        ModelAndView modelAndView = new ModelAndView("settingAdmin");
//        modelAndView.addObject(userService.getByUserId(id));
//        return modelAndView;
//    }
//
//    @GetMapping("/user/{id}")
//    public ModelAndView getUserById(@PathVariable Long id) {
//        ModelAndView modelAndView = new ModelAndView("settingUser");
//        modelAndView.addObject(userService.getByUserId(id));
//        return modelAndView;
//    }

//    @GetMapping("/{id}")
//    public ModelAndView getUserById(@PathVariable Long id) {
//        ModelAndView modelAndView = new ModelAndView("setting");
//        modelAndView.addObject(userService.getByUserId(id));
//        return modelAndView;
//    }


    @GetMapping("/register-user")
    public ModelAndView registerUserPage() {
        ModelAndView modelAndView = new ModelAndView("registerUser");
        modelAndView.addObject(new User());
        return modelAndView;
    }


    @PostMapping("/register-user")
    public ModelAndView registerUser(@RequestParam("firstName") String firstName,
                                     @RequestParam("lastName") String lastName,
                                     @RequestParam("dateOfBirth") Date dateOfBirth,
                                     @RequestParam("email") String email,
                                     @RequestParam("username") String username,
                                     @RequestParam("password") String password,
                                     @RequestParam("role") String role,
                                     @RequestParam("phone") String phone,
                                     @RequestParam("city") String city,
                                     @RequestParam("county") String county,
                                     @RequestParam("postalCode") String postalCode,
                                     @RequestParam("address1") String address1,
                                     @RequestParam("address2") String address2) {
        User user = new User(firstName, lastName, dateOfBirth, email, username, password, role, phone, city, county, postalCode, address1, address2);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.registerUser(user);
        return new ModelAndView("redirect:/login");
    }

    @GetMapping("/create-user")
    public ModelAndView createUserPage() {
        ModelAndView modelAndView = new ModelAndView("createUser");
        modelAndView.addObject(new User());
        return modelAndView;
    }

    @PostMapping("/create-user")
    public ModelAndView createUser(@RequestParam("firstName") String firstName,
                                   @RequestParam("lastName") String lastName,
                                   @RequestParam("dateOfBirth") Date dateOfBirth,
                                   @RequestParam("email") String email,
                                   @RequestParam("username") String username,
                                   @RequestParam("password") String password,
                                   @RequestParam("role") String role,
                                   @RequestParam("phone") String phone,
                                   @RequestParam("city") String city,
                                   @RequestParam("county") String county,
                                   @RequestParam("postalCode") String postalCode,
                                   @RequestParam("address1") String address1,
                                   @RequestParam("address2") String address2) {
        User user = new User(firstName, lastName, dateOfBirth, email, username, password, role, phone, city, county, postalCode, address1, address2);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.createUser(user);
        return new ModelAndView("redirect:/users/users");
    }

    @PostMapping(path = "/update-user/{id}")
    public ModelAndView updateUser(@PathVariable Long id, @ModelAttribute("userUpdateForm") User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.updateUser(id, user);
        return new ModelAndView("redirect:/users/users");
    }

    @GetMapping("update-user/{id}")
    public ModelAndView updateUserPage(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("updateUser");
        modelAndView.addObject(userService.getByUserId(id));
        return modelAndView;
    }

    @GetMapping("update-admin")
    public ModelAndView updateAdminPage() {
        ModelAndView modelAndView = new ModelAndView("updateAdmin");
        User user = userService.getLoggedUser();
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping(path = "update-admin/{id}")
    public ModelAndView updateAdmin(@PathVariable Long id, @ModelAttribute("adminUpdateForm") User user) {
        userService.updateUser(id, user);
        return new ModelAndView("redirect:/users/settingAdmin");
    }

    @GetMapping("update-customer")
    public ModelAndView updateCustomerPage() {
        ModelAndView modelAndView = new ModelAndView("updateCustomer");
        User user = userService.getLoggedUser();
        modelAndView.addObject("user", user);
        return modelAndView;
    }

    @PostMapping(path = "update-customer/{id}")
    public ModelAndView updateCustomer(@PathVariable Long id, @ModelAttribute("customerUpdateForm") User user) {
        userService.updateUser(id, user);
        return new ModelAndView("redirect:/users/settingUser");
    }

    @DeleteMapping(path = "{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping(path = "/delete-user/{id}")
    public ModelAndView deleteUser(@PathVariable("id") Long id, Model model) {
        userService.deleteUser(id);
        return new ModelAndView("redirect:/users/users");
    }
}