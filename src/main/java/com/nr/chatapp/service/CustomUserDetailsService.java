package com.nr.chatapp.service;

import com.nr.chatapp.model.User;
import com.nr.chatapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User byUserName = userRepository.findByUserName(username);
       return new org.springframework.security.core.userdetails.User(byUserName.getUserName(),byUserName.getPassword(), Collections.emptyList());
    }

}
