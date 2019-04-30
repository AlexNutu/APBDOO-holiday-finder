package com.holiday.finder.repository;

import com.holiday.finder.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User getById(long id);

    User getByUsername(String username);

    User getByEmail(String email);
}
