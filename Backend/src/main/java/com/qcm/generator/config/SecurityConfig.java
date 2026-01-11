package com.qcm.generator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // Routes publiques
                        .requestMatchers("/api/test").permitAll()  // juste pour les tests
                        .requestMatchers("/api/auth/**").permitAll()
                        // Routes protégées
                        .requestMatchers("/api/qcm/**").authenticated()
                        .requestMatchers("/api/reviews/**").authenticated()
                        // Tout le reste
                        .anyRequest().authenticated()
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        return http.build();
    }
}