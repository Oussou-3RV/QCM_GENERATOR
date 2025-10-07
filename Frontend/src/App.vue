<script setup>
import { ref } from 'vue'
import axios from 'axios'

const inputText = ref('')
const response = ref(null)
const loading = ref(false)
const error = ref('')

const sendToBackend = async () => {
  // Vérifier que le champ n'est pas vide
  if (!inputText.value.trim()) {
    error.value = 'Veuillez entrer du texte'
    return
  }

  loading.value = true
  error.value = ''
  response.value = null

  try {
    // Envoyer la requête POST au backend
    const result = await axios.post('http://localhost:8080/api/test', {
      text: inputText.value
    })

    // Stocker la réponse
    response.value = result.data

  } catch (err) {
    error.value = 'Erreur : Vérifiez que le backend est lancé sur le port 8080'
    console.error(err)
  } finally {
    loading.value = false
  }
}

const reset = () => {
  inputText.value = ''
  response.value = null
  error.value = ''
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
          Test de communication Frontend ↔️ Backend
        </p>
      </header>

      <!-- Formulaire -->
      <div class="bg-white rounded-2xl shadow-xl p-8 mb-6">
        <h2 class="text-2xl font-bold text-gray-800 mb-4">
          Envoyez du texte au backend
        </h2>

        <textarea
          v-model="inputText"
          placeholder="Tapez votre texte ici..."
          class="w-full h-32 p-4 border-2 border-gray-300 rounded-lg focus:border-indigo-500 focus:ring-2 focus:ring-indigo-200 transition-all resize-none mb-4"
        ></textarea>

        <div class="flex gap-4">
          <button
            @click="sendToBackend"
            :disabled="loading"
            class="flex-1 bg-indigo-600 hover:bg-indigo-700 text-white font-semibold py-3 px-6 rounded-lg transition-all disabled:bg-gray-400 disabled:cursor-not-allowed"
          >
            <span v-if="!loading">🚀 Envoyer au backend</span>
            <span v-else>⏳ Envoi en cours...</span>
          </button>

          <button
            @click="reset"
            class="bg-gray-200 hover:bg-gray-300 text-gray-700 font-semibold py-3 px-6 rounded-lg transition-all"
          >
            🔄 Réinitialiser
          </button>
        </div>

        <!-- Message d'erreur -->
        <div v-if="error" class="mt-4 p-4 bg-red-50 border border-red-200 rounded-lg text-red-700">
          {{ error }}
        </div>
      </div>

      <!-- Réponse du backend -->
      <div v-if="response" class="bg-green-50 border-2 border-green-200 rounded-2xl shadow-xl p-8">
        <h3 class="text-2xl font-bold text-green-800 mb-4">
          ✅ Réponse du backend
        </h3>
        
        <div class="bg-white rounded-lg p-4 mb-4">
          <p class="text-lg text-gray-800">
            <strong>Message :</strong> {{ response.message }}
          </p>
        </div>

        <div class="bg-white rounded-lg p-4">
          <p class="text-lg text-gray-800">
            <strong>Nombre de caractères :</strong> {{ response.textLength }}
          </p>
        </div>
      </div>

    </div>
  </div>
</template>