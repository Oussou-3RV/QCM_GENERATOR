package com.qcm.generator.controller;

import com.qcm.generator.dto.AuthResponse;
import com.qcm.generator.dto.LoginRequest;
import com.qcm.generator.dto.SignupRequest;
import com.qcm.generator.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Controller REST pour l'authentification
 * Endpoints : /api/auth/signup et /api/auth/login
 */
@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*") // Permet les requêtes depuis le frontend
public class AuthController {

    @Autowired
    private AuthService authService;

    // ========================================
    // INSCRIPTION
    // ========================================

    /**
     * POST /api/auth/signup
     * Inscrit un nouvel utilisateur
     * @param request Données d'inscription (name, email, password)
     * @return AuthResponse avec token JWT
     */
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest request) {
        try {
            AuthResponse response = authService.signUp(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            // Si l'email existe déjà ou autre erreur
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "SIGNUP_ERROR");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    // ========================================
    // CONNEXION
    // ========================================

    /**
     * POST /api/auth/login
     * Connecte un utilisateur existant
     * @param request Données de connexion (email, password)
     * @return AuthResponse avec token JWT
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest request) {
        try {
            AuthResponse response = authService.login(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            // Si email/password incorrect
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "LOGIN_ERROR");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponse);
        }
    }

    // ========================================
    // ENDPOINT DE TEST
    // ========================================

    /**
     * GET /api/auth/test
     * Endpoint simple pour tester que le controller fonctionne
     */
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Auth controller is working! 🚀");
    }
}