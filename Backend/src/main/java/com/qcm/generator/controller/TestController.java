package com.qcm.generator.controller;

import com.qcm.generator.model.TestRequest;
import com.qcm.generator.model.TestResponse;
import com.qcm.generator.model.TestResponse;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")  //Toutes les routes de ce controller commencent par /api
@CrossOrigin(origins = {"http://localhost:5173", "https://*.vercel.app"}) //Accepter les requêtes venant de cet url
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
