package com.workbeattalent.sport_club.user;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import java.util.Collection;

public interface UserHTTPClient {
    @GetExchange(url = "/users")
    Collection<User> findAll();

    @GetExchange(url = "/users/{id}")
    User findById(final @PathVariable Integer id);
}
