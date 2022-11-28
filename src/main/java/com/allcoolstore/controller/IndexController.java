package com.allcoolstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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
    public String getLoginUserPage() {
        return "loginUser";
    }

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

}
