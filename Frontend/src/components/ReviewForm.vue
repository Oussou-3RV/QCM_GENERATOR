
<template>
    <div class="bg-white rounded-2xl shadow-xl p-8">
      <h3 class="text-2xl font-bold text-gray-800 mb-6">
        ⭐ Donnez votre avis
      </h3>
      
      <!-- Message de succès -->
      <div v-if="success" class="mb-6 p-4 bg-green-50 border border-green-200 rounded-lg text-green-700 flex items-center gap-3">
        <span class="text-2xl">✅</span>
        <span class="font-medium">Merci pour votre avis !</span>
      </div>
      
      <!-- Formulaire -->
      <form @submit.prevent="submitReview" class="space-y-6">
        <!-- Nom -->
        <div>
          <label class="block text-sm font-semibold text-gray-700 mb-2">
            Votre nom complet <span class="text-red-500">*</span>
          </label>
          <input
            v-model="name"
            type="text"
            placeholder="Ex: Ousmane Balde"
            maxlength="100"
            class="w-full px-4 py-3 border-2 border-gray-300 rounded-lg focus:border-indigo-500 focus:ring-2 focus:ring-indigo-200 transition-all"
          />
        </div>
        
        <!-- Note (étoiles) -->
        <div>
          <label class="block text-sm font-semibold text-gray-700 mb-3">
            Votre note <span class="text-red-500">*</span>
          </label>
          <div class="flex items-center gap-2">
            <button
              v-for="star in 5"
              :key="star"
              type="button"
              @click="selectRating(star)"
              class="text-4xl transition-all hover:scale-110"
            >
              <span v-if="star <= rating" class="text-yellow-400">⭐</span>
              <span v-else class="text-gray-300">☆</span>
            </button>
            <span class="ml-3 text-lg font-medium text-gray-600">
              {{ rating }}/5
            </span>
          </div>
        </div>
        
        <!-- Commentaire (optionnel) -->
        <div>
          <label class="block text-sm font-semibold text-gray-700 mb-2">
            Votre commentaire (optionnel)
          </label>
          <textarea
            v-model="comment"
            placeholder="Partagez votre expérience avec l'application..."
            maxlength="500"
            rows="4"
            class="w-full px-4 py-3 border-2 border-gray-300 rounded-lg focus:border-indigo-500 focus:ring-2 focus:ring-indigo-200 transition-all resize-none"
          ></textarea>
          <p class="text-xs text-gray-500 mt-1">
            {{ comment.length }}/500 caractères
          </p>
        </div>
        
        <!-- Message d'erreur -->
        <div v-if="error" class="p-4 bg-red-50 border border-red-200 rounded-lg text-red-700">
          {{ error }}
        </div>
        
        <!-- Bouton d'envoi -->
        <button
          type="submit"
          :disabled="loading"
          class="w-full bg-indigo-600 hover:bg-indigo-700 text-white font-semibold py-4 px-6 rounded-lg transition-all disabled:bg-gray-400 disabled:cursor-not-allowed text-lg"
        >
          <span v-if="!loading">🚀 Envoyer mon avis</span>
          <span v-else>⏳ Envoi en cours...</span>
        </button>
      </form>
    </div>
  </template>


<script setup>
import { ref } from 'vue'
import axios from 'axios'

// ========================================
// EMITS
// ========================================
const emit = defineEmits(['review-submitted'])

// ========================================
// STATE
// ========================================
const name = ref('')
const rating = ref(5) // Note par défaut : 5 étoiles
const comment = ref('')
const loading = ref(false)
const error = ref('')
const success = ref(false)

// ========================================
// FONCTIONS
// ========================================

/**
 * Soumettre l'avis
 */
const submitReview = async () => {
  // Validation
  if (!name.value.trim()) {
    error.value = 'Veuillez entrer votre nom'
    return
  }
  
  if (!rating.value) {
    error.value = 'Veuillez sélectionner une note'
    return
  }
  
  loading.value = true
  error.value = ''
  success.value = false
  
  try {
    await axios.post(`${import.meta.env.VITE_API_URL}/api/reviews`, {
      name: name.value.trim(),
      rating: rating.value,
      comment: comment.value.trim() || null
    })
    
    // Succès
    success.value = true
    
    // Réinitialiser le formulaire
    name.value = ''
    rating.value = 5
    comment.value = ''
    
    // Notifier le parent
    emit('review-submitted')
    
    // Masquer le message de succès après 3 secondes
    setTimeout(() => {
      success.value = false
    }, 3000)
    
  } catch (err) {
    error.value = err.response?.data?.message || 'Erreur lors de l\'envoi de votre avis'
    console.error(err)
  } finally {
    loading.value = false
  }
}

/**
 * Sélectionner une note en cliquant sur les étoiles
 */
const selectRating = (value) => {
  rating.value = value
}
</script>
