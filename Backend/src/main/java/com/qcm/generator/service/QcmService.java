package com.qcm.generator.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qcm.generator.dto.Question;
import com.qcm.generator.dto.QcmResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QcmService {

    @Autowired
    private OpenAIService openAIService;
    //librairie JSON pour lire ,ecrire,convertir json en object
    private final ObjectMapper objectMapper = new ObjectMapper();

    public QcmResponse generateQcm(String courseText) {
        try {
            // Appeler OpenAI pour générer les questions
            String aiResponse = openAIService.generateQcmWithAI(courseText);

            // ========================================
            // NETTOYAGE DE LA RÉPONSE CAR
            // L'IA peut renvoyer du texte avec des balises markdown
            // ========================================

            // Supprimer les balises markdown si présentes
            aiResponse = aiResponse.trim();

            // Retirer ```json au début
            if (aiResponse.startsWith("```json")) {
                aiResponse = aiResponse.substring(7); // Enlever "```json"
            } else if (aiResponse.startsWith("```")) {
                aiResponse = aiResponse.substring(3); // Enlever "```"
            }

            // Retirer ``` à la fin
            if (aiResponse.endsWith("```")) {
                aiResponse = aiResponse.substring(0, aiResponse.length() - 3);
            }

            // Enlever les espaces et sauts de ligne inutiles
            aiResponse = aiResponse.trim();

            // ========================================
            // PARSING JSON
            // ========================================

            // Parser la réponse JSON ou lire json sous forme d'arbre
            JsonNode rootNode = objectMapper.readTree(aiResponse);
            JsonNode questionsNode = rootNode.get("questions");

            List<Question> questions = new ArrayList<>();

            // Convertir chaque question JSON en objet Question
            for (JsonNode questionNode : questionsNode) {
                int id = questionNode.get("id").asInt();
                String questionText = questionNode.get("question").asText();
                int correctAnswer = questionNode.get("correctAnswer").asInt();

                // Récupérer les options
                List<String> options = new ArrayList<>();
                JsonNode optionsNode = questionNode.get("options");
                for (JsonNode optionNode : optionsNode) {
                    options.add(optionNode.asText());
                }

                // Créer l'objet Question
                Question question = new Question(id, questionText, options, correctAnswer);
                questions.add(question);
            }

            return new QcmResponse(questions);

        } catch (Exception e) {
            // En cas d'erreur, Afficher les détails de l'erreur dans les logs

            System.err.println("❌ Erreur lors de la génération du QCM avec l'IA");
            System.err.println("Type d'erreur : " + e.getClass().getName());
            System.err.println("Message : " + e.getMessage());
            e.printStackTrace();

            // Lancer une exception avec un message clair
            throw new RuntimeException("L'intelligence artificielle est temporairement indisponible pour générer les questions. Veuillez réessayer plus tard ou contacter le propriétaire du site.");
        }
    }

}