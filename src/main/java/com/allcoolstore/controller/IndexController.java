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

}
