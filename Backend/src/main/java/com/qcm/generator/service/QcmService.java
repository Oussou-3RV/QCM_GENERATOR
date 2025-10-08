package com.qcm.generator.service;

import com.qcm.generator.model.Question;
import com.qcm.generator.model.QcmResponse;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class QcmService {

    public QcmResponse generateQcm(String courseText) {
        List<Question> questions = new ArrayList<>();

        // Diviser le texte en phrases
        String[] sentences = courseText.split("\\.");

        // Filtrer les phrases valides (au moins 5 mots)
        List<String> validSentences = new ArrayList<>();
        for (String sentence : sentences) {
            sentence = sentence.trim();
            String[] words = sentence.split("\\s+");
            if (words.length >= 5) {
                validSentences.add(sentence);
            }
        }

        // Générer 3 questions maximum (ou moins si pas assez de phrases)
        int numQuestions = Math.min(3, validSentences.size());

        for (int i = 0; i < numQuestions; i++) {
            String sentence = validSentences.get(i);
            Question question = generateQuestionFromSentence(sentence, i + 1);
            questions.add(question);
        }

        return new QcmResponse(questions);
    }

    private Question generateQuestionFromSentence(String sentence, int questionId) {
        // Diviser la phrase en mots
        String[] words = sentence.split("\\s+");

        // Choisir un mot au hasard (éviter les petits mots)
        Random random = new Random();
        String targetWord = "";
        int attempts = 0;

        while (attempts < 10) {
            int randomIndex = random.nextInt(words.length);
            String word = words[randomIndex].replaceAll("[^a-zA-Zàâäéèêëïîôùûüÿæœç]", "");

            if (word.length() > 4) {
                targetWord = word;
                break;
            }
            attempts++;
        }

        // Si aucun mot trouvé, prendre le premier mot long
        if (targetWord.isEmpty()) {
            for (String word : words) {
                word = word.replaceAll("[^a-zA-Zàâäéèêëïîôùûüÿæœç]", "");
                if (word.length() > 4) {
                    targetWord = word;
                    break;
                }
            }
        }

        // Créer la question en remplaçant le mot par "______"
        String questionText = sentence.replace(targetWord, "______");

        // Créer les options
        List<String> options = new ArrayList<>();
        options.add(targetWord); // Bonne réponse
        options.add(targetWord + "s");
        options.add("Aucune réponse");
        options.add(generateRandomWord());

        // Mélanger les options
        Collections.shuffle(options);

        // Trouver l'index de la bonne réponse
        int correctIndex = options.indexOf(targetWord);

        return new Question(
                questionId,
                "Complétez la phrase : " + questionText,
                options,
                correctIndex
        );
    }

    private String generateRandomWord() {
        String[] randomWords = {"chose", "élément", "objet", "concept", "idée", "terme"};
        Random random = new Random();
        return randomWords[random.nextInt(randomWords.length)];
    }
}