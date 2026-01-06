<template>
    <footer class="relative mt-24 ">
      <!-- Séparateur avec gradient -->
      <div class="max-w-5xl mx-auto mb-8">
        <div class="h-px bg-gradient-to-r from-transparent via-gray-300 to-transparent"></div>
      </div>
      
      <!-- Contenu du footer -->
      <div >
        <div class="bg-white/60 backdrop-blur-xl  border border-white/20 p-8 ">
          <div class="grid grid-cols-1 md:flex justify-between gap-8 ">
            
            <!-- Colonne 1 : À propos -->
            <div> 
              <h3 class="font-bold text-gray-900 mb-3 flex items-center gap-2 cursor-pointer" @click="scrollToSection()">
                <div class="w-8 h-8 bg-gradient-to-br from-indigo-600 to-purple-600 rounded-lg flex items-center justify-center">
                    <i class="fa-solid fa-hexagon-nodes fa-xl" style="color: #ffffff;"></i>
                </div>
                QCM Generator
              </h3>
              <p class="text-sm text-gray-600 leading-relaxed">
                Plateforme professionnelle de génération de quiz par intelligence artificielle.<br/> 
                Créez des QCM de qualité en quelques secondes.
              </p>
            </div>
  
            <!-- Colonne 2 : Contact & Auteur -->
            <div >
              <h3 class="font-bold text-gray-900 mb-3">Développeur</h3>
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
            <div class="flex flex-col md:flex-row items-center justify-center text-sm text-gray-600">
              <p>© 2025 QCM Generator. Tous droits réservés.</p>
            </div>
          </div>
        </div>
      </div>
    </footer>
  </template>
  
  <script setup>
const scrollToSection =() =>{
    document.getElementById('homePage')
    .scrollIntoView({behavior:'smooth'})
}
  </script>
  
  <style scoped>
  /* Animation du coeur */
  @keyframes pulse {
    0%, 100% { transform: scale(1); }
    50% { transform: scale(1.1); }
  }
  </style>