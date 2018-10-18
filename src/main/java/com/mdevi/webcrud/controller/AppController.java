package com.mdevi.webcrud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppController {

    @GetMapping(value = {"/login", "/logout"})
    public String processTheLogin() {
        return "login";
    }

    @GetMapping("/accessDenied")
    public String processAccessDenied() {
        return "403";
    }

}
