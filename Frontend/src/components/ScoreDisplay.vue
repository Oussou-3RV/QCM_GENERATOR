
<template>
  <div>
    <!-- Carte du score -->
    <div class="bg-white rounded-2xl shadow-xl p-8 mb-8 text-center">
      <!-- Emoji dynamique -->
      <div class="text-6xl mb-4">{{ emoji }}</div>
      
      <!-- Message dynamique -->
      <h2 class="text-3xl font-bold text-gray-800 mb-2">{{ message }}</h2>
      
      <!-- Score numérique -->
      <div class="text-6xl font-bold text-indigo-600 my-6">
        {{ score }} / {{ total }}
      </div>
      
      <!-- Pourcentage -->
      <div class="text-2xl text-gray-600 mb-6">
        Score : {{ percentage }}%
      </div>
      
      <!-- Bouton recommencer -->
      <button
        @click="emit('restart')"
        class="bg-indigo-600 hover:bg-indigo-700 text-white font-semibold py-3 px-8 rounded-lg transition-all"
      >
        🔄 Nouveau QCM
      </button>
    </div>

    <!-- Corrections détaillées -->
    <div class="bg-white rounded-2xl shadow-xl p-8">
      <h3 class="text-2xl font-bold text-gray-800 mb-6">📋 Corrections détaillées</h3>
      
      <div class="space-y-6">
        <!-- Boucle sur chaque question -->
        <div
          v-for="(question, index) in questions"
          :key="question.id"
          class="border-2 rounded-xl p-6"
          :class="userAnswers[index] === question.correctAnswer ? 'border-green-300 bg-green-50' : 'border-red-300 bg-red-50'"
        >
          <div class="flex items-start gap-3">
            <!-- Emoji de validation -->
            <div class="text-2xl">
              {{ userAnswers[index] === question.correctAnswer ? '✅' : '❌' }}
            </div>
            
            <div class="flex-1">
              <!-- Numéro de la question -->
              <h4 class="font-semibold text-gray-800 mb-2">Question {{ index + 1 }}</h4>
              
              <!-- Texte de la question -->
              <p class="text-gray-700 mb-4">{{ question.question }}</p>
              
              <!-- Options avec indication des bonnes/mauvaises réponses -->
              <div class="space-y-2">
                <div
                  v-for="(option, oIndex) in question.options"
                  :key="oIndex"
                  class="p-3 rounded-lg"
                  :class="{
                    'bg-green-100 border-2 border-green-400 font-semibold': oIndex === question.correctAnswer,
                    'bg-red-100 border-2 border-red-400': oIndex === userAnswers[index] && oIndex !== question.correctAnswer,
                    'bg-gray-50': oIndex !== question.correctAnswer && oIndex !== userAnswers[index]
                  }"
                >
                  <div class="flex items-center justify-between">
                    <!-- Texte de l'option -->
                    <span>{{ option }}</span>
                    
                    <!-- Indication "Bonne réponse" -->
                    <span v-if="oIndex === question.correctAnswer" class="text-green-600 font-semibold">
                      ✓ Bonne réponse
                    </span>
                    
                    <!-- Indication "Votre réponse" (si fausse) -->
                    <span v-else-if="oIndex === userAnswers[index]" class="text-red-600 font-semibold">
                      ✗ Votre réponse
                    </span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import { computed } from 'vue'

// ========================================
// PROPS (données reçues du parent)
// ========================================
const props = defineProps({
  score: {
    type: Number,
    required: true
  },
  total: {
    type: Number,
    required: true
  },
  questions: {
    type: Array,
    required: true
  },
  userAnswers: {
    type: Array,
    required: true
  }
})

// ========================================
// EMITS (événements envoyés au parent)
// ========================================
const emit = defineEmits(['restart'])

// ========================================
// COMPUTED (valeurs calculées)
// ========================================

// Calculer le pourcentage de réussite
const percentage = computed(() => {
  return Math.round((props.score / props.total) * 100)
})

// Obtenir l'emoji selon le score
const emoji = computed(() => {
  const pct = percentage.value
  
  if (pct === 100) return '🏆'  // Parfait
  if (pct >= 80) return '🎉'    // Excellent
  if (pct >= 60) return '😊'    // Bien
  if (pct >= 40) return '💪'    // Moyen
  if (pct > 0) return '📚'      // Faible
  return '😅'                   // Aucune bonne réponse
})

// Obtenir le message selon le score
const message = computed(() => {
  const pct = percentage.value
  
  if (pct === 100) return 'Parfait ! Score impeccable !'
  if (pct >= 80) return 'Excellent travail !'
  if (pct >= 60) return 'Très bien ! Continue comme ça !'
  if (pct >= 40) return 'Pas mal, mais tu peux faire mieux !'
  if (pct > 0) return 'Il faut réviser un peu plus...'
  return 'Aïe ! Reprends tes cours et réessaye !'
})
</script>
