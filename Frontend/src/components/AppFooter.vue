<template>
    <footer class="relative mt-24 ">
      <!-- Séparateur avec gradient -->
      <div class="max-w-5xl mx-auto mb-8">
        <div class="h-px bg-gradient-to-r from-transparent via-gray-300 to-transparent"></div>
      </div>
      
      <!-- Contenu du footer -->
      <div >
        <div class="bg-white/60 backdrop-blur-xl rounded-2xl shadow-xl border border-white/20 p-8">
          <div class="grid grid-cols-1 md:grid-cols-3 gap-8">
            
            <!-- Colonne 1 : À propos -->
            <div>
              <h3 class="font-bold text-gray-900 mb-3 flex items-center gap-2">
                <div class="w-8 h-8 bg-gradient-to-br from-indigo-600 to-purple-600 rounded-lg flex items-center justify-center">
                  <svg class="w-5 h-5 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                    <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z"></path>
                  </svg>
                </div>
                QCM Generator
              </h3>
              <p class="text-sm text-gray-600 leading-relaxed">
                Plateforme professionnelle de génération de quiz par intelligence artificielle. 
                Créez des QCM de qualité en quelques secondes.
              </p>
            </div>
  
            <!-- Colonne 2 : Technologies -->
            <div>
              <h3 class="font-bold text-gray-900 mb-3">⚡ Technologies</h3>
              <ul class="space-y-2 text-sm text-gray-600">
                <li class="flex items-center gap-2">
                  <svg class="w-4 h-4 text-green-500" fill="currentColor" viewBox="0 0 20 20">
                    <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd"></path>
                  </svg>
                  Vue.js 3 + Composition API
                </li>
                <li class="flex items-center gap-2">
                  <svg class="w-4 h-4 text-green-500" fill="currentColor" viewBox="0 0 20 20">
                    <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd"></path>
                  </svg>
                  Spring Boot + PostgreSQL
                </li>
                <li class="flex items-center gap-2">
                  <svg class="w-4 h-4 text-green-500" fill="currentColor" viewBox="0 0 20 20">
                    <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd"></path>
                  </svg>
                  OpenAI GPT-4o-mini
                </li>
              </ul>
            </div>
  
            <!-- Colonne 3 : Contact & Auteur -->
            <div>
              <h3 class="font-bold text-gray-900 mb-3">👨‍💻 Développeur</h3>
              <div class="space-y-3">
                <p class="text-gray-700 font-semibold">Oussou</p>
                <a 
                  href="mailto:baldemarc225@gmail.com" 
                  class="inline-flex items-center gap-2 text-sm text-indigo-600 hover:text-indigo-800 transition-colors group"
                >
                  <svg class="w-4 h-4 group-hover:scale-110 transition-transform" fill="currentColor" viewBox="0 0 20 20">
                    <path d="M2.003 5.884L10 9.882l7.997-3.998A2 2 0 0016 4H4a2 2 0 00-1.997 1.884z"></path>
                    <path d="M18 8.118l-8 4-8-4V14a2 2 0 002 2h12a2 2 0 002-2V8.118z"></path>
                  </svg>
                  baldemarc225@gmail.com
                </a>
              </div>
            </div>
          </div>
  
          <!-- Copyright -->
          <div class="mt-8 pt-6 border-t border-gray-200">
            <div class="flex flex-col md:flex-row items-center justify-between gap-4 text-sm text-gray-600">
              <p>© 2025 QCM Generator. Tous droits réservés.</p>
              <div class="flex items-center gap-2">
                <span class="flex items-center gap-1">
                  Fait avec 
                  <svg class="w-4 h-4 text-red-500 animate-pulse" fill="currentColor" viewBox="0 0 20 20">
                    <path fill-rule="evenodd" d="M3.172 5.172a4 4 0 015.656 0L10 6.343l1.172-1.171a4 4 0 115.656 5.656L10 17.657l-6.828-6.829a4 4 0 010-5.656z" clip-rule="evenodd"></path>
                  </svg>
                  par Oussou
                </span>
              </div>
            </div>
          </div>
        </div>
      </div>
    </footer>
  </template>
  
  <script setup>
  // Pas de logique nécessaire
  </script>
  
  <style scoped>
  /* Animation du coeur */
  @keyframes pulse {
    0%, 100% { transform: scale(1); }
    50% { transform: scale(1.1); }
  }
  </style>