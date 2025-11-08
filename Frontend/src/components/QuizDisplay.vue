<template>
    <!-- Conteneur principal du quiz -->
    <div class="bg-white rounded-2xl shadow-xl p-8">
      <h2 class="text-2xl font-bold text-gray-800 mb-2">
        Étape 2 : Répondez aux questions
      </h2>
      <p class="text-gray-600 mb-8">
        {{ questions.length }} question{{ questions.length > 1 ? 's' : '' }} générée{{ questions.length > 1 ? 's' : '' }}
      </p>
  
      <!-- Liste des questions -->
      <div class="space-y-8">
        <div
          v-for="(question, qIndex) in questions"
          :key="question.id"
          class="border-2 border-gray-200 rounded-xl p-6 hover:border-indigo-300 transition-all"
        >
          <!-- Numéro de la question -->
          <h3 class="font-semibold text-lg text-gray-800 mb-4">
            Question {{ qIndex + 1 }}
          </h3>
          
          <!-- Texte de la question -->
          <p class="text-gray-700 mb-4">{{ question.question }}</p>
  
          <!-- Options de réponse -->
          <div class="space-y-3">
            <div
              v-for="(option, oIndex) in question.options"
              :key="oIndex"
              @click="selectAnswer(qIndex, oIndex)"
              :class="[
                'p-4 rounded-lg border-2 cursor-pointer transition-all',
                selectedAnswers[qIndex] === oIndex
                  ? 'border-indigo-500 bg-indigo-50'
                  : 'border-gray-200 hover:border-indigo-300 bg-white'
              ]"
            >
              <label class="flex items-center cursor-pointer">
                <input
                  type="radio"
                  :name="`question-${qIndex}`"
                  :value="oIndex"
                  :checked="selectedAnswers[qIndex] === oIndex"
                  class="mr-3 w-5 h-5 text-indigo-600"
                />
                <span class="text-gray-700">{{ option }}</span>
              </label>
            </div>
          </div>
        </div>
      </div>
  
      <!-- Bouton de validation -->
      <div class="mt-8 flex justify-center">
        <button
          @click="submitQuiz"
          :disabled="!allAnswered"
          class="bg-green-600 hover:bg-green-700 text-white font-semibold py-4 px-12 rounded-lg transition-all disabled:bg-gray-400 disabled:cursor-not-allowed text-lg"
        >
          ✅ Valider mes réponses
        </button>
      </div>
  
      <!-- Message si toutes les questions ne sont pas répondues -->
      <div v-if="!allAnswered" class="mt-4 text-center text-sm text-gray-500">
        Répondez à toutes les questions pour continuer
      </div>
    </div>
</template>

<script setup>
import { ref, computed } from 'vue'

const props = defineProps({
    questions: {
        type: Array,
        required: true
    }
})

// ========================================
// EMITS (événements envoyés au parent)
// ========================================
const emit = defineEmits(['submit'])

// ========================================
// STATE LOCAL
// ========================================
// Tableau pour stocker les réponses sélectionnées par l'utilisateur
// Index = numéro de la question, Valeur = index de l'option choisie
const selectedAnswers = ref(new Array(props.questions.length).fill(null))

// ========================================
// FONCTIONS
// ========================================

// Sélectionner une réponse pour une question
const selectAnswer = (questionIndex, optionIndex) => {
  selectedAnswers.value[questionIndex] = optionIndex
}

// Vérifier si toutes les questions ont une réponse
const allAnswered = computed(() => {
  return !selectedAnswers.value.includes(null)
})

// Soumettre le quiz au parent
const submitQuiz = () => {
  if (!allAnswered.value) {
    alert('Veuillez répondre à toutes les questions')
    return
  }
  
  emit('submit', selectedAnswers.value)
}
</script>

<style lang="scss" scoped>

</style>