<template>
    <div>
      <!-- Statistiques -->
      <div class="bg-white rounded-2xl shadow-xl p-8 mb-8">
        <h3 class="text-2xl font-bold text-gray-800 mb-6">
          📊 Statistiques des avis
        </h3>
        
        <div class="grid grid-cols-1 md:grid-cols-2 gap-6">
          <!-- Note moyenne -->
          <div class="bg-gradient-to-br from-yellow-50 to-orange-50 rounded-xl p-6 text-center border-2 border-yellow-200">
            <div class="text-5xl font-bold text-yellow-600 mb-2">
              {{ averageRatingRounded }}
            </div>
            <div class="text-2xl mb-2">
              {{ getStars(Math.round(stats.averageRating)).filled }}{{ getStars(Math.round(stats.averageRating)).empty }}
            </div>
            <div class="text-sm text-gray-600">Note moyenne</div>
          </div>
          
          <!-- Nombre total d'avis -->
          <div class="bg-gradient-to-br from-indigo-50 to-blue-50 rounded-xl p-6 text-center border-2 border-indigo-200">
            <div class="text-5xl font-bold text-indigo-600 mb-2">
              {{ stats.totalReviews }}
            </div>
            <div class="text-sm text-gray-600">
              Avis {{ stats.totalReviews > 1 ? 'laissés' : 'laissé' }}
            </div>
          </div>
        </div>
      </div>
      
      <!-- Liste des avis -->
      <div class="bg-white rounded-2xl shadow-xl p-8">
        <h3 class="text-2xl font-bold text-gray-800 mb-6">
          💬 Tous les avis
        </h3>
        
        <!-- Loading -->
        <div v-if="loading" class="text-center py-8">
          <div class="text-4xl mb-2">⏳</div>
          <p class="text-gray-600">Chargement des avis...</p>
        </div>
        
        <!-- Liste -->
        <div v-else-if="reviews.length > 0" class="space-y-6">
          <div
            v-for="review in reviews"
            :key="review.id"
            class="border-2 border-gray-100 rounded-xl p-6 hover:border-indigo-200 transition-all"
          >
            <!-- Header : Nom et note -->
            <div class="flex items-start justify-between mb-3">
              <div>
                <h4 class="font-bold text-lg text-gray-800">{{ review.name }}</h4>
                <p class="text-sm text-gray-500">{{ formatDate(review.createdAt) }}</p>
              </div>
              <div class="text-2xl">
                {{ getStars(review.rating).filled }}{{ getStars(review.rating).empty }}
              </div>
            </div>
            
            <!-- Commentaire -->
            <p v-if="review.comment" class="text-gray-700 leading-relaxed">
              {{ review.comment }}
            </p>
            <p v-else class="text-gray-400 italic">
              Aucun commentaire
            </p>
          </div>
        </div>
        
        <!-- Aucun avis -->
        <div v-else class="text-center py-12">
          <div class="text-6xl mb-4">💭</div>
          <h4 class="text-xl font-bold text-gray-800 mb-2">
            Aucun avis pour le moment
          </h4>
          <p class="text-gray-600">
            Soyez le premier à donner votre avis !
          </p>
        </div>
      </div>
    </div>
  </template>

<script setup>
import { ref, onMounted, computed } from 'vue'
import axios from 'axios'

// ========================================
// PROPS
// ========================================
const props = defineProps({
  refreshTrigger: {
    type: Number,
    default: 0
  }
})

// ========================================
// STATE
// ========================================
const reviews = ref([])
const stats = ref({ averageRating: 0, totalReviews: 0 })
const loading = ref(true)

// ========================================
// COMPUTED
// ========================================
const averageRatingRounded = computed(() => {
  return stats.value.averageRating ? stats.value.averageRating.toFixed(1) : '0.0'
})

// ========================================
// FONCTIONS
// ========================================

/**
 * Charger les avis et les statistiques
 */
const loadReviews = async () => {
  loading.value = true
  
  try {
    // Charger les avis
    const reviewsResponse = await axios.get(`${import.meta.env.VITE_API_URL}/api/reviews`)
    reviews.value = reviewsResponse.data
    
    // Charger les statistiques
    const statsResponse = await axios.get(`${import.meta.env.VITE_API_URL}/api/reviews/stats`)
    stats.value = statsResponse.data
    
  } catch (err) {
    console.error('Erreur lors du chargement des avis:', err)
  } finally {
    loading.value = false
  }
}

/**
 * Formater la date en français
 */
const formatDate = (dateString) => {
  const date = new Date(dateString)
  return new Intl.DateTimeFormat('fr-FR', {
    dateStyle: 'long',
    timeStyle: 'short'
  }).format(date)
}

/**
 * Générer les étoiles pleines/vides
 */
const getStars = (rating) => {
  return {
    filled: '⭐'.repeat(rating),
    empty: '☆'.repeat(5 - rating)
  }
}

// ========================================
// LIFECYCLE & WATCHERS
// ========================================
onMounted(() => {
  loadReviews()
})

// Recharger quand refreshTrigger change
import { watch } from 'vue'
watch(() => props.refreshTrigger, () => {
  loadReviews()
})
</script>

