package com.allcoolstore.htmlcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GdprController {

    @GetMapping(value = "/global/gdpr")
    public String index() {
        return "gdpr";
    }

}
