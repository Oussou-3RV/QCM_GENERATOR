<template>
    <div class="overflow-hidden">
      
      <!-- ====================================== -->
      <!-- HERO SECTION -->
      <!-- ====================================== -->
      <section class="relative py-20 lg:py-32 overflow-hidden">
        <!-- Background effects -->
        <div class="absolute inset-0 bg-gradient-to-br from-indigo-50 via-purple-50 to-pink-50 opacity-60"></div>
        <div class="absolute top-0 right-0 w-96 h-96 bg-gradient-to-br from-indigo-200/30 to-purple-200/30 rounded-full blur-3xl"></div>
        <div class="absolute bottom-0 left-0 w-96 h-96 bg-gradient-to-tr from-blue-200/30 to-cyan-200/30 rounded-full blur-3xl"></div>
        
        <div class="relative max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div class="grid lg:grid-cols-2 gap-12 items-center">
            
            <!-- Texte principal -->
            <div class="text-center lg:text-left">
              <!-- Badge -->
              <div class="inline-flex items-center gap-2 bg-indigo-100 text-indigo-700 px-4 py-2 rounded-full text-sm font-semibold mb-6">
                <svg class="w-4 h-4" fill="currentColor" viewBox="0 0 20 20">
                  <path d="M9.049 2.927c.3-.921 1.603-.921 1.902 0l1.07 3.292a1 1 0 00.95.69h3.462c.969 0 1.371 1.24.588 1.81l-2.8 2.034a1 1 0 00-.364 1.118l1.07 3.292c.3.921-.755 1.688-1.54 1.118l-2.8-2.034a1 1 0 00-1.175 0l-2.8 2.034c-.784.57-1.838-.197-1.539-1.118l1.07-3.292a1 1 0 00-.364-1.118L2.98 8.72c-.783-.57-.38-1.81.588-1.81h3.461a1 1 0 00.951-.69l1.07-3.292z"></path>
                </svg>
                Intelligence Artificielle GPT-4
              </div>
  
              <!-- Titre principal -->
              <h1 class="text-5xl lg:text-6xl font-bold text-gray-900 mb-6 leading-tight">
                Créez des <span class="bg-gradient-to-r from-indigo-600 to-purple-600 bg-clip-text text-transparent">QCM intelligents</span> en quelques secondes
              </h1>
  
              <!-- Description -->
              <p class="text-xl text-gray-600 mb-8 leading-relaxed">
                Transformez vos cours en quiz interactifs grâce à l'intelligence artificielle. 
                Téléchargez un PDF ou collez votre texte, et obtenez des questions de qualité instantanément.
              </p>
  
              <!-- CTA Buttons -->
              <div class="flex flex-col sm:flex-row gap-4 justify-center lg:justify-start">
                <button 
                  @click="$emit('navigate', 'generator')"
                  class="bg-gradient-to-r from-indigo-600 to-purple-600 text-white px-8 py-4 rounded-xl font-semibold text-lg shadow-lg hover:shadow-xl hover:scale-105 transition-all"
                >
                  Essayer gratuitement
                </button>
                <button 
                  @click="scrollToFeatures"
                  class="bg-white text-gray-700 px-8 py-4 rounded-xl font-semibold text-lg border-2 border-gray-200 hover:border-indigo-300 hover:bg-gray-50 transition-all"
                >
                  En savoir plus
                </button>
              </div>
  
              <!-- Stats -->
              <div class="grid grid-cols-3 gap-6 mt-12 pt-8 border-t border-gray-200">
                <div>
                  <div class="text-3xl font-bold text-indigo-600">10k+</div>
                  <div class="text-sm text-gray-600">QCM générés</div>
                </div>
                <div>
                  <div class="text-3xl font-bold text-purple-600">98%</div>
                  <div class="text-sm text-gray-600">Satisfaction</div>
                </div>
                <div>
                  <div class="text-3xl font-bold text-pink-600">5★</div>
                  <div class="text-sm text-gray-600">Note moyenne</div>
                </div>
              </div>
            </div>
  
            <!-- Illustration / Mockup -->
            <div class="relative">
              <div class="relative bg-white rounded-2xl shadow-2xl p-6 transform hover:scale-105 transition-transform duration-300">
                <!-- Mockup d'un QCM -->
                <div class="space-y-4">
                  <div class="flex items-center gap-3 pb-4 border-b border-gray-200">
                    <div class="w-10 h-10 bg-gradient-to-br from-indigo-500 to-purple-500 rounded-lg flex items-center justify-center">
                      <svg class="w-6 h-6 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                        <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z"></path>
                      </svg>
                    </div>
                    <div>
                      <div class="font-semibold text-gray-900">Aperçu d'un QCM</div>
                      <div class="text-sm text-gray-500">Généré automatiquement</div>
                    </div>
                  </div>
                  
                  <div class="space-y-3">
                    <div class="font-medium text-gray-900">Question 1: Qu'est-ce que Vue.js ?</div>
                    <div class="space-y-2">
                      <div class="p-3 bg-gray-50 rounded-lg border border-gray-200 hover:border-indigo-300 transition-colors cursor-pointer">
                        <label class="flex items-center gap-2 cursor-pointer">
                          <input type="radio" name="demo" class="text-indigo-600" />
                          <span class="text-gray-700">Un framework JavaScript</span>
                        </label>
                      </div>
                      <div class="p-3 bg-green-50 rounded-lg border-2 border-green-400">
                        <label class="flex items-center gap-2">
                          <input type="radio" name="demo" checked class="text-green-600" />
                          <span class="text-gray-700 font-medium">Un framework JavaScript progressif ✓</span>
                        </label>
                      </div>
                      <div class="p-3 bg-gray-50 rounded-lg border border-gray-200 hover:border-indigo-300 transition-colors cursor-pointer">
                        <label class="flex items-center gap-2 cursor-pointer">
                          <input type="radio" name="demo" class="text-indigo-600" />
                          <span class="text-gray-700">Une librairie CSS</span>
                        </label>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
              
              <!-- Badges flottants -->
              <div class="absolute -top-4 -right-4 bg-white rounded-xl shadow-lg px-4 py-2 flex items-center gap-2 animate-bounce">
                <svg class="w-5 h-5 text-green-500" fill="currentColor" viewBox="0 0 20 20">
                  <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd"></path>
                </svg>
                <span class="font-semibold text-gray-700">IA Avancée</span>
              </div>
            </div>
          </div>
        </div>
      </section>
  
      <!-- ====================================== -->
      <!-- FEATURES SECTION -->
      <!-- ====================================== -->
      <section id="features" class="py-20 bg-white">
        <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <!-- Titre de section -->
          <div class="text-center mb-16">
            <h2 class="text-4xl font-bold text-gray-900 mb-4">
              Pourquoi choisir QCM Generator ?
            </h2>
            <p class="text-xl text-gray-600 max-w-2xl mx-auto">
              Une solution complète pour créer, passer et analyser vos quiz en toute simplicité
            </p>
          </div>
  
          <!-- Grille de fonctionnalités -->
          <div class="grid md:grid-cols-3 gap-8">
            <!-- Feature 1 -->
            <div class="group p-8 bg-gradient-to-br from-indigo-50 to-purple-50 rounded-2xl hover:shadow-xl transition-all duration-300 border border-indigo-100">
              <div class="w-14 h-14 bg-gradient-to-br from-indigo-500 to-purple-500 rounded-xl flex items-center justify-center mb-6 group-hover:scale-110 transition-transform">
                <svg class="w-8 h-8 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M13 10V3L4 14h7v7l9-11h-7z"></path>
                </svg>
              </div>
              <h3 class="text-2xl font-bold text-gray-900 mb-3">Génération Instantanée</h3>
              <p class="text-gray-600 leading-relaxed">
                Créez des QCM de qualité en quelques secondes grâce à GPT-4. 
                Plus besoin de passer des heures à rédiger des questions.
              </p>
            </div>
  
            <!-- Feature 2 -->
            <div class="group p-8 bg-gradient-to-br from-blue-50 to-cyan-50 rounded-2xl hover:shadow-xl transition-all duration-300 border border-blue-100">
              <div class="w-14 h-14 bg-gradient-to-br from-blue-500 to-cyan-500 rounded-xl flex items-center justify-center mb-6 group-hover:scale-110 transition-transform">
                <svg class="w-8 h-8 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z"></path>
                </svg>
              </div>
              <h3 class="text-2xl font-bold text-gray-900 mb-3">PDF & Texte</h3>
              <p class="text-gray-600 leading-relaxed">
                Importez vos documents PDF ou collez directement votre texte. 
                Notre IA s'adapte à tous les formats.
              </p>
            </div>
  
            <!-- Feature 3 -->
            <div class="group p-8 bg-gradient-to-br from-pink-50 to-rose-50 rounded-2xl hover:shadow-xl transition-all duration-300 border border-pink-100">
              <div class="w-14 h-14 bg-gradient-to-br from-pink-500 to-rose-500 rounded-xl flex items-center justify-center mb-6 group-hover:scale-110 transition-transform">
                <svg class="w-8 h-8 text-white" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 19v-6a2 2 0 00-2-2H5a2 2 0 00-2 2v6a2 2 0 002 2h2a2 2 0 002-2zm0 0V9a2 2 0 012-2h2a2 2 0 012 2v10m-6 0a2 2 0 002 2h2a2 2 0 002-2m0 0V5a2 2 0 012-2h2a2 2 0 012 2v14a2 2 0 01-2 2h-2a2 2 0 01-2-2z"></path>
                </svg>
              </div>
              <h3 class="text-2xl font-bold text-gray-900 mb-3">Corrections Automatiques</h3>
              <p class="text-gray-600 leading-relaxed">
                Obtenez vos résultats instantanément avec des corrections détaillées 
                et des statistiques de performance.
              </p>
            </div>
          </div>
        </div>
      </section>
  
      <!-- ====================================== -->
      <!-- HOW IT WORKS SECTION -->
      <!-- ====================================== -->
      <section class="py-20 bg-gray-50">
        <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
          <div class="text-center mb-16">
            <h2 class="text-4xl font-bold text-gray-900 mb-4">
              Comment ça marche ?
            </h2>
            <p class="text-xl text-gray-600">
              Créez vos QCM en 3 étapes simples
            </p>
          </div>
  
          <div class="grid md:grid-cols-3 gap-8">
            <!-- Étape 1 -->
            <div class="relative">
              <div class="bg-white rounded-2xl p-8 shadow-lg hover:shadow-xl transition-shadow">
                <div class="w-12 h-12 bg-indigo-100 text-indigo-600 rounded-full flex items-center justify-center font-bold text-xl mb-6">
                  1
                </div>
                <h3 class="text-xl font-bold text-gray-900 mb-3">Importez votre contenu</h3>
                <p class="text-gray-600">
                  Téléchargez un PDF ou collez votre texte de cours directement dans l'interface.
                </p>
              </div>
              <!-- Flèche (desktop) -->
              <div class="hidden md:block absolute top-1/2 -right-4 transform -translate-y-1/2">
                <svg class="w-8 h-8 text-indigo-300" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7"></path>
                </svg>
              </div>
            </div>
  
            <!-- Étape 2 -->
            <div class="relative">
              <div class="bg-white rounded-2xl p-8 shadow-lg hover:shadow-xl transition-shadow">
                <div class="w-12 h-12 bg-purple-100 text-purple-600 rounded-full flex items-center justify-center font-bold text-xl mb-6">
                  2
                </div>
                <h3 class="text-xl font-bold text-gray-900 mb-3">L'IA génère les questions</h3>
                <p class="text-gray-600">
                  Notre intelligence artificielle analyse votre contenu et crée des questions pertinentes.
                </p>
              </div>
              <!-- Flèche (desktop) -->
              <div class="hidden md:block absolute top-1/2 -right-4 transform -translate-y-1/2">
                <svg class="w-8 h-8 text-purple-300" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                  <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7"></path>
                </svg>
              </div>
            </div>
  
            <!-- Étape 3 -->
            <div class="relative">
              <div class="bg-white rounded-2xl p-8 shadow-lg hover:shadow-xl transition-shadow">
                <div class="w-12 h-12 bg-pink-100 text-pink-600 rounded-full flex items-center justify-center font-bold text-xl mb-6">
                  3
                </div>
                <h3 class="text-xl font-bold text-gray-900 mb-3">Passez et révisez</h3>
                <p class="text-gray-600">
                  Répondez aux questions et obtenez vos résultats avec des corrections détaillées.
                </p>
              </div>
            </div>
          </div>
        </div>
      </section>
  
      <!-- ====================================== -->
      <!-- CTA SECTION -->
      <!-- ====================================== -->
      <section class="py-20 bg-gradient-to-r from-indigo-600 to-purple-600 text-white">
        <div class="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8 text-center">
          <h2 class="text-4xl font-bold mb-6">
            Prêt à révolutionner vos révisions ?
          </h2>
          <p class="text-xl mb-8 text-indigo-100">
            Rejoignez des milliers d'étudiants qui utilisent déjà QCM Generator
          </p>
          <button 
            @click="$emit('navigate', 'generator')"
            class="bg-white text-indigo-600 px-8 py-4 rounded-xl font-semibold text-lg shadow-lg hover:shadow-xl hover:scale-105 transition-all"
          >
            Commencer gratuitement
          </button>
        </div>
      </section>
  
    </div>
  </template>
  
  <script setup>
  // Emits
  defineEmits(['navigate'])
  
  // Fonction pour scroller vers les fonctionnalités
  const scrollToFeatures = () => {
    document.getElementById('features')?.scrollIntoView({ 
      behavior: 'smooth' 
    })
  }
  </script>
  
  <style scoped>
  /* Animation du badge */
  @keyframes bounce {
    0%, 100% {
      transform: translateY(0);
    }
    50% {
      transform: translateY(-10px);
    }
  }
  
  .animate-bounce {
    animation: bounce 2s infinite;
  }
  </style>