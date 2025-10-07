package com.qcm.generator.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//Dit à Spring que cette classe gère des requêtes HTTP
@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return "Hello World ! Bienvenue sur QCM Generator 🚀";
    }
}