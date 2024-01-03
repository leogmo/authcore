package com.cjl.auth.application.usecase;

import java.util.Optional;

import com.cjl.auth.domain.User;

public interface AccountRepository {
    Optional<User> findByEmail(String username);
    User signup(User user) throws Exception;

    void save(User user) throws Exception;
}
