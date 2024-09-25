package com.workbeattalent.sport_club;

import com.workbeattalent.sport_club.user.UserHTTPClient;
import com.workbeattalent.sport_club.user.UserRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

@SpringBootApplication
public class SportClubApplication {

    public static void main(String[] args) {
        SpringApplication.run(SportClubApplication.class, args);
    }

    @Bean
    UserHTTPClient userHTTPClient() {
        final var restClient = RestClient.create("https://jsonplaceholder.typicode.com");
        final var factory = HttpServiceProxyFactory
                .builderFor(RestClientAdapter.create(restClient))
                .build();

        return factory.createClient(UserHTTPClient.class);
    }

    @Bean
    CommandLineRunner runner(UserHTTPClient client) {
        return args -> {
            final var user = client.findById(10);
            System.out.println(user);
        };
    }
}
