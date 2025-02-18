package com.river.springsecurity.controller;

import com.river.springsecurity.model.User;
import com.river.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping(path = "/register")
    public User register(@RequestBody User user) {
        return service.saveUser(user);
    }

}
