package com.workbeattalent.sport_club.user;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.Collection;

@Component
public class UserRestClient {

    private final RestClient restClient;

    public UserRestClient(RestClient.Builder builder) {
        this.restClient = builder
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();
    }

    public Collection<User> getAllUsers() {
        return this.restClient.get()
                .uri("/users")
                .retrieve()
                .body(new ParameterizedTypeReference<>() {
                });
    }

    public User getById(final Integer id) {
        return this.restClient.get()
                .uri("/users/{id}", id)
                .retrieve()
                .body(User.class);
    }
}