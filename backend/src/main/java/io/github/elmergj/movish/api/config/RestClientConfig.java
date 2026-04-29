package io.github.elmergj.movish.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Value("${api.catalog.base-url}")
    private String BASE_URL;

    @Value("${api.catalog.token}")
    private String KEY;

    @Bean
    RestClient restClient() {
        return RestClient
                .builder()
                .baseUrl(BASE_URL)
                .defaultHeader("Authorization", "Bearer " + KEY)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}