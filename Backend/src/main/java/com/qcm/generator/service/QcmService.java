package com.qcm.generator.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qcm.generator.model.Question;
import com.qcm.generator.model.QcmResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QcmService {

    @Autowired
    private OpenAIService openAIService;

    private final ObjectMapper objectMapper = new ObjectMapper();

//    public QcmResponse generateQcm(String courseText) {
//        try {
//            // Appeler OpenAI pour générer les questions
//            String aiResponse = openAIService.generateQcmWithAI(courseText);
//
//            // Parser la réponse JSON
//            JsonNode rootNode = objectMapper.readTree(aiResponse);
//            JsonNode questionsNode = rootNode.get("questions");
//
//            List<Question> questions = new ArrayList<>();
//
//            // Convertir chaque question JSON en objet Question
//            for (JsonNode questionNode : questionsNode) {
//                int id = questionNode.get("id").asInt();
//                String questionText = questionNode.get("question").asText();
//                int correctAnswer = questionNode.get("correctAnswer").asInt();
//
//                // Récupérer les options
//                List<String> options = new ArrayList<>();
//                JsonNode optionsNode = questionNode.get("options");
//                for (JsonNode optionNode : optionsNode) {
//                    options.add(optionNode.asText());
//                }
//
//                // Créer l'objet Question
//                Question question = new Question(id, questionText, options, correctAnswer);
//                questions.add(question);
//            }
//
//            return new QcmResponse(questions);
//
//        } catch (Exception e) {
//            // En cas d'erreur avec l'IA, utiliser l'ancien système
//            System.err.println("Erreur OpenAI : " + e.getMessage());
//            return generateQcmFallback(courseText);
//        }
//    }

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

            // Parser la réponse JSON
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
            // En cas d'erreur, afficher plus de détails
            System.err.println("Erreur complète : " + e.getClass().getName());
            System.err.println("Message : " + e.getMessage());
            e.printStackTrace();

            // En cas d'erreur avec l'IA, utiliser l'ancien système
            return generateQcmFallback(courseText);
        }
    }

    // Méthode de secours (l'ancien système)
    private QcmResponse generateQcmFallback(String courseText) {
        List<Question> questions = new ArrayList<>();

        String[] sentences = courseText.split("\\.");
        List<String> validSentences = new ArrayList<>();

        for (String sentence : sentences) {
            sentence = sentence.trim();
            String[] words = sentence.split("\\s+");
            if (words.length >= 5) {
                validSentences.add(sentence);
            }
        }

        int numQuestions = Math.min(3, validSentences.size());

        for (int i = 0; i < numQuestions; i++) {
            Question question = new Question(
                    i + 1,
                    "Question générée automatiquement (IA indisponible)",
                    List.of("Option A", "Option B", "Option C", "Option D"),
                    0
            );
            questions.add(question);
        }

        return new QcmResponse(questions);
    }
}