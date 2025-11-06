package com.qcm.generator.controller;

import com.qcm.generator.model.CourseRequest;
import com.qcm.generator.model.QcmResponse;
import com.qcm.generator.service.PdfService;
import com.qcm.generator.service.QcmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/qcm")
public class QcmController {

    @Autowired
    private QcmService qcmService;

    @Autowired
    private PdfService pdfService;

    @PostMapping("/generate")
    public ResponseEntity<?> generateQcm(@RequestBody CourseRequest request) {
        try {
            QcmResponse response = qcmService.generateQcm(request.getCourseText());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            // Créer un objet d'erreur clair pour le frontend
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "IA_UNAVAILABLE");
            errorResponse.put("message", e.getMessage());

            System.err.println("❌ Erreur retournée au client : " + e.getMessage());

            return ResponseEntity.status(503).body(errorResponse); // 503 = Service Unavailable
        }
    }

    @PostMapping("/generate-from-pdf")
    public ResponseEntity<?> generateQcmFromPdf(@RequestParam("file") MultipartFile file) {
        try {
            // verifier que c'est bien un PDF
            if(!file.getContentType().equals("application/pdf")) {
                return ResponseEntity.badRequest().build();
            }

            //extraire le texte du pdf
            String courseText = pdfService.extractTextFromPdf(file);

            //Generer le QCM
            QcmResponse response = qcmService.generateQcm(courseText);
            return ResponseEntity.ok(response);

        }catch (RuntimeException e) {
            // Erreur IA
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "IA_UNAVAILABLE");
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(503).body(errorResponse);

        }catch (IOException e) {
            // Erreur PDF
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "PDF_EXTRACTION_ERROR");
            errorResponse.put("message", "Erreur lors de la lecture du PDF. Vérifiez que le fichier contient du texte extractible.");
            return ResponseEntity.status(400).body(errorResponse);

        } catch (Exception e) {
            // Erreur générique
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "UNKNOWN_ERROR");
            errorResponse.put("message", "Une erreur inattendue s'est produite.");
            return ResponseEntity.status(500).body(errorResponse);
        }
    }
}