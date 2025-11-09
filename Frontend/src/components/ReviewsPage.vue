
<template>
    <div class="space-y-8">
      <!-- En-tête -->
      <div class="bg-white rounded-2xl shadow-xl p-8 text-center">
        <h2 class="text-4xl font-bold text-gray-800 mb-3">
          💬 Avis des utilisateurs
        </h2>
        <p class="text-gray-600 text-lg">
          Partagez votre expérience et consultez les avis des autres utilisateurs
        </p>
      </div>
  
      <!-- Formulaire d'avis -->
      <ReviewForm @review-submitted="handleReviewSubmitted" />
  
      <!-- Liste des avis et statistiques -->
      <ReviewsList :refreshTrigger="refreshTrigger" />
    </div>
  </template>

<script setup>
import { ref } from 'vue'
import ReviewForm from './ReviewForm.vue'
import ReviewsList from './ReviewsList.vue'

// ========================================
// STATE
// ========================================
// Trigger pour forcer le rechargement de la liste après soumission
const refreshTrigger = ref(0)

// ========================================
// FONCTIONS
// ========================================

/**
 * Quand un avis est soumis, recharger la liste
 */
const handleReviewSubmitted = () => {
  refreshTrigger.value++
}
</script>
