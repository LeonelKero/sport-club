package com.workbeattalent.sport_club.user;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RestClientTest(UserRestClient.class)
class UserRestClientTest {

    @Autowired
    private MockRestServiceServer restServiceServer;

    @Autowired
    private UserRestClient userRestClient;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldGetAllUsers() throws JsonProcessingException {
        // GIVEN
        final var user = new User(
                1,
                "leonel ka",
                "black",
                "black.mail@mail.com",
                new Address("453 Toy Bvd", "42", "yaounde", "453",
                        new Geo("98746", "-497300")),
                "697445469",
                "workbeattalent.com",
                new Company("wbt", "work beats talent", "wbt-42"));
        final var users = List.of(user);
        // WHEN
        this.restServiceServer
                .expect(MockRestRequestMatchers.requestTo("https://jsonplaceholder.typicode.com/users"))
                .andRespond(MockRestResponseCreators.withSuccess(
                        objectMapper.writeValueAsString(users),
                        MediaType.APPLICATION_JSON)
                );
        // THEN
        final var allUsers = this.userRestClient.getAllUsers();
        assertThat(allUsers).isEqualTo(users);
    }

    @Test
    void shouldGetSingleUserById() throws JsonProcessingException {
        // GIVEN
        final var user = new User(
                1,
                "leonel ka",
                "black",
                "black.mail@mail.com",
                new Address("453 Toy Bvd", "42", "yaounde", "453",
                        new Geo("98746", "-497300")),
                "697445469",
                "workbeattalent.com",
                new Company("wbt", "work beats talent", "wbt-42"));
        // WHEN
        this.restServiceServer.expect(MockRestRequestMatchers.requestTo("https://jsonplaceholder.typicode.com/users/1"))
                .andRespond(MockRestResponseCreators.withSuccess(objectMapper.writeValueAsString(user), MediaType.APPLICATION_JSON));
        // THEN
        final var resultUser = this.userRestClient.getById(1);
        assertThat(resultUser.name()).isEqualTo(user.name());
        assertThat(resultUser.phone()).isEqualTo(user.phone());
        assertThat(resultUser.address()).isEqualTo(user.address());
        assertThat(resultUser.company()).isEqualTo(user.company());
    }
}