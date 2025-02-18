package com.river.springsecurity.service;

import com.river.springsecurity.exception.CustomValidationException;
import com.river.springsecurity.model.User;
import com.river.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public User saveUser(User user) {
        if (repository.findByUsername(user.getUsername()) != null) {
            throw new CustomValidationException("User already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        return repository.save(user);
    }

}
