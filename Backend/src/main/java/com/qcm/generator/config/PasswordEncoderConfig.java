package com.qcm.generator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Configuration pour le hashage des mots de passe
 * Utilise BCrypt qui est le standard de l'industrie
 */
@Configuration
public class PasswordEncoderConfig {

    /**
     * Bean PasswordEncoder utilisant BCrypt
     * BCrypt hash les mots de passe de manière sécurisée (irreversible)
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}