<script setup>
import { ref } from 'vue'
import axios from 'axios'

const courseText = ref('')
const questions = ref([])
const userAnswers = ref([])
const showQuiz = ref(false)
const showResults = ref(false)
const score = ref(0)
const loading = ref(false)
const error = ref('')

const generateQcm = async () => {

  if (!courseText.value.trim()) {
    error.value = 'Veuillez entrer du texte'
    return
  }

  loading.value = true
  error.value = ''

  try {
    const result = await axios.post(`${import.meta.env.VITE_API_URL}/api/qcm/generate`, {
      courseText: courseText.value
    })

    questions.value = result.data.questions
    userAnswers.value = new Array(questions.value.length).fill(null)
    showQuiz.value = true
    showResults.value = false

  } catch (err) {
    error.value = 'Erreur : Vérifiez que le backend est lancé'
    console.error(err)
  } finally {
    loading.value = false
  }
}

const selectAnswer = (questionIndex, optionIndex) => {
  userAnswers.value[questionIndex] = optionIndex
}

const submitQuiz = () => {
  // Vérifier que toutes les questions ont une réponse
  if (userAnswers.value.includes(null)) {
    alert('Veuillez répondre à toutes les questions')
    return
  }

  // Calculer le score
  let correctCount = 0
  questions.value.forEach((question, index) => {
    if (userAnswers.value[index] === question.correctAnswer) {
      correctCount++
    }
  })

  score.value = correctCount
  showResults.value = true
}

const restart = () => {
  courseText.value = ''
  questions.value = []
  userAnswers.value = []
  showQuiz.value = false
  showResults.value = false
  score.value = 0
  error.value = ''
}

const loadExample = () => {
  courseText.value = `La photosynthèse est un processus biologique qui permet aux plantes de convertir l'énergie lumineuse en énergie chimique. Ce processus se déroule dans les chloroplastes, des organites présents dans les cellules végétales. La chlorophylle, un pigment vert, capte la lumière solaire. Les plantes utilisent ensuite cette énergie pour transformer le dioxyde de carbone et l'eau en glucose et en oxygène. Ce processus est essentiel pour la vie sur Terre car il produit l'oxygène que nous respirons.`
}

const getEmoji = () => {
  const percentage = (score.value / questions.value.length) * 100

  if (percentage === 100) return '🏆'
  if (percentage >= 80) return '🎉'
  if (percentage >= 60) return '😊'
  if (percentage >= 40) return '💪'
  if (percentage > 0) return '📚'
  return '😅'
}

const getMessage = () => {
  const percentage = (score.value / questions.value.length) * 100

  if (percentage === 100) return 'Parfait ! Score impeccable !'
  if (percentage >= 80) return 'Excellent travail !'
  if (percentage >= 60) return 'Très bien ! Continue comme ça !'
  if (percentage >= 40) return 'Pas mal, mais tu peux faire mieux !'
  if (percentage > 0) return 'Il faut réviser un peu plus...'
  return 'Aïe ! Reprends tes cours et réessaye !'
}

</script>

<template>
  <div class="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100 py-12 px-4">
    <div class="max-w-4xl mx-auto">

      <!-- Header -->
      <header class="text-center mb-12">
        <h1 class="text-5xl font-bold text-indigo-900 mb-2">
          📚 QCM Generator
        </h1>
        <p class="text-gray-600 text-lg">
          Transformez vos cours en quiz interactifs
        </p>
      </header>

      <!-- Étape 1 : Saisie du cours -->
      <div v-if="!showQuiz && !showResults" class="bg-white rounded-2xl shadow-xl p-8">
        <h2 class="text-2xl font-bold text-gray-800 mb-6">
          Étape 1 : Collez votre cours
        </h2>

        <textarea v-model="courseText" placeholder="Collez ici le texte de votre cours..."
          class="w-full h-64 p-4 border-2 border-gray-300 rounded-lg focus:border-indigo-500 focus:ring-2 focus:ring-indigo-200 transition-all resize-none"></textarea>

        <div class="flex gap-4 mt-6">
          <button @click="generateQcm" :disabled="loading"
            class="flex-1 bg-indigo-600 hover:bg-indigo-700 text-white font-semibold py-3 px-6 rounded-lg transition-all disabled:bg-gray-400 disabled:cursor-not-allowed">
            <span v-if="!loading">✨ Générer le QCM</span>
            <span v-else>⏳ Génération en cours...</span>
          </button>

          <button @click="loadExample"
            class="bg-gray-200 hover:bg-gray-300 text-gray-700 font-semibold py-3 px-6 rounded-lg transition-all">
            📝 Exemple
          </button>
        </div>

        <div v-if="error" class="mt-4 p-4 bg-red-50 border border-red-200 rounded-lg text-red-700">
          {{ error }}
        </div>

        <div class="mt-6 text-sm text-gray-500">
          💡 Conseil : Plus votre texte est long et structuré, meilleurs seront les QCM générés !
        </div>
      </div>

      <!-- Étape 2 : Quiz -->
      <div v-if="showQuiz && !showResults" class="bg-white rounded-2xl shadow-xl p-8">
        <h2 class="text-2xl font-bold text-gray-800 mb-2">
          Étape 2 : Répondez aux questions
        </h2>
        <p class="text-gray-600 mb-8">
          {{ questions.length }} question{{ questions.length > 1 ? 's' : '' }} générée{{ questions.length > 1 ? 's' : ''
          }}
        </p>

        <div class="space-y-8">
          <div v-for="(question, qIndex) in questions" :key="question.id"
            class="border-2 border-gray-200 rounded-xl p-6 hover:border-indigo-300 transition-all">
            <h3 class="font-semibold text-lg text-gray-800 mb-4">
              Question {{ qIndex + 1 }}
            </h3>
            <p class="text-gray-700 mb-4">{{ question.question }}</p>

            <div class="space-y-3">
              <div v-for="(option, oIndex) in question.options" :key="oIndex" @click="selectAnswer(qIndex, oIndex)"
                :class="[
                  'p-4 rounded-lg border-2 cursor-pointer transition-all',
                  userAnswers[qIndex] === oIndex
                    ? 'border-indigo-500 bg-indigo-50'
                    : 'border-gray-200 hover:border-indigo-300 bg-white'
                ]">
                <label class="flex items-center cursor-pointer">
                  <input type="radio" :name="`question-${qIndex}`" :value="oIndex"
                    :checked="userAnswers[qIndex] === oIndex" class="mr-3 w-5 h-5 text-indigo-600" />
                  <span class="text-gray-700">{{ option }}</span>
                </label>
              </div>
            </div>
          </div>
        </div>

        <div class="mt-8 flex justify-center">
          <button @click="submitQuiz"
            class="bg-green-600 hover:bg-green-700 text-white font-semibold py-4 px-12 rounded-lg transition-all text-lg">
            ✅ Valider mes réponses
          </button>
        </div>
      </div>

      <!-- Étape 3 : Résultats -->
      <div v-if="showResults">
        <!-- Score Card -->
        <div class="bg-white rounded-2xl shadow-xl p-8 mb-8 text-center">
          <div class="text-6xl mb-4">
            {{ getEmoji() }}
          </div>
          <h2 class="text-3xl font-bold text-gray-800 mb-2">
            {{ getMessage() }}
          </h2>
          <div class="text-6xl font-bold text-indigo-600 my-6">
            {{ score }} / {{ questions.length }}
          </div>
          <div class="text-2xl text-gray-600 mb-6">
            Score : {{ Math.round((score / questions.length) * 100) }}%
          </div>

          <button @click="restart"
            class="bg-indigo-600 hover:bg-indigo-700 text-white font-semibold py-3 px-8 rounded-lg transition-all">
            🔄 Nouveau QCM
          </button>
        </div>

        <!-- Corrections -->
        <div class="bg-white rounded-2xl shadow-xl p-8">
          <h3 class="text-2xl font-bold text-gray-800 mb-6">📋 Corrections détaillées</h3>

          <div class="space-y-6">
            <div v-for="(question, index) in questions" :key="question.id" class="border-2 rounded-xl p-6"
              :class="userAnswers[index] === question.correctAnswer ? 'border-green-300 bg-green-50' : 'border-red-300 bg-red-50'">
              <div class="flex items-start gap-3">
                <div class="text-2xl">
                  {{ userAnswers[index] === question.correctAnswer ? '✅' : '❌' }}
                </div>
                <div class="flex-1">
                  <h4 class="font-semibold text-gray-800 mb-2">Question {{ index + 1 }}</h4>
                  <p class="text-gray-700 mb-4">{{ question.question }}</p>

                  <div class="space-y-2">
                    <div v-for="(option, oIndex) in question.options" :key="oIndex" class="p-3 rounded-lg" :class="{
                      'bg-green-100 border-2 border-green-400 font-semibold': oIndex === question.correctAnswer,
                      'bg-red-100 border-2 border-red-400': oIndex === userAnswers[index] && oIndex !== question.correctAnswer,
                      'bg-gray-50': oIndex !== question.correctAnswer && oIndex !== userAnswers[index]
                    }">
                      <div class="flex items-center justify-between">
                        <span>{{ option }}</span>
                        <span v-if="oIndex === question.correctAnswer" class="text-green-600 font-semibold">
                          ✓ Bonne réponse
                        </span>
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
      <!-- Footer -->
      <footer class="text-center mt-16 text-gray-600">
        <div class="bg-white rounded-xl shadow-md p-6 max-w-md mx-auto">
          <p class="text-sm mb-2">Créé avec Vue.js 3 + Spring Boot 🚀</p>
          <p class="text-lg font-semibold text-indigo-600">
            Auteur :
            <a href="mailto:baldemarc225@gmail.com" class="hover:underline hover:text-indigo-800 transition">
              Oussou 👨‍💻
            </a>
          </p>
        </div>
      </footer>
    </div>
  </div>
</template>