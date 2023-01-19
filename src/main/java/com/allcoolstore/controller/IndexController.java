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

    @GetMapping(value = "/users/admin")
    public String getAdminPage() {
        return "admin";
    }

    @GetMapping(value = "/login")
    public String getLoginUserPage(ModelMap modelMap){
        modelMap.addAttribute("loginForm", new User());
        return "login";
    }

    @GetMapping(value = "/users/user")
    public String getUserPage() {
        return "user";
    }

//    @GetMapping(value = "/users/settingAdmin")
//    public String getSettingAdminPage() {
//        return "settingAdmin";
//    }

    @GetMapping(value = "/users/settingUser")
    public String getSettingUserPage() {
        return "settingUser";
    }

    @GetMapping(value = "/users/tkyou")
    public String getThankYouPage() {
        return "tkyou";
    }

    @GetMapping(value = "/users/register")
    public String getRegisterPage() {
        return "register";
    }

    @GetMapping(value = "/users/update-admin")
    public String getUpdateAdmin() {
        return "updateAdmin";
    }

    @GetMapping(value = "/users/update-customer")
    public String getUpdateCustomer() {
        return "updateCustomer";
    }

    @GetMapping(value = "/users/not-register")
    public String getNotRegisterPage() { return "notRegister"; }

    @GetMapping(value = "/orders/my-orders")
    public String getMyOrders() { return "myOrders"; }

//    @GetMapping(value = "/orders/orders-admin")
//    public String getAdminOrders() { return "ordersAdmin"; }

    @GetMapping(value = "/orders/orders-user")
    public String getUserOrders() { return "ordersUser"; }

//    @GetMapping(value = "/orders/create-order")
//    public String createOrder() { return "createOrder"; }

    @GetMapping(value = "/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login";
    }

}
