<script setup>
import { ref } from 'vue'
import axios from 'axios'

// ========================================
// VARIABLES D'ÉTAT (State Management)
// ========================================

// Onglet actif : 'text' pour saisie manuelle, 'pdf' pour upload
const activeTab = ref('text')

// Contenu du cours saisi manuellement par l'utilisateur
const courseText = ref('')

// Fichier PDF sélectionné par l'utilisateur
const pdfFile = ref(null)

// Nom du fichier PDF pour affichage
const pdfFileName = ref('')

// Liste des questions générées par le backend
const questions = ref([])

// Réponses sélectionnées par l'utilisateur (array d'index)
const userAnswers = ref([])

// Afficher l'écran de quiz (étape 2)
const showQuiz = ref(false)

// Afficher l'écran de résultats (étape 3)
const showResults = ref(false)

// Score final de l'utilisateur
const score = ref(0)

// État de chargement (pendant l'appel API)
const loading = ref(false)

// Message d'erreur à afficher
const error = ref('')

// ========================================
// FONCTION : Générer QCM depuis du texte
// ========================================
const generateQcmFromText = async () => {
  // Vérifier que l'utilisateur a saisi du texte
  if (!courseText.value.trim()) {
    error.value = 'Veuillez entrer du texte'
    return
  }

  // Activer le loader et réinitialiser les erreurs
  loading.value = true
  error.value = ''

  try {
    // Appeler l'API backend pour générer les QCM
    const result = await axios.post(`${import.meta.env.VITE_API_URL}/api/qcm/generate`, {
      courseText: courseText.value
    })

    // Stocker les questions reçues
    questions.value = result.data.questions
    
    // Initialiser un tableau de réponses vide (null = pas encore répondu)
    userAnswers.value = new Array(questions.value.length).fill(null)
    
    // Passer à l'écran de quiz
    showQuiz.value = true
    showResults.value = false

  } catch (err) {
    // En cas d'erreur, afficher un message
    error.value = 'Erreur : Vérifiez que le backend est lancé'
    console.error(err)
  } finally {
    // Désactiver le loader dans tous les cas
    loading.value = false
  }
}

// ========================================
// FONCTION : Générer QCM depuis un PDF
// ========================================
const generateQcmFromPdf = async () => {
  // Vérifier qu'un fichier a été sélectionné
  if (!pdfFile.value) {
    error.value = 'Veuillez sélectionner un fichier PDF'
    return
  }

  // Activer le loader et réinitialiser les erreurs
  loading.value = true
  error.value = ''

  try {
    // Créer un objet FormData pour envoyer le fichier
    const formData = new FormData()
    formData.append('file', pdfFile.value)

    // Appeler l'API backend pour générer les QCM depuis le PDF
    const result = await axios.post(
      `${import.meta.env.VITE_API_URL}/api/qcm/generate-from-pdf`,
      formData,
      {
        headers: {
          'Content-Type': 'multipart/form-data' // Important pour l'upload de fichiers
        }
      }
    )

    // Stocker les questions reçues
    questions.value = result.data.questions
    
    // Initialiser un tableau de réponses vide
    userAnswers.value = new Array(questions.value.length).fill(null)
    
    // Passer à l'écran de quiz
    showQuiz.value = true
    showResults.value = false

  } catch (err) {
    // En cas d'erreur (PDF illisible, etc.)
    error.value = 'Erreur lors de la lecture du PDF. Vérifiez que le fichier contient du texte.'
    console.error(err)
  } finally {
    // Désactiver le loader
    loading.value = false
  }
}

// ========================================
// FONCTION : Gérer la sélection de fichier
// ========================================
const handleFileChange = (event) => {
  // Récupérer le premier fichier sélectionné
  const file = event.target.files[0]
  
  if (file) {
    // Vérifier que c'est bien un PDF
    if (file.type !== 'application/pdf') {
      error.value = 'Veuillez sélectionner un fichier PDF'
      pdfFile.value = null
      pdfFileName.value = ''
      return
    }
    
    // Vérifier la taille (max 10MB)
    if (file.size > 10 * 1024 * 1024) {
      error.value = 'Le fichier est trop volumineux (max 10MB)'
      pdfFile.value = null
      pdfFileName.value = ''
      return
    }
    
    // Tout est OK, stocker le fichier
    pdfFile.value = file
    pdfFileName.value = file.name
    error.value = ''
  }
}

// ========================================
// FONCTION : Sélectionner une réponse
// ========================================
const selectAnswer = (questionIndex, optionIndex) => {
  // Enregistrer la réponse de l'utilisateur
  // questionIndex = numéro de la question (0, 1, 2...)
  // optionIndex = index de l'option choisie (0, 1, 2, 3)
  userAnswers.value[questionIndex] = optionIndex
}

// ========================================
// FONCTION : Valider le quiz
// ========================================
const submitQuiz = () => {
  // Vérifier que toutes les questions ont une réponse
  if (userAnswers.value.includes(null)) {
    alert('Veuillez répondre à toutes les questions')
    return
  }

  // Calculer le score
  let correctCount = 0
  questions.value.forEach((question, index) => {
    // Comparer la réponse de l'utilisateur avec la bonne réponse
    if (userAnswers.value[index] === question.correctAnswer) {
      correctCount++
    }
  })

  // Stocker le score et afficher les résultats
  score.value = correctCount
  showResults.value = true
}

// ========================================
// FONCTION : Recommencer (nouveau QCM)
// ========================================
const restart = () => {
  // Réinitialiser toutes les variables à leur état initial
  courseText.value = ''
  pdfFile.value = null
  pdfFileName.value = ''
  questions.value = []
  userAnswers.value = []
  showQuiz.value = false
  showResults.value = false
  score.value = 0
  error.value = ''
  activeTab.value = 'text'
}

// ========================================
// FONCTION : Charger un exemple de texte
// ========================================
const loadExample = () => {
  // Remplir le champ texte avec un exemple
  courseText.value = `Le Canada est un pays d’Amérique du Nord, le deuxième plus grand au monde en superficie, situé entre les États-Unis au sud et l’océan Arctique au nord. Il possède dix provinces et trois territoires, chacun ayant ses propres particularités culturelles et économiques. Le pays est reconnu pour sa diversité géographique : on y trouve des montagnes, des forêts, des lacs et des plaines. La population canadienne est multiculturelle et bilingue, avec l’anglais et le français comme langues officielles. Le Canada est également connu pour son système de santé universel, sa stabilité politique et son engagement envers l’environnement. Son économie repose sur des secteurs variés comme les ressources naturelles, la technologie et les services.`
  
  // Basculer sur l'onglet texte
  activeTab.value = 'text'
}

// ========================================
// FONCTION : Obtenir l'emoji selon le score
// ========================================
const getEmoji = () => {
  // Calculer le pourcentage de réussite
  const percentage = (score.value / questions.value.length) * 100
  
  // Retourner l'emoji approprié
  if (percentage === 100) return '🏆'  // Parfait
  if (percentage >= 80) return '🎉'    // Excellent
  if (percentage >= 60) return '😊'    // Bien
  if (percentage >= 40) return '💪'    // Moyen
  if (percentage > 0) return '📚'      // Faible
  return '😅'                           // Aucune bonne réponse
}

// ========================================
// FONCTION : Obtenir le message selon le score
// ========================================
const getMessage = () => {
  // Calculer le pourcentage de réussite
  const percentage = (score.value / questions.value.length) * 100
  
  // Retourner le message approprié
  if (percentage === 100) return 'Parfait ! Score impeccable !'
  if (percentage >= 80) return 'Excellent travail !'
  if (percentage >= 60) return 'Très bien ! Continue comme ça !'
  if (percentage >= 40) return 'Pas mal, mais tu peux faire mieux !'
  if (percentage > 0) return 'Il faut réviser un peu plus...'
  return 'Aïe ! Reprends tes cours et réessaye !'
}
</script>

<template>
  <!-- ========================================
       CONTENEUR PRINCIPAL
       ======================================== -->
  <div class="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100 py-12 px-4">
    <div class="max-w-4xl mx-auto">
      
      <!-- ========================================
           HEADER - Titre de l'application
           ======================================== -->
      <header class="text-center mb-12">
        <h1 class="text-5xl font-bold text-indigo-900 mb-2">
          📚 QCM Generator
        </h1>
        <p class="text-gray-600 text-lg">
          Transformez vos cours en quiz interactifs
        </p>
      </header>

      <!-- ========================================
           ÉTAPE 1 : SAISIE DU COURS
           Affichée uniquement si on n'est pas dans le quiz ou les résultats
           ======================================== -->
      <div v-if="!showQuiz && !showResults" class="bg-white rounded-2xl shadow-xl p-8">
        <h2 class="text-2xl font-bold text-gray-800 mb-6">
          Étape 1 : Importez votre cours
        </h2>

        <!-- ========================================
             SYSTÈME D'ONGLETS (Texte / PDF)
             ======================================== -->
        <div class="flex gap-4 mb-6 border-b-2 border-gray-200">
          <!-- Onglet Texte -->
          <button
            @click="activeTab = 'text'"
            :class="[
              'pb-3 px-4 font-semibold transition-all',
              activeTab === 'text'
                ? 'text-indigo-600 border-b-4 border-indigo-600'  // Style si actif
                : 'text-gray-500 hover:text-gray-700'              // Style si inactif
            ]"
          >
            📝 Texte
          </button>
          
          <!-- Onglet PDF -->
          <button
            @click="activeTab = 'pdf'"
            :class="[
              'pb-3 px-4 font-semibold transition-all',
              activeTab === 'pdf'
                ? 'text-indigo-600 border-b-4 border-indigo-600'  // Style si actif
                : 'text-gray-500 hover:text-gray-700'              // Style si inactif
            ]"
          >
            📄 PDF
          </button>
        </div>

        <!-- ========================================
             CONTENU DE L'ONGLET TEXTE
             v-show = affiche/cache sans détruire le DOM
             ======================================== -->
        <div v-show="activeTab === 'text'">
          <!-- Zone de texte pour coller le cours -->
          <textarea
            v-model="courseText"
            placeholder="Collez ici le texte de votre cours..."
            class="w-full h-64 p-4 border-2 border-gray-300 rounded-lg focus:border-indigo-500 focus:ring-2 focus:ring-indigo-200 transition-all resize-none"
          ></textarea>

          <!-- Boutons d'action -->
          <div class="flex gap-4 mt-6">
            <!-- Bouton Générer -->
            <button
              @click="generateQcmFromText"
              :disabled="loading"
              class="flex-1 bg-indigo-600 hover:bg-indigo-700 text-white font-semibold py-3 px-6 rounded-lg transition-all disabled:bg-gray-400 disabled:cursor-not-allowed"
            >
              <!-- Affichage conditionnel selon l'état de chargement -->
              <span v-if="!loading">✨ Générer le QCM</span>
              <span v-else>⏳ Génération en cours...</span>
            </button>

            <!-- Bouton Exemple -->
            <button
              @click="loadExample"
              class="bg-gray-200 hover:bg-gray-300 text-gray-700 font-semibold py-3 px-6 rounded-lg transition-all"
            >
              📝 Exemple
            </button>
          </div>
        </div>

        <!-- ========================================
             CONTENU DE L'ONGLET PDF
             ======================================== -->
        <div v-show="activeTab === 'pdf'">
          <!-- Zone de drop pour le fichier PDF -->
          <div class="border-2 border-dashed border-gray-300 rounded-lg p-8 text-center hover:border-indigo-400 transition-all">
            <!-- Input file caché (stylé avec le label) -->
            <input
              type="file"
              id="pdf-input"
              accept=".pdf"
              @change="handleFileChange"
              class="hidden"
            />
            
            <!-- Label cliquable pour déclencher l'input file -->
            <label
              for="pdf-input"
              class="cursor-pointer flex flex-col items-center"
            >
              <div class="text-6xl mb-4">📄</div>
              <p class="text-lg font-semibold text-gray-700 mb-2">
                Cliquez pour sélectionner un fichier PDF
              </p>
              <p class="text-sm text-gray-500">
                ou glissez-déposez votre fichier ici
              </p>
              <p class="text-xs text-gray-400 mt-2">
                Taille maximale : 10MB
              </p>
            </label>
          </div>

          <!-- Affichage du fichier sélectionné -->
          <div v-if="pdfFileName" class="mt-4 p-4 bg-indigo-50 border border-indigo-200 rounded-lg flex items-center justify-between">
            <div class="flex items-center gap-3">
              <span class="text-2xl">📄</span>
              <span class="font-medium text-gray-700">{{ pdfFileName }}</span>
            </div>
            
            <!-- Bouton pour supprimer le fichier -->
            <button
              @click="pdfFile = null; pdfFileName = ''"
              class="text-red-600 hover:text-red-800 font-semibold"
            >
              ✕ Supprimer
            </button>
          </div>

          <!-- Bouton pour générer depuis le PDF -->
          <div class="mt-6">
            <button
              @click="generateQcmFromPdf"
              :disabled="loading || !pdfFile"
              class="w-full bg-indigo-600 hover:bg-indigo-700 text-white font-semibold py-3 px-6 rounded-lg transition-all disabled:bg-gray-400 disabled:cursor-not-allowed"
            >
              <!-- Affichage conditionnel selon l'état de chargement -->
              <span v-if="!loading">✨ Générer le QCM depuis le PDF</span>
              <span v-else>⏳ Extraction et génération en cours...</span>
            </button>
          </div>
        </div>

        <!-- Message d'erreur (affiché si error n'est pas vide) -->
        <div v-if="error" class="mt-4 p-4 bg-red-50 border border-red-200 rounded-lg text-red-700">
          {{ error }}
        </div>

        <!-- Conseil -->
        <div class="mt-6 text-sm text-gray-500">
          💡 Conseil : Plus votre texte est long et structuré, meilleurs seront les QCM générés !
        </div>
      </div>

      <!-- ========================================
           ÉTAPE 2 : QUIZ
           Affichée uniquement si showQuiz est true et showResults est false
           ======================================== -->
      <div v-if="showQuiz && !showResults" class="bg-white rounded-2xl shadow-xl p-8">
        <h2 class="text-2xl font-bold text-gray-800 mb-2">
          Étape 2 : Répondez aux questions
        </h2>
        <p class="text-gray-600 mb-8">
          <!-- Affichage du nombre de questions avec pluralisation -->
          {{ questions.length }} question{{ questions.length > 1 ? 's' : '' }} générée{{ questions.length > 1 ? 's' : '' }}
        </p>

        <!-- Liste des questions -->
        <div class="space-y-8">
          <!-- Boucle sur chaque question -->
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
              <!-- Boucle sur chaque option -->
              <div
                v-for="(option, oIndex) in question.options"
                :key="oIndex"
                @click="selectAnswer(qIndex, oIndex)"
                :class="[
                  'p-4 rounded-lg border-2 cursor-pointer transition-all',
                  // Style conditionnel : bleu si sélectionné, gris sinon
                  userAnswers[qIndex] === oIndex
                    ? 'border-indigo-500 bg-indigo-50'
                    : 'border-gray-200 hover:border-indigo-300 bg-white'
                ]"
              >
                <label class="flex items-center cursor-pointer">
                  <!-- Radio button -->
                  <input
                    type="radio"
                    :name="`question-${qIndex}`"
                    :value="oIndex"
                    :checked="userAnswers[qIndex] === oIndex"
                    class="mr-3 w-5 h-5 text-indigo-600"
                  />
                  <!-- Texte de l'option -->
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
            class="bg-green-600 hover:bg-green-700 text-white font-semibold py-4 px-12 rounded-lg transition-all text-lg"
          >
            ✅ Valider mes réponses
          </button>
        </div>
      </div>

      <!-- ========================================
           ÉTAPE 3 : RÉSULTATS
           Affichée uniquement si showResults est true
           ======================================== -->
      <div v-if="showResults">
        <!-- Carte du score -->
        <div class="bg-white rounded-2xl shadow-xl p-8 mb-8 text-center">
          <!-- Emoji dynamique selon le score -->
          <div class="text-6xl mb-4">{{ getEmoji() }}</div>
          
          <!-- Message dynamique selon le score -->
          <h2 class="text-3xl font-bold text-gray-800 mb-2">{{ getMessage() }}</h2>
          
          <!-- Score numérique -->
          <div class="text-6xl font-bold text-indigo-600 my-6">
            {{ score }} / {{ questions.length }}
          </div>
          
          <!-- Pourcentage -->
          <div class="text-2xl text-gray-600 mb-6">
            Score : {{ Math.round((score / questions.length) * 100) }}%
          </div>
          
          <!-- Bouton recommencer -->
          <button
            @click="restart"
            class="bg-indigo-600 hover:bg-indigo-700 text-white font-semibold py-3 px-8 rounded-lg transition-all"
          >
            🔄 Nouveau QCM
          </button>
        </div>

        <!-- Corrections détaillées -->
        <div class="bg-white rounded-2xl shadow-xl p-8">
          <h3 class="text-2xl font-bold text-gray-800 mb-6">📋 Corrections détaillées</h3>
          
          <div class="space-y-6">
            <!-- Boucle sur chaque question pour afficher la correction -->
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
                  
                  <!-- Options avec indication de la bonne réponse -->
                  <div class="space-y-2">
                    <div
                      v-for="(option, oIndex) in question.options"
                      :key="oIndex"
                      class="p-3 rounded-lg"
                      :class="{
                        // Vert pour la bonne réponse
                        'bg-green-100 border-2 border-green-400 font-semibold': oIndex === question.correctAnswer,
                        // Rouge pour la mauvaise réponse sélectionnée
                        'bg-red-100 border-2 border-red-400': oIndex === userAnswers[index] && oIndex !== question.correctAnswer,
                        // Gris pour les autres options
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

    </div>

    <!-- ========================================
         FOOTER - Signature
         ======================================== -->
    <footer class="text-center mt-16 text-gray-600">
      <div class="bg-white rounded-xl shadow-md p-6 max-w-md mx-auto">
        <p class="text-sm mb-2">Créé avec Vue.js 3 + Spring Boot 🚀</p>
        <p class="text-lg font-semibold text-indigo-600">
          Auteur : Oussou 👨‍💻
        </p>
      </div>
    </footer>
  </div>
</template>