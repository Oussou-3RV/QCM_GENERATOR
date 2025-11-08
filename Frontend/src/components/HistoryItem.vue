<script setup>
// ========================================
// PROPS (données d'un QCM sauvegardé)
// ========================================
const props = defineProps({
  historyItem: {
    type: Object,
    required: true
  }
})

// ========================================
// EMITS (événements vers le parent)
// ========================================
const emit = defineEmits(['replay', 'delete'])

// ========================================
// FONCTIONS
// ========================================

// Formater la date en français
const formatDate = (isoDate) => {
  const date = new Date(isoDate)
  return new Intl.DateTimeFormat('fr-FR', {
    dateStyle: 'medium',
    timeStyle: 'short'
  }).format(date)
}

// Obtenir l'emoji selon le score
const getEmoji = () => {
  const pct = props.historyItem.percentage
  
  if (pct === 100) return '🏆'
  if (pct >= 80) return '🎉'
  if (pct >= 60) return '😊'
  if (pct >= 40) return '💪'
  if (pct > 0) return '📚'
  return '😅'
}

// Obtenir la couleur selon le score
const getScoreColor = () => {
  const pct = props.historyItem.percentage
  
  if (pct >= 80) return 'text-green-600'
  if (pct >= 60) return 'text-blue-600'
  if (pct >= 40) return 'text-orange-600'
  return 'text-red-600'
}
</script>

<template>
  <!-- Carte d'un QCM dans l'historique -->
  <div class="bg-white rounded-xl shadow-md hover:shadow-lg transition-all p-6 border-2 border-gray-100">
    <div class="flex items-start justify-between gap-4">
      <!-- Informations du QCM -->
      <div class="flex-1">
        <!-- Titre et date -->
        <div class="mb-3">
          <h3 class="text-lg font-semibold text-gray-800 mb-1 line-clamp-2">
            {{ historyItem.courseTitle }}
          </h3>
          <p class="text-sm text-gray-500">
            📅 {{ formatDate(historyItem.date) }}
          </p>
        </div>

        <!-- Statistiques -->
        <div class="flex flex-wrap gap-4 mb-4">
          <!-- Nombre de questions -->
          <div class="flex items-center gap-2 text-sm text-gray-600">
            <span class="font-medium">{{ historyItem.questionsCount }}</span>
            <span>question{{ historyItem.questionsCount > 1 ? 's' : '' }}</span>
          </div>

          <!-- Score -->
          <div class="flex items-center gap-2">
            <span class="text-2xl">{{ getEmoji() }}</span>
            <span :class="['text-lg font-bold', getScoreColor()]">
              {{ historyItem.score }}/{{ historyItem.questionsCount }}
            </span>
            <span :class="['text-sm font-medium', getScoreColor()]">
              ({{ historyItem.percentage }}%)
            </span>
          </div>
        </div>

        <!-- Boutons d'action -->
        <div class="flex gap-3">
          <!-- Bouton Refaire -->
          <button
            @click="emit('replay', historyItem)"
            class="flex items-center gap-2 bg-indigo-600 hover:bg-indigo-700 text-white px-4 py-2 rounded-lg transition-all text-sm font-medium"
          >
            🔄 Refaire
          </button>

          <!-- Bouton Supprimer -->
          <button
            @click="emit('delete', historyItem.id)"
            class="flex items-center gap-2 bg-red-100 hover:bg-red-200 text-red-600 px-4 py-2 rounded-lg transition-all text-sm font-medium"
          >
            🗑️ Supprimer
          </button>
        </div>
      </div>

      <!-- Badge du pourcentage (grand) -->
      <div class="flex-shrink-0">
        <div :class="[
          'w-20 h-20 rounded-full flex items-center justify-center text-2xl font-bold border-4',
          historyItem.percentage >= 80 ? 'bg-green-50 border-green-500 text-green-700' :
          historyItem.percentage >= 60 ? 'bg-blue-50 border-blue-500 text-blue-700' :
          historyItem.percentage >= 40 ? 'bg-orange-50 border-orange-500 text-orange-700' :
          'bg-red-50 border-red-500 text-red-700'
        ]">
          {{ historyItem.percentage }}%
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
/* Limiter le texte à 2 lignes */
.line-clamp-2 {
  display: -webkit-box;
  -webkit-line-clamp: 2;
  -webkit-box-orient: vertical;
  overflow: hidden;
}
</style>