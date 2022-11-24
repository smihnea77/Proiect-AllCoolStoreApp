package com.allcoolstore.htmlcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContactController {

    @GetMapping(value = "/global/contact")
    public String index() {
        return "contact";
    }

}

