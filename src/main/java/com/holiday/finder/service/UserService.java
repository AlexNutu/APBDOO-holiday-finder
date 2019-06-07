package com.holiday.finder.service;

import com.holiday.finder.model.User;

public interface UserService {

    void saveUser(User user);

    User getByUsername(String name);
}
