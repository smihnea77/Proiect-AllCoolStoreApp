package com.allcoolstore.controller;

import com.allcoolstore.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class IndexController {

    @GetMapping(value = "/")
    public String index() {
        return "index";
    }

    @GetMapping(value = "/global/contact")
    public String getContactPage() {
        return "contact";
    }

    @GetMapping(value = "/global/gdpr")
    public String getGdprPage() {
        return "gdpr";
    }

    @GetMapping(value = "/global/terms")
    public String getTermsPage() {
        return "terms";
    }

//    @GetMapping(value = "/users/login-admin")
//    public String getLoginAdminPage() {
//        return "loginAdmin";
//    }

    @GetMapping(value = "/users/create-user")
    public String createUser() {
        return "createUser";
    }

    @GetMapping(value = "/users/admin")
    public String getAdminPage() {
        return "admin";
    }

//        @GetMapping(value = "/users/login-user")
//    public String getLoginUserPage(ModelMap modelMap) {
//        modelMap.addAttribute("user", new User());
//        return "loginUser";
//    }

    @GetMapping(value = "/login")
    public String getLoginUserPage(ModelMap modelMap){
        modelMap.addAttribute("loginForm", new User());
        return "login";
    }

//    @PostMapping("/users/login-user")
//    public ModelAndView loginUser(@ModelAttribute User user, Model model) {
//        return new ModelAndView("redirect:/");
//    }

    @GetMapping(value = "/users/user")
    public String getUserPage() {
        return "user";
    }

    @GetMapping(value = "/users/register")
    public String getRegisterPage() {
        return "register";
    }

    @GetMapping(value = "/users/not-register")
    public String getNotRegisterPage() {
        return "notRegister";
    }

    @GetMapping(value = "/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

}
