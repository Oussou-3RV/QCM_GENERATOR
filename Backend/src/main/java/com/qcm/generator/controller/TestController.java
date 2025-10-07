package com.qcm.generator.controller;

import com.qcm.generator.model.TestRequest;
import com.qcm.generator.model.TestResponse;
import com.qcm.generator.model.TestResponse;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")  //Toutes les routes de ce controller commencent par /api
public class TestController {

    //Route POST sur /api/test
    @PostMapping("/test")
    public TestResponse testEndPoint(@RequestBody TestRequest testRequest) {
        //Cette Methode prend en parametre une requette et retourne une reponse
        String text = testRequest.getText();
        int length = text.length();

        String message = "J'ai reçu ton texte ! Il contient " + length + " caractères.";

        return new TestResponse(message, length);
    }

}
