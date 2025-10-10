package com.qcm.generator.controller;

import com.qcm.generator.model.CourseRequest;
import com.qcm.generator.model.QcmResponse;
import com.qcm.generator.service.QcmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/qcm")
public class QcmController {

    @Autowired
    private QcmService qcmService;

    @PostMapping("/generate")
    public ResponseEntity<QcmResponse> generateQcm(@RequestBody CourseRequest request) {
        try {
            QcmResponse response = qcmService.generateQcm(request.getCourseText());
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("QCM Generator Backend is running!");
    }
}