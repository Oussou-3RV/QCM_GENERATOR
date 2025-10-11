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

@RestController
@RequestMapping("/api/qcm")
public class QcmController {

    @Autowired
    private QcmService qcmService;

    @Autowired
    private PdfService pdfService;

    @PostMapping("/generate")
    public ResponseEntity<QcmResponse> generateQcm(@RequestBody CourseRequest request) {
        try {
            QcmResponse response = qcmService.generateQcm(request.getCourseText());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/generate-from-pdf")
    public ResponseEntity<QcmResponse> generateQcmFromPdf(@RequestParam("file") MultipartFile file) {
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

        } catch (Exception e) {
            System.err.println("Erreur: "+e.getMessage());
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("QCM Generator Backend is running!");
    }
}