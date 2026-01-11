package com.qcm.generator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignupRequest request) {
        // 1. Vérifier si email existe déjà
        // 2. Hasher le password avec BCrypt
        // 3. Créer le user en BDD
        // 4. Générer un JWT token
        // 5. Retourner le token + infos user
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        // 1. Vérifier email + password
        // 2. Générer un JWT token
        // 3. Retourner le token + infos user
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout() {
        // Côté client, on supprime juste le token
        return ResponseEntity.ok("Logged out");
    }
}
