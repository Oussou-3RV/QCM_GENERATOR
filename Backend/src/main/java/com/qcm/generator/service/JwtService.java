package com.qcm.generator.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Service pour gérer les tokens JWT
 * Génère, valide et extrait les informations des tokens
 */
@Service
public class JwtService {

    // Clé secrète pour signer les tokens (à mettre dans application.properties)
    @Value("${jwt.secret}")
    private String SECRET_KEY;

    // Durée de validité du token (24 heures)
    private static final long JWT_EXPIRATION = 1000 * 60 * 60 * 24; // 24h en millisecondes

    // ========================================
    // GÉNÉRATION DU TOKEN
    // ========================================

    /**
     * Génère un token JWT pour un utilisateur
     * @param email Email de l'utilisateur
     * @return Token JWT
     */
    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, email);
    }

    /**
     * Crée un token JWT avec des claims et un subject (email)
     */
    private String createToken(Map<String, Object> claims, String subject) {
        Date now = new Date();
        Date expiration = new Date(now.getTime() + JWT_EXPIRATION);

        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)                    // Email de l'utilisateur
                .setIssuedAt(now)                       // Date de création
                .setExpiration(expiration)              // Date d'expiration
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)  // Signature
                .compact();
    }

    // ========================================
    // VALIDATION DU TOKEN
    // ========================================

    /**
     * Valide un token JWT
     * @param token Token à valider
     * @param email Email de l'utilisateur
     * @return true si le token est valide
     */
    public Boolean validateToken(String token, String email) {
        final String extractedEmail = extractEmail(token);
        return (extractedEmail.equals(email) && !isTokenExpired(token));
    }

    /**
     * Vérifie si le token a expiré
     */
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // ========================================
    // EXTRACTION DES DONNÉES DU TOKEN
    // ========================================

    /**
     * Extrait l'email du token
     */
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extrait la date d'expiration du token
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * Extrait un claim spécifique du token
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /**
     * Extrait tous les claims du token
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // ========================================
    // CLÉ DE SIGNATURE
    // ========================================

    /**
     * Récupère la clé de signature à partir du SECRET_KEY
     */
    private Key getSigningKey() {
        byte[] keyBytes = SECRET_KEY.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }
}