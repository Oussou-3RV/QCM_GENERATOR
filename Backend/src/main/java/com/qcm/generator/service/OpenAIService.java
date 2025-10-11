package com.qcm.generator.service;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.completion.chat.ChatMessageRole;
import com.theokanning.openai.service.OpenAiService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Service
public class OpenAIService {

    //Lit la clé API depuis application.properties
    @Value("${openai.api.key}")
    private String apiKey;

    public String generateQcmWithAI(String courseText) {
        // Créer le service OpenAI
        OpenAiService service = new OpenAiService(apiKey, Duration.ofSeconds(60));

        // Créer le prompt (instructions pour l'IA)
        String systemPrompt = "Tu es un assistant pédagogique expert en création de QCM (Questions à Choix Multiples). " +
                "À partir d'un texte de cours fourni, tu génères automatiquement plusieurs questions de qualité, " +
                "avec 4 options de réponse dont une seule correcte. Adapte le nombre de questions à la taille du texte : " +
                "- pour un texte court (moins de 100 lignes), crée 5 à 10 questions, " +
                "- pour un texte moyen (100 à 500 lignes), crée 15 à 25 questions, " +
                "- pour un texte long (plus de 500 lignes), crée 30 questions ou plus. " +
                "Les questions doivent couvrir l'ensemble des concepts importants du texte, être claires et pédagogiques, " +
                "et les réponses incorrectes doivent être plausibles pour tester la compréhension.";

        String userPrompt = "Génère 3 ou plus questions si possible à choix multiples (QCM) à partir du texte suivant.\n\n" +
                "FORMAT DE RÉPONSE (respecte EXACTEMENT ce format JSON) :\n" +
                "{\n" +
                "  \"questions\": [\n" +
                "    {\n" +
                "      \"id\": 1,\n" +
                "      \"question\": \"Quelle est la question ?\",\n" +
                "      \"options\": [\"Option A\", \"Option B\", \"Option C\", \"Option D\"],\n" +
                "      \"correctAnswer\": 0\n" +
                "    }\n" +
                "  ]\n" +
                "}\n\n" +
                "RÈGLES :\n" +
                "- 3 ou plus questions si possible\n" +
                "- 4 options par question\n" +
                "- correctAnswer est l'index de la bonne réponse (0, 1, 2 ou 3)\n" +
                "- Questions pertinentes et pédagogiques\n" +
                "- Une seule bonne réponse par question\n" +
                "- Distracteurs plausibles mais clairement incorrects\n\n" +
                "TEXTE DU COURS :\n" + courseText;

        // Créer les messages
        List<ChatMessage> messages = new ArrayList<>();
        messages.add(new ChatMessage(ChatMessageRole.SYSTEM.value(), systemPrompt));
        messages.add(new ChatMessage(ChatMessageRole.USER.value(), userPrompt));

        // Créer la requête
        ChatCompletionRequest completionRequest = ChatCompletionRequest.builder()
                .model("gpt-4o-mini")
                .messages(messages)
                .temperature(0.7)
                .maxTokens(1000)
                .build();

        // Appeler l'API OpenAI
        String response = service.createChatCompletion(completionRequest)
                .getChoices()
                .get(0)
                .getMessage()
                .getContent();

        // Fermer le service
        service.shutdownExecutor();

        return response;
    }
}