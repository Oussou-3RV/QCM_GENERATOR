package com.qcm.generator.dto;

public class ReviewStatsResponse {

    private Double averageRating;
    private Long totalReviews;

    // Constructeurs
    public ReviewStatsResponse() {
    }

    public ReviewStatsResponse(Double averageRating, Long totalReviews) {
        this.averageRating = averageRating;
        this.totalReviews = totalReviews;
    }

    // Getters et Setters
    public Double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(Double averageRating) {
        this.averageRating = averageRating;
    }

    public Long getTotalReviews() {
        return totalReviews;
    }

    public void setTotalReviews(Long totalReviews) {
        this.totalReviews = totalReviews;
    }
}