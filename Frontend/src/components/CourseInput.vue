<template>
    <!-- Conteneur principal de la saisie -->
    <div class="bg-white rounded-2xl shadow-xl p-8">

      <h2 class="text-2xl font-bold text-gray-800 mb-6">
        Étape 1 : Importez votre cours
      </h2>
  
      <!-- Système d'onglets -->
      <div class="flex gap-4 mb-6 border-b-2 border-gray-200">
        <button
          @click="activeTab = 'text'"
          :class="[
            'pb-3 px-4 font-semibold transition-all',
            activeTab === 'text'
              ? 'text-indigo-600 border-b-4 border-indigo-600'
              : 'text-gray-500 hover:text-gray-700'
          ]"
        >
          📝 Texte
        </button>
        <button
          @click="activeTab = 'pdf'"
          :class="[
            'pb-3 px-4 font-semibold transition-all',
            activeTab === 'pdf'
              ? 'text-indigo-600 border-b-4 border-indigo-600'
              : 'text-gray-500 hover:text-gray-700'
          ]"
        >
          📄 PDF
        </button>
      </div>
  
      <!-- Onglet Texte -->
      <div v-show="activeTab === 'text'">
        <textarea
          v-model="courseText"
          placeholder="Collez ici le texte de votre cours..."
          class="w-full h-64 p-4 border-2 border-gray-300 rounded-lg focus:border-indigo-500 focus:ring-2 focus:ring-indigo-200 transition-all resize-none"
        ></textarea>
  
        <div class="flex gap-4 mt-6">
          <button
            @click="handleGenerateFromText"
            :disabled="loading"
            class="flex-1 bg-indigo-600 hover:bg-indigo-700 text-white font-semibold py-3 px-6 rounded-lg transition-all disabled:bg-gray-400 disabled:cursor-not-allowed"
          >
            <span v-if="!loading">✨ Générer le QCM</span>
            <span v-else>⏳ Génération en cours...</span>
          </button>
  
          <button
            @click="loadExample"
            class="bg-gray-200 hover:bg-gray-300 text-gray-700 font-semibold py-3 px-6 rounded-lg transition-all"
          >
            📝 Exemple
          </button>
        </div>
      </div>
  
      <!-- Onglet PDF -->
      <div v-show="activeTab === 'pdf'">
        <div class="border-2 border-dashed border-gray-300 rounded-lg p-8 text-center hover:border-indigo-400 transition-all">
          <input
            type="file"
            id="pdf-input"
            accept=".pdf"
            @change="handleFileChange"
            class="hidden"
          />
          <label for="pdf-input" class="cursor-pointer flex flex-col items-center">
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
          <button
            @click="removePdfFile"
            class="text-red-600 hover:text-red-800 font-semibold"
          >
            ✕ Supprimer
          </button>
        </div>
  
        <div class="mt-6">
          <button
            @click="handleGenerateFromPdf"
            :disabled="loading || !pdfFile"
            class="w-full bg-indigo-600 hover:bg-indigo-700 text-white font-semibold py-3 px-6 rounded-lg transition-all disabled:bg-gray-400 disabled:cursor-not-allowed"
          >
            <span v-if="!loading">✨ Générer le QCM depuis le PDF</span>
            <span v-else>⏳ Extraction et génération en cours...</span>
          </button>
        </div>
      </div>
  
      <!-- Messages d'erreur -->
      <div v-if="localError || error" class="mt-4 p-4 bg-red-50 border border-red-200 rounded-lg text-red-700">
        {{ localError || error }}
      </div>
  
      <!-- Conseil -->
      <div class="mt-6 text-sm text-gray-500">
        💡 Conseil : Plus votre texte est long et structuré, meilleurs seront les QCM générés !
      </div>
    </div>
  </template>

<script setup>
import { ref } from 'vue'
// ========================================
// PROPS (données reçues du parent App.vue)
// ========================================

const props = defineProps({
    loading: {
        type: Boolean,
        default: false
    },
    error: {
        type: String,
        default: ''
    }
})

// ========================================
// EMITS (événements envoyés au parent)
// ========================================
const emit = defineEmits(['generate-from-text', 'generate-from-pdf'])

// ========================================
// STATE LOCAL
// ========================================
const activeTab = ref('text') // 'text' ou 'pdf'
const courseText = ref('')
const pdfFile = ref(null)
const pdfFileName = ref('')
const localError = ref('')

// ========================================
// FONCTIONS
// ========================================

// Charger un exemple de texte
const loadExample = () =>{
    courseText.value = "Le Canada est un pays d’Amérique du Nord, le deuxième plus grand au monde en superficie, situé entre les États-Unis au sud et l’océan Arctique au nord. Il possède dix provinces et trois territoires, chacun ayant ses propres particularités culturelles et économiques. Le pays est reconnu pour sa diversité géographique : on y trouve des montagnes, des forêts, des lacs et des plaines. La population canadienne est multiculturelle et bilingue, avec l’anglais et le français comme langues officielles. Le Canada est également connu pour son système de santé universel, sa stabilité politique et son engagement envers l’environnement. Son économie repose sur des secteurs variés comme les ressources naturelles, la technologie et les services."
    activeTab.value = "text"
}

// Générer QCM depuis le texte
const handleGenerateFromText = () =>{
    if(!courseText.value.trim()){
        localError.value = "Veuillez entrer du texte !."
        return 
    }

    localError.value = ''
    emit('generate-from-text', courseText.value)
}

// Gérer la sélection de fichier PDF
const handleFileChange = (event) =>{
    const file = event.target.files[0]

    if(file){
        // verifier le type
        if(file.type !== 'application/pdf'){
            localError.value = 'Veuillez sélectionner un fichier PDF'
            pdfFile.value = null
            pdfFileName.value = ''
            return
        }
    }

     // Vérifier la taille (10MB max)
     if (file.size > 10 * 1024 * 1024) {
      localError.value = 'Le fichier est trop volumineux (max 10MB)'
      pdfFile.value = null
      pdfFileName.value = ''
      return
    }

    pdfFile.value = file
    pdfFileName.value = file.name
    localError.value = ''
}

// Générer QCM depuis le PDF
const handleGenerateFromPdf = () => {
  if (!pdfFile.value) {
    localError.value = 'Veuillez sélectionner un fichier PDF'
    return
  }
  
  localError.value = ''
  emit('generate-from-pdf', pdfFile.value)
}

// Supprimer le fichier sélectionné
const removePdfFile = () => {
  pdfFile.value = null
  pdfFileName.value = ''
  localError.value = ''
}

</script>

<style lang="scss" scoped>

</style>