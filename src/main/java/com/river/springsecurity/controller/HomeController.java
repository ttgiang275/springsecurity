package com.river.springsecurity.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @GetMapping(path = "/")
    public String home(HttpServletRequest request) {
        return "home";
    }

    @GetMapping(path = "hello")
    public String hello() {
        return "hello";
    }

    @GetMapping(path = "about")
    public String about() {
        return "about";
    }
}
