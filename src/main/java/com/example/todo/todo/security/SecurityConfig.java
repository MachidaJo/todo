package com.example.todo.todo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
        http
        .authorizeHttpRequests(request -> request
        .anyRequest().authenticated())
        .formLogin(login -> login
        .loginProcessingUrl("/login")
        .defaultSuccessUrl("/todo")
        .failureUrl("/login?error")
        /permitAll()
        );
        return http.build();
    }
}
