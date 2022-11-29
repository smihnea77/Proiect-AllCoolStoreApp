package com.allcoolstore.controller;

import com.allcoolstore.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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

    @GetMapping(value = "/users/login-admin")
    public String getLoginAdminPage() {
        return "loginAdmin";
    }

    @GetMapping(value = "/users/admin")
    public String getAdminPage() {
        return "admin";
    }

    @GetMapping(value = "/users/login-user")
    public String getLoginUserPage(ModelMap modelMap) {
        modelMap.addAttribute("user", new User());
        return "loginUser";
    }
    @PostMapping("/users/login-user")
    public ModelAndView loginUser(@ModelAttribute User user, Model model) {
        return new ModelAndView("redirect:/");
    }

    @GetMapping(value = "/users/user")
    @PostMapping
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

}
