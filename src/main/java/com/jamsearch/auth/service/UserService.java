package com.jamsearch.auth.service;

import com.jamsearch.auth.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
