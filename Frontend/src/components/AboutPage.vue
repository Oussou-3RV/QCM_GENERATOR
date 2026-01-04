<template>
    <div class="py-20">
      <div class="max-w-4xl mx-auto px-4 sm:px-6 lg:px-8">
        
        <!-- En-tête -->
        <div class="text-center mb-16">
          <h1 class="text-4xl lg:text-5xl font-bold text-gray-900 mb-6">
            À propos de QCM Generator
          </h1>
          <p class="text-xl text-gray-600">
            Notre mission : rendre l'apprentissage plus efficace grâce à l'intelligence artificielle
          </p>
        </div>
  
        <!-- Histoire -->
        <div class="bg-white rounded-2xl shadow-lg p-8 mb-12">
          <h2 class="text-2xl font-bold text-gray-900 mb-6 flex items-center gap-3">
            <span class="text-3xl">📖</span>
            Notre histoire
          </h2>
          <div class="space-y-4 text-gray-700 leading-relaxed">
            <p>
              QCM Generator est né d'une frustration partagée par des milliers d'étudiants : 
              passer des heures à créer des quiz pour réviser leurs cours.
            </p>
            <p>
              En 2024, nous avons décidé de révolutionner cette expérience en combinant 
              l'intelligence artificielle GPT-4 avec une interface intuitive. Notre objectif 
              était simple : permettre à n'importe qui de créer des QCM de qualité en quelques secondes.
            </p>
            <p>
              Aujourd'hui, des milliers d'étudiants, enseignants et professionnels utilisent 
              QCM Generator pour préparer leurs examens, former leurs équipes ou simplement 
              valider leurs connaissances.
            </p>
          </div>
        </div>
  
        <!-- Valeurs -->
        <div class="mb-12">
          <h2 class="text-2xl font-bold text-gray-900 mb-8 text-center">
            Nos valeurs
          </h2>
          <div class="grid md:grid-cols-3 gap-6">
            <div class="bg-gradient-to-br from-indigo-50 to-purple-50 rounded-xl p-6 border border-indigo-100">
              <div class="text-4xl mb-4">🎯</div>
              <h3 class="text-lg font-bold text-gray-900 mb-2">Excellence</h3>
              <p class="text-gray-600 text-sm">
                Nous utilisons les technologies les plus avancées pour garantir 
                la qualité de chaque QCM généré.
              </p>
            </div>
  
            <div class="bg-gradient-to-br from-blue-50 to-cyan-50 rounded-xl p-6 border border-blue-100">
              <div class="text-4xl mb-4">⚡</div>
              <h3 class="text-lg font-bold text-gray-900 mb-2">Simplicité</h3>
              <p class="text-gray-600 text-sm">
                Une interface intuitive qui permet à tout le monde de créer 
                des quiz sans compétences techniques.
              </p>
            </div>
  
            <div class="bg-gradient-to-br from-pink-50 to-rose-50 rounded-xl p-6 border border-pink-100">
              <div class="text-4xl mb-4">🤝</div>
              <h3 class="text-lg font-bold text-gray-900 mb-2">Accessibilité</h3>
              <p class="text-gray-600 text-sm">
                Un plan gratuit généreux pour que l'apprentissage soit 
                accessible à tous.
              </p>
            </div>
          </div>
        </div>
  
        <!-- Technologies -->
        <div class="bg-white rounded-2xl shadow-lg p-8 mb-12">
          <h2 class="text-2xl font-bold text-gray-900 mb-6 flex items-center gap-3">
            <span class="text-3xl">⚙️</span>
            Technologies utilisées
          </h2>
          <div class="grid md:grid-cols-2 gap-6">
            <div>
              <h3 class="font-semibold text-gray-900 mb-3 flex items-center gap-2">
                <svg class="w-5 h-5 text-green-500" fill="currentColor" viewBox="0 0 20 20">
                  <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd"></path>
                </svg>
                Frontend
              </h3>
              <ul class="space-y-2 text-gray-600 text-sm">
                <li>• Vue.js 3 (Composition API)</li>
                <li>• TailwindCSS</li>
                <li>• Vite</li>
                <li>• Axios</li>
              </ul>
            </div>
  
            <div>
              <h3 class="font-semibold text-gray-900 mb-3 flex items-center gap-2">
                <svg class="w-5 h-5 text-blue-500" fill="currentColor" viewBox="0 0 20 20">
                  <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd"></path>
                </svg>
                Backend
              </h3>
              <ul class="space-y-2 text-gray-600 text-sm">
                <li>• Java 17 + Spring Boot</li>
                <li>• PostgreSQL</li>
                <li>• OpenAI GPT-4o-mini</li>
                <li>• Apache PDFBox</li>
              </ul>
            </div>
          </div>
        </div>
  
        <!-- Équipe -->
        <div class="bg-gradient-to-r from-indigo-600 to-purple-600 rounded-2xl shadow-xl p-8 text-white text-center">
          <h2 class="text-2xl font-bold mb-4">
            Créé avec passion ❤️
          </h2>
          <p class="text-indigo-100 mb-6">
            QCM Generator est développé et maintenu par Oussou, étudiant passionné 
            par le développement web et l'intelligence artificielle.
          </p>
          <div class="flex items-center justify-center gap-4">
            <a 
              href="mailto:baldemarc225@gmail.com"
              class="inline-flex items-center gap-2 bg-white text-indigo-600 px-6 py-3 rounded-lg font-semibold hover:bg-gray-100 transition-colors"
            >
              <svg class="w-5 h-5" fill="currentColor" viewBox="0 0 20 20">
                <path d="M2.003 5.884L10 9.882l7.997-3.998A2 2 0 0016 4H4a2 2 0 00-1.997 1.884z"></path>
                <path d="M18 8.118l-8 4-8-4V14a2 2 0 002 2h12a2 2 0 002-2V8.118z"></path>
              </svg>
              Me contacter
            </a>
          </div>
        </div>
  
      </div>
    </div>
  </template>
  
  <script setup>
  // Pas de logique nécessaire
  </script>