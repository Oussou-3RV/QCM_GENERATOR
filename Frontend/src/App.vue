
<template>
  <!-- Conteneur principal avec fond dégradé -->
  <div class="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100 py-12 px-4">
    <div class="max-w-4xl mx-auto">
      
      <!-- ========================================
           HEADER
           ======================================== -->
      <AppHeader />

      <!-- ========================================
           ÉTAPE 1 : SAISIE DU COURS
           ======================================== -->
      <CourseInput
        v-if="currentStep === 'input'"
        :loading="loading"
        :error="error"
        @generate-from-text="handleGenerateFromText"
        @generate-from-pdf="handleGenerateFromPdf"
      />

      <!-- ========================================
           ÉTAPE 2 : QUIZ
           ======================================== -->
      <QuizDisplay
        v-if="currentStep === 'quiz'"
        :questions="questions"
        @submit="handleSubmitQuiz"
      />

      <!-- ========================================
           ÉTAPE 3 : RÉSULTATS
           ======================================== -->
      <ScoreDisplay
        v-if="currentStep === 'score'"
        :score="score"
        :total="questions.length"
        :questions="questions"
        :userAnswers="userAnswers"
        @restart="handleRestart"
      />

    </div>

    <!-- ========================================
         FOOTER
         ======================================== -->
    <AppFooter />
  </div>
</template>


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

// ========================================
// STATE GLOBAL DE L'APPLICATION
// ========================================

// Étape actuelle : 'input', 'quiz', ou 'score'
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
// FONCTIONS - Génération de QCM
// ========================================

/**
 * Générer un QCM depuis du texte
 * @param {string} courseText - Le texte du cours
 */
const handleGenerateFromText = async (courseText) => {
  loading.value = true
  error.value = ''

  try {
    const result = await axios.post(`${import.meta.env.VITE_API_URL}/api/qcm/generate`, {
      courseText: courseText
    })

    // Stocker les questions et passer à l'étape quiz
    questions.value = result.data.questions
    userAnswers.value = new Array(questions.value.length).fill(null)
    currentStep.value = 'quiz'

  } catch (err) {
    // Gestion d'erreur
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
 * @param {File} pdfFile - Le fichier PDF
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

    // Stocker les questions et passer à l'étape quiz
    questions.value = result.data.questions
    userAnswers.value = new Array(questions.value.length).fill(null)
    currentStep.value = 'quiz'

  } catch (err) {
    // Gestion d'erreur
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
 * @param {Array} answers - Tableau des réponses sélectionnées
 */
const handleSubmitQuiz = (answers) => {
  userAnswers.value = answers
  
  // Calculer le score
  let correctCount = 0
  questions.value.forEach((question, index) => {
    if (answers[index] === question.correctAnswer) {
      correctCount++
    }
  })
  
  score.value = correctCount
  currentStep.value = 'score'
}

// ========================================
// FONCTIONS - Navigation
// ========================================

/**
 * Recommencer : retour à l'étape de saisie
 */
const handleRestart = () => {
  currentStep.value = 'input'
  questions.value = []
  userAnswers.value = []
  score.value = 0
  error.value = ''
}
</script>


<style>
/* Import de Tailwind CSS */
@import 'tailwindcss/base';
@import 'tailwindcss/components';
@import 'tailwindcss/utilities';
</style>
