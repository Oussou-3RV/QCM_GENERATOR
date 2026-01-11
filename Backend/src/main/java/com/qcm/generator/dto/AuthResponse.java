package com.qcm.generator.dto;

import com.qcm.generator.entity.Role;

/**
 * DTO pour la réponse d'authentification
 * Renvoyé après un login ou signup réussi
 * Contient le token JWT et les infos de l'utilisateur
 */
public class AuthResponse {

    private String token;      // Token JWT
    private Long id;           // ID de l'utilisateur
    private String name;       // Nom de l'utilisateur
    private String email;      // Email de l'utilisateur
    private Role role;         // Rôle (FREE ou PRO)

    // ========================================
    // CONSTRUCTEURS
    // ========================================

    public AuthResponse() {
    }

    public AuthResponse(String token, Long id, String name, String email, Role role) {
        this.token = token;
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
    }

    // ========================================
    // GETTERS ET SETTERS
    // ========================================

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}