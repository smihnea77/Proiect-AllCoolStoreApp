package com.allcoolstore.htmlcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TermsController {

    @GetMapping(value = "/global/terms")
    public String index() {
        return "terms";
    }

}
