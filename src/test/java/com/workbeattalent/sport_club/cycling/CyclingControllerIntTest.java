package com.workbeattalent.sport_club.cycling;

import com.workbeattalent.sport_club.api.ApiResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.client.RestClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CyclingControllerIntTest {

    @LocalServerPort
    private Integer randomServerPort;

    private RestClient restClient;

    private final static String URI = "/api/v1/sports/cycling";

    @BeforeEach
    void setUp() {
        restClient = RestClient.create("http://localhost:" + randomServerPort);
    }

    @Test
    void shouldGetAllTours() {
        final var response = this.restClient
                .get()
                .uri(URI)
                .retrieve()
                .body(new ParameterizedTypeReference<ApiResponse>() {
                });

        assertThat(response.message()).isEqualTo("All Cycling Tours");

    }
}