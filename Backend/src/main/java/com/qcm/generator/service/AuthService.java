package com.qcm.generator.service;

import com.qcm.generator.dto.AuthResponse;
import com.qcm.generator.dto.LoginRequest;
import com.qcm.generator.dto.SignupRequest;
import com.qcm.generator.entity.Role;
import com.qcm.generator.entity.User;
import com.qcm.generator.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
    * Service d'authentification
    * Gère l'inscription, la connexion et la validation des utilisateurs
 */

@Service
public class AuthService {


    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder passwordEncoder;


     // ========================================
     // INSCRIPTION (SIGNUP)
     // ========================================
    /**
        * Inscrit un nouvel utilisateur
        * @param request Données d'inscription (name, email, password)
        * @return AuthResponse avec le token JWT
        * @throws RuntimeException si l'email existe déjà
     **/

    public AuthResponse signUp(SignupRequest request){

        // 1. Vérifier si l'email existe déjà
        if(userRepository.existsByEmail(request.getEmail())){
            throw new RuntimeException("Cet email est déjà utilisé");
        }

        // 2. Créer le nouvel utilisateur
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.FREE);

        // 3. Sauvegarder en base de données
        User savedUser = userRepository.save(user);

        // 4. Générer le token JWT
        String token = jwtService.generateToken(savedUser.getEmail());

        // 5. Retourner la réponse avec le token et les infos user
        return new AuthResponse(
            token, savedUser.getId(), savedUser.getName(), savedUser.getEmail(),  savedUser.getRole()
        );
    }

    // ========================================
    // CONNEXION (LOGIN)
    // ========================================

          /**
           * * Connecte un utilisateur existant
            * @param request Données de connexion (email, password)
            * @return AuthResponse avec le token JWT
            * @throws RuntimeException si email/password incorrect
           * * */

    public AuthResponse login(LoginRequest request){
        // 1. Trouver l'utilisateur par email

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Email ou mot de passe incorrect"));

        // 2. Vérifier le mot de passe
        if(!passwordEncoder.matches(request.getPassword(), user.getPassword())){
            throw new RuntimeException("Password ou mot de passe incorrect");
        }

        // 3. Générer le token JWT
        String token = jwtService.generateToken(user.getEmail());

        // 4. Retourner la réponse avec le token et les infos user
        return new AuthResponse(token, user.getId(), user.getName(), user.getEmail(), user.getRole());
    }


}

