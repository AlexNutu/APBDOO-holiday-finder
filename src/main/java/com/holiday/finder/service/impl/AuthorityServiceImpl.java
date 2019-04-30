package com.holiday.finder.service.impl;

import com.holiday.finder.model.Authority;
import com.holiday.finder.repository.AuthorityRepository;
import com.holiday.finder.service.AuthorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityServiceImpl implements AuthorityService {

    @Autowired
    AuthorityRepository authorityRepository;

    @Override
    public void saveAuthority(Authority authority) {
        authorityRepository.save(authority);
    }
}
