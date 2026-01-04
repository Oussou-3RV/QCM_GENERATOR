<template>
    <div class="py-12">
      <div class="max-w-5xl mx-auto px-4">
        
        <!-- Navigation avec tabs pour Générateur / Historique / Avis -->
        <div class="bg-white/90 backdrop-blur-xl rounded-2xl shadow-xl border border-gray-100 p-2 mb-8">
          <div class="flex gap-2">
            <button
              @click="activeTab = 'generator'"
              :class="[
                'flex-1 py-3 px-4 rounded-lg font-semibold transition-all text-sm md:text-base',
                activeTab === 'generator'
                  ? 'bg-gradient-to-r from-indigo-600 to-purple-600 text-white shadow-md'
                  : 'text-gray-700 hover:bg-gray-50'
              ]"
            >
              <span class="flex items-center justify-center gap-2">
                <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 4v16m8-8H4"></path>
                </svg>
                Créer
              </span>
            </button>
            <button
              @click="activeTab = 'history'"
              :class="[
                'flex-1 py-3 px-4 rounded-lg font-semibold transition-all text-sm md:text-base',
                activeTab === 'history'
                  ? 'bg-gradient-to-r from-indigo-600 to-purple-600 text-white shadow-md'
                  : 'text-gray-700 hover:bg-gray-50'
              ]"
            >
              <span class="flex items-center justify-center gap-2">
                <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M12 8v4l3 3m6-3a9 9 0 11-18 0 9 9 0 0118 0z"></path>
                </svg>
                Historique
              </span>
            </button>
            <button
              @click="activeTab = 'reviews'"
              :class="[
                'flex-1 py-3 px-4 rounded-lg font-semibold transition-all text-sm md:text-base',
                activeTab === 'reviews'
                  ? 'bg-gradient-to-r from-indigo-600 to-purple-600 text-white shadow-md'
                  : 'text-gray-700 hover:bg-gray-50'
              ]"
            >
              <span class="flex items-center justify-center gap-2">
                <svg class="w-5 h-5" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M11.049 2.927c.3-.921 1.603-.921 1.902 0l1.519 4.674a1 1 0 00.95.69h4.915c.969 0 1.371 1.24.588 1.81l-3.976 2.888a1 1 0 00-.363 1.118l1.518 4.674c.3.922-.755 1.688-1.538 1.118l-3.976-2.888a1 1 0 00-1.176 0l-3.976 2.888c-.783.57-1.838-.197-1.538-1.118l1.518-4.674a1 1 0 00-.363-1.118l-3.976-2.888c-.784-.57-.38-1.81.588-1.81h4.914a1 1 0 00.951-.69l1.519-4.674z"></path>
                </svg>
                Avis
              </span>
            </button>
          </div>
        </div>
  
        <!-- Contenu selon l'onglet -->
        <div v-if="activeTab === 'generator'">
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
  
        <HistoryPage
          v-if="activeTab === 'history'"
          @replay-quiz="handleReplayQuiz"
        />
  
        <ReviewsPage v-if="activeTab === 'reviews'" />
  
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue'
  import axios from 'axios'
  import CourseInput from './CourseInput.vue'
  import QuizDisplay from './QuizDisplay.vue'
  import ScoreDisplay from './ScoreDisplay.vue'
  import HistoryPage from './HistoryPage.vue'
  import ReviewsPage from './ReviewsPage.vue'
  import { saveToHistory } from '../services/historyService'
  
  // State
  const activeTab = ref('generator')
  const currentStep = ref('input')
  const questions = ref([])
  const userAnswers = ref([])
  const score = ref(0)
  const loading = ref(false)
  const error = ref('')
  
  // Génération depuis texte
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
  
  // Génération depuis PDF
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
  
  // Soumettre le quiz
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
  
  // Refaire un quiz
  const handleReplayQuiz = (historyItem) => {
    questions.value = historyItem.questions
    userAnswers.value = new Array(historyItem.questions.length).fill(null)
    activeTab.value = 'generator'
    currentStep.value = 'quiz'
  }
  
  // Recommencer
  const handleRestart = () => {
    currentStep.value = 'input'
    questions.value = []
    userAnswers.value = []
    score.value = 0
    error.value = ''
  }
  </script>