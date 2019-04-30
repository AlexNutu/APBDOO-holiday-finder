package com.holiday.finder.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;

public class RememberMeService implements UserDetailsService {

    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return new User(username, "", Collections.emptyList());
    }
}