package com.qcm.generator.repository;

import com.qcm.generator.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // Récupérer tous les avis triés par date (plus récent en premier)
    List<Review> findAllByOrderByCreatedAtDesc();

    // Calculer la moyenne des notes
    @Query("SELECT AVG(r.rating) FROM Review r")
    Double findAverageRating();

    // Compter le nombre d'avis
    @Query("SELECT COUNT(r) FROM Review r")
    Long countReviews();
}