package com.jamsearch.auth.service;

import com.jamsearch.auth.model.User;

import java.util.List;

public interface UserService {
    void save(User user);

    User findByUsername(String username);

    List<User> findAll();
}
