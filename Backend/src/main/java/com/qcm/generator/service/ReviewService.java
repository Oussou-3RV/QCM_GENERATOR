package com.qcm.generator.service;

import com.qcm.generator.dto.ReviewRequest;
import com.qcm.generator.dto.ReviewStatsResponse;
import com.qcm.generator.entity.Review;
import com.qcm.generator.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    /**
     * Créer un nouvel avis
     */
    public Review createReview(ReviewRequest request) {
        Review review = new Review(
                request.getName(),
                request.getRating(),
                request.getComment()
        );
        return reviewRepository.save(review);
    }

    /**
     * Récupérer tous les avis (triés par date décroissante)
     */
    public List<Review> getAllReviews() {
        return reviewRepository.findAllByOrderByCreatedAtDesc();
    }

    /**
     * Récupérer les statistiques des avis
     */
    public ReviewStatsResponse getReviewStats() {
        Double averageRating = reviewRepository.findAverageRating();
        Long totalReviews = reviewRepository.countReviews();

        // Si aucun avis, retourner 0
        if (averageRating == null) {
            averageRating = 0.0;
        }
        if (totalReviews == null) {
            totalReviews = 0L;
        }

        return new ReviewStatsResponse(averageRating, totalReviews);
    }

    /**
     * Supprimer un avis par ID
     */
    public boolean deleteReview(Long id) {
        if (reviewRepository.existsById(id)) {
            reviewRepository.deleteById(id);
            return true;
        }
        return false;
    }
}