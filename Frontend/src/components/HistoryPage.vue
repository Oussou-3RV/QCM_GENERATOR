<script setup>
import { ref, onMounted } from 'vue'
import HistoryItem from './HistoryItem.vue'
import { getHistory, deleteFromHistory, clearHistory } from '../services/historyService'

// ========================================
// EMITS
// ========================================
const emit = defineEmits(['replay-quiz'])

// ========================================
// STATE
// ========================================
const history = ref([])
const showClearConfirm = ref(false)

// ========================================
// FONCTIONS
// ========================================

// Charger l'historique au montage du composant
const loadHistory = () => {
  history.value = getHistory()
}

// Refaire un quiz depuis l'historique
const handleReplay = (historyItem) => {
  emit('replay-quiz', historyItem)
}

// Supprimer un quiz de l'historique
const handleDelete = (id) => {
  if (confirm('Voulez-vous vraiment supprimer ce QCM de l\'historique ?')) {
    deleteFromHistory(id)
    loadHistory() // Recharger la liste
  }
}

// Vider tout l'historique
const handleClearAll = () => {
  clearHistory()
  loadHistory()
  showClearConfirm.value = false
}

// Calculer les statistiques globales
const getGlobalStats = () => {
  if (history.value.length === 0) {
    return { totalQuizzes: 0, averageScore: 0, totalQuestions: 0 }
  }

  const totalQuizzes = history.value.length
  const totalQuestions = history.value.reduce((sum, item) => sum + item.questionsCount, 0)
  const totalScore = history.value.reduce((sum, item) => sum + item.score, 0)
  const averageScore = Math.round((totalScore / totalQuestions) * 100)

  return { totalQuizzes, averageScore, totalQuestions }
}

// ========================================
// LIFECYCLE
// ========================================
onMounted(() => {
  loadHistory()
})
</script>

<template>
  <div>
    <!-- Header de l'historique -->
    <div class="bg-white rounded-2xl shadow-xl p-8 mb-8">
      <div class="flex items-center justify-between mb-6">
        <div>
          <h2 class="text-3xl font-bold text-gray-800 mb-2">
            📚 Historique des QCM
          </h2>
          <p class="text-gray-600">
            Retrouvez vos derniers quiz et vos scores
          </p>
        </div>

        <!-- Bouton vider l'historique -->
        <button
          v-if="history.length > 0"
          @click="showClearConfirm = true"
          class="bg-red-100 hover:bg-red-200 text-red-600 px-4 py-2 rounded-lg transition-all font-medium"
        >
          🗑️ Tout supprimer
        </button>
      </div>

      <!-- Statistiques globales -->
      <div v-if="history.length > 0" class="grid grid-cols-1 md:grid-cols-3 gap-4 mb-6">
        <div class="bg-indigo-50 rounded-lg p-4 text-center">
          <div class="text-3xl font-bold text-indigo-600">{{ getGlobalStats().totalQuizzes }}</div>
          <div class="text-sm text-gray-600 mt-1">QCM réalisés</div>
        </div>
        <div class="bg-green-50 rounded-lg p-4 text-center">
          <div class="text-3xl font-bold text-green-600">{{ getGlobalStats().averageScore }}%</div>
          <div class="text-sm text-gray-600 mt-1">Score moyen</div>
        </div>
        <div class="bg-blue-50 rounded-lg p-4 text-center">
          <div class="text-3xl font-bold text-blue-600">{{ getGlobalStats().totalQuestions }}</div>
          <div class="text-sm text-gray-600 mt-1">Questions répondues</div>
        </div>
      </div>
    </div>

    <!-- Liste de l'historique -->
    <div v-if="history.length > 0" class="space-y-4">
      <HistoryItem
        v-for="item in history"
        :key="item.id"
        :historyItem="item"
        @replay="handleReplay"
        @delete="handleDelete"
      />
    </div>

    <!-- Message si aucun historique -->
    <div v-else class="bg-white rounded-2xl shadow-xl p-12 text-center">
      <div class="text-6xl mb-4">📭</div>
      <h3 class="text-2xl font-bold text-gray-800 mb-2">
        Aucun historique pour le moment
      </h3>
      <p class="text-gray-600">
        Commencez par créer un QCM pour voir votre historique ici !
      </p>
    </div>

    <!-- Modal de confirmation pour tout supprimer -->
    <div
      v-if="showClearConfirm"
      class="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center z-50 p-4"
      @click.self="showClearConfirm = false"
    >
      <div class="bg-white rounded-2xl p-8 max-w-md w-full">
        <h3 class="text-2xl font-bold text-gray-800 mb-4">
          ⚠️ Confirmation
        </h3>
        <p class="text-gray-600 mb-6">
          Êtes-vous sûr de vouloir supprimer tout l'historique ? Cette action est irréversible.
        </p>
        <div class="flex gap-4">
          <button
            @click="handleClearAll"
            class="flex-1 bg-red-600 hover:bg-red-700 text-white py-3 rounded-lg font-semibold transition-all"
          >
            Oui, tout supprimer
          </button>
          <button
            @click="showClearConfirm = false"
            class="flex-1 bg-gray-200 hover:bg-gray-300 text-gray-700 py-3 rounded-lg font-semibold transition-all"
          >
            Annuler
          </button>
        </div>
      </div>
    </div>
  </div>
</template>