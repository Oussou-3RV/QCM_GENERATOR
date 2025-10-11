package com.qcm.generator.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PdfService {

    public String extractTextFromPdf(MultipartFile file) throws IOException {

        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);

            // Nettoyer le texte
            text = text.trim();

            if (text.isEmpty()) {
                throw new IOException("Le PDF ne contient pas de texte extractible");
            }

            return text;
        }
    }
}