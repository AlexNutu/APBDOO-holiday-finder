package com.holiday.finder.service.impl;

import com.holiday.finder.model.User;
import com.holiday.finder.repository.UserRepository;
import com.holiday.finder.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    @Override
    public User getByUsername(String name) {
        return userRepository.getByUsername(name);
    }
}
