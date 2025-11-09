package com.qcm.generator.controller;

import com.qcm.generator.dto.ReviewRequest;
import com.qcm.generator.dto.ReviewStatsResponse;
import com.qcm.generator.entity.Review;
import com.qcm.generator.service.ReviewService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "http://localhost:5173") // Pour le dev local
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    /**
     * Créer un nouvel avis
     * POST /api/reviews
     */
    @PostMapping
    public ResponseEntity<?> createReview(@Valid @RequestBody ReviewRequest request) {
        try {
            Review review = reviewService.createReview(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(review);
        } catch (Exception e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "CREATION_ERROR");
            errorResponse.put("message", "Erreur lors de la création de l'avis");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }

    /**
     * Récupérer tous les avis
     * GET /api/reviews
     */
    @GetMapping
    public ResponseEntity<List<Review>> getAllReviews() {
        List<Review> reviews = reviewService.getAllReviews();
        return ResponseEntity.ok(reviews);
    }

    /**
     * Récupérer les statistiques des avis
     * GET /api/reviews/stats
     */
    @GetMapping("/stats")
    public ResponseEntity<ReviewStatsResponse> getReviewStats() {
        ReviewStatsResponse stats = reviewService.getReviewStats();
        return ResponseEntity.ok(stats);
    }

    /**
     * Supprimer un avis
     * DELETE /api/reviews/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteReview(@PathVariable Long id) {
        boolean deleted = reviewService.deleteReview(id);

        if (deleted) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Avis supprimé avec succès");
            return ResponseEntity.ok(response);
        } else {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", "NOT_FOUND");
            errorResponse.put("message", "Avis non trouvé");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}