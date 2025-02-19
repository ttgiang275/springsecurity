package com.river.springsecurity.service;

import com.river.springsecurity.exception.UserNotFoundException;
import com.river.springsecurity.model.User;
import com.river.springsecurity.model.UserDetailsImpl;
import com.river.springsecurity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = repository.findByUsername(username);
        if (user == null) {
            System.out.println("User not found");
            throw new UserNotFoundException("User not found");
        }   else {
            return new UserDetailsImpl(user);
        }
    }

}
