<template>
    <div class="py-20">
      <div class="max-w-5xl mx-auto px-4 sm:px-6 lg:px-8">
        
        <!-- En-tête -->
        <div class="text-center mb-16">
          <h1 class="text-4xl lg:text-5xl font-bold text-gray-900 mb-6">
            Contactez-nous
          </h1>
          <p class="text-xl text-gray-600">
            Une question ? Une suggestion ? N'hésitez pas à nous écrire !
          </p>
        </div>
  
        <div class="grid md:grid-cols-2 gap-12">
          
          <!-- Formulaire de contact -->
          <div class="bg-white rounded-2xl shadow-lg p-8">
            <h2 class="text-2xl font-bold text-gray-900 mb-6">
              Envoyez-nous un message
            </h2>
            
            <form @submit.prevent="handleSubmit" class="space-y-6">
              <!-- Nom -->
              <div>
                <label for="name" class="block text-sm font-medium text-gray-700 mb-2">
                  Votre nom *
                </label>
                <input
                  type="text"
                  id="name"
                  v-model="form.name"
                  required
                  class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-indigo-500 transition-colors"
                  placeholder="Jean Dupont"
                />
              </div>
  
              <!-- Email -->
              <div>
                <label for="email" class="block text-sm font-medium text-gray-700 mb-2">
                  Votre email *
                </label>
                <input
                  type="email"
                  id="email"
                  v-model="form.email"
                  required
                  class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-indigo-500 transition-colors"
                  placeholder="jean@exemple.com"
                />
              </div>
  
              <!-- Sujet -->
              <div>
                <label for="subject" class="block text-sm font-medium text-gray-700 mb-2">
                  Sujet *
                </label>
                <select
                  id="subject"
                  v-model="form.subject"
                  required
                  class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-indigo-500 transition-colors"
                >
                  <option value="">Sélectionnez un sujet</option>
                  <option value="question">Question générale</option>
                  <option value="bug">Signaler un bug</option>
                  <option value="feature">Suggérer une fonctionnalité</option>
                  <option value="billing">Question sur la facturation</option>
                  <option value="other">Autre</option>
                </select>
              </div>
  
              <!-- Message -->
              <div>
                <label for="message" class="block text-sm font-medium text-gray-700 mb-2">
                  Votre message *
                </label>
                <textarea
                  id="message"
                  v-model="form.message"
                  required
                  rows="5"
                  class="w-full px-4 py-3 border border-gray-300 rounded-lg focus:ring-2 focus:ring-indigo-500 focus:border-indigo-500 transition-colors resize-none"
                  placeholder="Décrivez votre question ou suggestion..."
                ></textarea>
              </div>
  
              <!-- Bouton d'envoi -->
              <button
                type="submit"
                :disabled="isSubmitting"
                class="w-full bg-gradient-to-r from-indigo-600 to-purple-600 text-white py-3 px-6 rounded-lg font-semibold shadow-lg hover:shadow-xl hover:scale-105 transition-all disabled:opacity-50 disabled:cursor-not-allowed"
              >
                <span v-if="!isSubmitting">Envoyer le message</span>
                <span v-else>Envoi en cours...</span>
              </button>
            </form>
  
            <!-- Message de succès -->
            <div v-if="showSuccess" class="mt-6 p-4 bg-green-50 border border-green-200 rounded-lg">
              <p class="text-green-700 flex items-center gap-2">
                <svg class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20">
                  <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd"></path>
                </svg>
                Message envoyé avec succès ! Nous vous répondrons rapidement.
              </p>
            </div>
          </div>
  
          <!-- Informations de contact -->
          <div class="space-y-8">
            
            <!-- Email -->
            <div class="bg-gradient-to-br from-indigo-50 to-purple-50 rounded-2xl p-8 border border-indigo-100">
              <div class="flex items-start gap-4">
                <div class="w-12 h-12 bg-indigo-600 rounded-lg flex items-center justify-center flex-shrink-0">
                  <svg class="w-6 h-6 text-white" fill="currentColor" viewBox="0 0 20 20">
                    <path d="M2.003 5.884L10 9.882l7.997-3.998A2 2 0 0016 4H4a2 2 0 00-1.997 1.884z"></path>
                    <path d="M18 8.118l-8 4-8-4V14a2 2 0 002 2h12a2 2 0 002-2V8.118z"></path>
                  </svg>
                </div>
                <div>
                  <h3 class="text-lg font-bold text-gray-900 mb-2">Email</h3>
                  <a href="mailto:baldemarc225@gmail.com" class="text-indigo-600 hover:text-indigo-800 transition-colors">
                    baldemarc225@gmail.com
                  </a>
                  <p class="text-sm text-gray-600 mt-2">
                    Nous répondons généralement sous 24h
                  </p>
                </div>
              </div>
            </div>
  
            <!-- FAQ -->
            <div class="bg-gradient-to-br from-blue-50 to-cyan-50 rounded-2xl p-8 border border-blue-100">
              <div class="flex items-start gap-4">
                <div class="w-12 h-12 bg-blue-600 rounded-lg flex items-center justify-center flex-shrink-0">
                  <svg class="w-6 h-6 text-white" fill="currentColor" viewBox="0 0 20 20">
                    <path fill-rule="evenodd" d="M18 10a8 8 0 11-16 0 8 8 0 0116 0zm-8-3a1 1 0 00-.867.5 1 1 0 11-1.731-1A3 3 0 0113 8a3.001 3.001 0 01-2 2.83V11a1 1 0 11-2 0v-1a1 1 0 011-1 1 1 0 100-2zm0 8a1 1 0 100-2 1 1 0 000 2z" clip-rule="evenodd"></path>
                  </svg>
                </div>
                <div>
                  <h3 class="text-lg font-bold text-gray-900 mb-2">Questions fréquentes</h3>
                  <p class="text-gray-700 mb-3">
                    Consultez notre FAQ pour trouver rapidement des réponses
                  </p>
                  <button 
                    @click="$emit('navigate', 'pricing')"
                    class="text-blue-600 hover:text-blue-800 font-semibold inline-flex items-center gap-1 transition-colors"
                  >
                    Voir la FAQ
                    <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7"></path>
                    </svg>
                  </button>
                </div>
              </div>
            </div>
  
            <!-- Réseaux sociaux (si applicable) -->
            <div class="bg-gradient-to-br from-pink-50 to-rose-50 rounded-2xl p-8 border border-pink-100">
              <div class="flex items-start gap-4">
                <div class="w-12 h-12 bg-pink-600 rounded-lg flex items-center justify-center flex-shrink-0">
                  <svg class="w-6 h-6 text-white" fill="currentColor" viewBox="0 0 20 20">
                    <path fill-rule="evenodd" d="M10 0C4.477 0 0 4.484 0 10.017c0 4.425 2.865 8.18 6.839 9.504.5.092.682-.217.682-.483 0-.237-.008-.868-.013-1.703-2.782.605-3.369-1.343-3.369-1.343-.454-1.158-1.11-1.466-1.11-1.466-.908-.62.069-.608.069-.608 1.003.07 1.531 1.032 1.531 1.032.892 1.53 2.341 1.088 2.91.832.092-.647.35-1.088.636-1.338-2.22-.253-4.555-1.113-4.555-4.951 0-1.093.39-1.988 1.029-2.688-.103-.253-.446-1.272.098-2.65 0 0 .84-.27 2.75 1.026A9.564 9.564 0 0110 4.844c.85.004 1.705.115 2.504.337 1.909-1.296 2.747-1.027 2.747-1.027.546 1.379.203 2.398.1 2.651.64.7 1.028 1.595 1.028 2.688 0 3.848-2.339 4.695-4.566 4.942.359.31.678.921.678 1.856 0 1.338-.012 2.419-.012 2.747 0 .268.18.58.688.482A10.019 10.019 0 0020 10.017C20 4.484 15.522 0 10 0z" clip-rule="evenodd"></path>
                  </svg>
                </div>
                <div>
                  <h3 class="text-lg font-bold text-gray-900 mb-2">Projet Open Source</h3>
                  <p class="text-gray-700 mb-3">
                    Contribuez au projet sur GitHub
                  </p>
                  <a 
                    href="#" 
                    class="text-pink-600 hover:text-pink-800 font-semibold inline-flex items-center gap-1 transition-colors"
                  >
                    Voir le repository
                    <svg class="w-4 h-4" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                      <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M10 6H6a2 2 0 00-2 2v10a2 2 0 002 2h10a2 2 0 002-2v-4M14 4h6m0 0v6m0-6L10 14"></path>
                    </svg>
                  </a>
                </div>
              </div>
            </div>
  
          </div>
        </div>
  
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue'
  
  // Emits
  defineEmits(['navigate'])
  
  // Form state
  const form = ref({
    name: '',
    email: '',
    subject: '',
    message: ''
  })
  
  const isSubmitting = ref(false)
  const showSuccess = ref(false)
  
  // Gérer la soumission du formulaire
  const handleSubmit = async () => {
    isSubmitting.value = true
    
    // Simuler l'envoi (à remplacer par un vrai appel API)
    setTimeout(() => {
      isSubmitting.value = false
      showSuccess.value = true
      
      // Réinitialiser le formulaire
      form.value = {
        name: '',
        email: '',
        subject: '',
        message: ''
      }
      
      // Cacher le message de succès après 5 secondes
      setTimeout(() => {
        showSuccess.value = false
      }, 5000)
    }, 1500)
  }
  </script>