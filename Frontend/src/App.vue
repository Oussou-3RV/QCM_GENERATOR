<script setup>
import { ref } from 'vue'
import axios from 'axios'

// ========================================
// IMPORT DES COMPOSANTS
// ========================================
import AppHeader from './components/AppHeader.vue'
import AppFooter from './components/AppFooter.vue'
import CourseInput from './components/CourseInput.vue'
import QuizDisplay from './components/QuizDisplay.vue'
import ScoreDisplay from './components/ScoreDisplay.vue'
import HistoryPage from './components/HistoryPage.vue'
import ReviewsPage from './components/ReviewsPage.vue'
import Button from './components/Button.vue'

// ========================================
// IMPORT DU SERVICE HISTORIQUE
// ========================================
import { saveToHistory } from './services/historyService'

// ========================================
// STATE GLOBAL DE L'APPLICATION
// ========================================

// Page actuelle : 'home', 'history', ou 'reviews'
const currentPage = ref('home')

// Étape actuelle sur la page home : 'input', 'quiz', ou 'score'
const currentStep = ref('input')

// Questions générées par l'IA
const questions = ref([])

// Réponses de l'utilisateur
const userAnswers = ref([])

// Score final
const score = ref(0)

// État de chargement (pendant l'appel API)
const loading = ref(false)

// Message d'erreur
const error = ref('')

// ========================================
// FONCTIONS - Navigation
// ========================================

/**
 * Naviguer vers une page
 * @param {string} page - 'home', 'history', ou 'reviews'
 */
const navigateTo = (page) => {
  currentPage.value = page
  
  // Si on retourne à home, réinitialiser à l'étape input
  if (page === 'home' && currentStep.value === 'score') {
    handleRestart()
  }
}

// ========================================
// FONCTIONS - Génération de QCM
// ========================================

/**
 * Générer un QCM depuis du texte
 */
const handleGenerateFromText = async (courseText) => {
  loading.value = true
  error.value = ''

  try {
    const result = await axios.post(`${import.meta.env.VITE_API_URL}/api/qcm/generate`, {
      courseText: courseText
    })

    questions.value = result.data.questions
    userAnswers.value = new Array(questions.value.length).fill(null)
    currentStep.value = 'quiz'

  } catch (err) {
    if (err.response && err.response.status === 503) {
      error.value = '🤖 ' + (err.response.data.message || 
        "L'intelligence artificielle est temporairement indisponible. Veuillez réessayer dans quelques instants.")
    } else if (err.response && err.response.data && err.response.data.message) {
      error.value = '❌ ' + err.response.data.message
    } else {
      error.value = '⚠️ Impossible de contacter le serveur. Vérifiez votre connexion internet.'
    }
    console.error(err)
  } finally {
    loading.value = false
  }
}

/**
 * Générer un QCM depuis un PDF
 */
const handleGenerateFromPdf = async (pdfFile) => {
  loading.value = true
  error.value = ''

  try {
    const formData = new FormData()
    formData.append('file', pdfFile)

    const result = await axios.post(
      `${import.meta.env.VITE_API_URL}/api/qcm/generate-from-pdf`,
      formData,
      {
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }
    )

    questions.value = result.data.questions
    userAnswers.value = new Array(questions.value.length).fill(null)
    currentStep.value = 'quiz'

  } catch (err) {
    if (err.response && err.response.status === 503) {
      error.value = '🤖 ' + (err.response.data.message || 
        "L'intelligence artificielle est temporairement indisponible. Veuillez réessayer dans quelques instants.")
    } else if (err.response && err.response.status === 400) {
      error.value = '📄 ' + (err.response.data.message || 
        'Erreur lors de la lecture du PDF. Vérifiez que le fichier contient du texte.')
    } else if (err.response && err.response.data && err.response.data.message) {
      error.value = '❌ ' + err.response.data.message
    } else {
      error.value = '⚠️ Impossible de contacter le serveur. Vérifiez votre connexion internet.'
    }
    console.error(err)
  } finally {
    loading.value = false
  }
}

// ========================================
// FONCTIONS - Quiz
// ========================================

/**
 * Soumettre les réponses du quiz et calculer le score
 */
const handleSubmitQuiz = (answers) => {
  userAnswers.value = answers
  
  let correctCount = 0
  questions.value.forEach((question, index) => {
    if (answers[index] === question.correctAnswer) {
      correctCount++
    }
  })
  
  score.value = correctCount
  currentStep.value = 'score'
  
  // Sauvegarder dans l'historique
  saveToHistory({
    questions: questions.value,
    userAnswers: answers,
    score: correctCount
  })
}

// ========================================
// FONCTIONS - Historique
// ========================================

/**
 * Refaire un quiz depuis l'historique
 */
const handleReplayQuiz = (historyItem) => {
  questions.value = historyItem.questions
  userAnswers.value = new Array(historyItem.questions.length).fill(null)
  currentPage.value = 'home'
  currentStep.value = 'quiz'
}

// ========================================
// FONCTIONS - Réinitialisation
// ========================================

/**
 * Recommencer
 */
const handleRestart = () => {
  currentStep.value = 'input'
  questions.value = []
  userAnswers.value = []
  score.value = 0
  error.value = ''
}
</script>

<template>
  <div class="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100 py-12 px-4">
    <div class="max-w-4xl mx-auto">
      
      <!-- HEADER -->
      <AppHeader />

      <!-- MENU DE NAVIGATION -->
      <div class="flex gap-2 mb-8 bg-white rounded-xl shadow-md p-2">
       
        <Button 
          label="Accueil"
          icon="🏠"
          @click="navigateTo('home')"
          :active = "currentPage === 'home'"
        />

        <Button 
          label="Historique"
          icon="📚"
          @click="navigateTo('history')"
          :active = "currentPage === 'history'"
        />
       
        <Button 
          label="Avis"
          icon="⭐"
          @click="navigateTo('reviews')"
          :active = "currentPage === 'reviews' "
        />
  
      </div>

      <!-- PAGE HOME -->
      <div v-if="currentPage === 'home'">
        <CourseInput
          v-if="currentStep === 'input'"
          :loading="loading"
          :error="error"
          @generate-from-text="handleGenerateFromText"
          @generate-from-pdf="handleGenerateFromPdf"
        />

        <QuizDisplay
          v-if="currentStep === 'quiz'"
          :questions="questions"
          @submit="handleSubmitQuiz"
        />

        <ScoreDisplay
          v-if="currentStep === 'score'"
          :score="score"
          :total="questions.length"
          :questions="questions"
          :userAnswers="userAnswers"
          @restart="handleRestart"
        />
      </div>

      <!-- PAGE HISTORIQUE -->
      <HistoryPage
        v-if="currentPage === 'history'"
        @replay-quiz="handleReplayQuiz"
      />

      <!-- PAGE AVIS -->
      <ReviewsPage v-if="currentPage === 'reviews'" />

    </div>

    <!-- FOOTER -->
    <AppFooter />
  </div>
</template>

