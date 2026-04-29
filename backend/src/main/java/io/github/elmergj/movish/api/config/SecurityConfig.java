package io.github.elmergj.movish.api.config;

import io.github.elmergj.movish.api.infrastructure.integration.authentication.FakeAuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, FakeAuthFilter fakeFilter) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Important to do PUT/POST from Postman
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll()) // Here we let everything flow through here!
                .addFilterBefore(fakeFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}