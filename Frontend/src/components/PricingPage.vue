<template>
    <div class="py-20">
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8">
        
        <!-- En-tête -->
        <div class="text-center mb-16">
          <h1 class="text-4xl lg:text-5xl font-bold text-gray-900 mb-4">
            Choisissez votre plan
          </h1>
          <p class="text-xl text-gray-600 max-w-2xl mx-auto">
            Commencez gratuitement, passez à Pro quand vous êtes prêt
          </p>
        </div>
  
        <!-- Grille de plans -->
        <div class="grid md:grid-cols-2 gap-8 max-w-5xl mx-auto">
          
          <!-- Plan Gratuit -->
          <div class="bg-white rounded-2xl shadow-lg border-2 border-gray-200 p-8 hover:shadow-xl transition-shadow">
            <div class="text-center mb-6">
              <h3 class="text-2xl font-bold text-gray-900 mb-2">Gratuit</h3>
              <div class="text-5xl font-bold text-gray-900 mb-2">0€</div>
              <p class="text-gray-600">Pour toujours</p>
            </div>
  
            <ul class="space-y-4 mb-8">
              <li class="flex items-start gap-3">
                <svg class="w-6 h-6 text-green-500 flex-shrink-0 mt-0.5" fill="currentColor" viewBox="0 0 20 20">
                  <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd"></path>
                </svg>
                <span class="text-gray-700"><strong>5 QCM</strong> par jour</span>
              </li>
              <li class="flex items-start gap-3">
                <svg class="w-6 h-6 text-green-500 flex-shrink-0 mt-0.5" fill="currentColor" viewBox="0 0 20 20">
                  <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd"></path>
                </svg>
                <span class="text-gray-700">Jusqu'à <strong>10 questions</strong> par QCM</span>
              </li>
              <li class="flex items-start gap-3">
                <svg class="w-6 h-6 text-green-500 flex-shrink-0 mt-0.5" fill="currentColor" viewBox="0 0 20 20">
                  <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd"></path>
                </svg>
                <span class="text-gray-700">Import <strong>texte & PDF</strong></span>
              </li>
              <li class="flex items-start gap-3">
                <svg class="w-6 h-6 text-green-500 flex-shrink-0 mt-0.5" fill="currentColor" viewBox="0 0 20 20">
                  <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd"></path>
                </svg>
                <span class="text-gray-700">Corrections automatiques</span>
              </li>
              <li class="flex items-start gap-3">
                <svg class="w-6 h-6 text-green-500 flex-shrink-0 mt-0.5" fill="currentColor" viewBox="0 0 20 20">
                  <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd"></path>
                </svg>
                <span class="text-gray-700">Historique des <strong>10 derniers QCM</strong></span>
              </li>
            </ul>
  
            <button 
              @click="$emit('navigate', 'generator')"
              class="w-full bg-gray-100 text-gray-900 py-3 px-6 rounded-lg font-semibold hover:bg-gray-200 transition-colors"
            >
              Commencer gratuitement
            </button>
          </div>
  
          <!-- Plan Pro (Populaire) -->
          <div class="relative bg-gradient-to-br from-indigo-600 to-purple-600 rounded-2xl shadow-2xl p-8 text-white transform md:scale-105">
            <!-- Badge Populaire -->
            <div class="absolute -top-4 left-1/2 transform -translate-x-1/2">
              <span class="bg-yellow-400 text-gray-900 px-4 py-1 rounded-full text-sm font-bold shadow-lg">
                ⭐ POPULAIRE
              </span>
            </div>
  
            <div class="text-center mb-6 mt-4">
              <h3 class="text-2xl font-bold mb-2">Pro</h3>
              <div class="text-5xl font-bold mb-2">9,99$</div>
              <p class="text-indigo-200">par mois</p>
            </div>
  
            <ul class="space-y-4 mb-8">
              <li class="flex items-start gap-3">
                <svg class="w-6 h-6 text-yellow-300 flex-shrink-0 mt-0.5" fill="currentColor" viewBox="0 0 20 20">
                  <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd"></path>
                </svg>
                <span><strong>QCM illimités</strong></span>
              </li>
              <li class="flex items-start gap-3">
                <svg class="w-6 h-6 text-yellow-300 flex-shrink-0 mt-0.5" fill="currentColor" viewBox="0 0 20 20">
                  <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd"></path>
                </svg>
                <span>Jusqu'à <strong>50 questions</strong> par QCM</span>
              </li>
              <li class="flex items-start gap-3">
                <svg class="w-6 h-6 text-yellow-300 flex-shrink-0 mt-0.5" fill="currentColor" viewBox="0 0 20 20">
                  <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd"></path>
                </svg>
                <span>PDF jusqu'à <strong>50MB</strong></span>
              </li>
              <li class="flex items-start gap-3">
                <svg class="w-6 h-6 text-yellow-300 flex-shrink-0 mt-0.5" fill="currentColor" viewBox="0 0 20 20">
                  <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd"></path>
                </svg>
                <span><strong>Historique illimité</strong></span>
              </li>
              <li class="flex items-start gap-3">
                <svg class="w-6 h-6 text-yellow-300 flex-shrink-0 mt-0.5" fill="currentColor" viewBox="0 0 20 20">
                  <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd"></path>
                </svg>
                <span><strong>Export PDF</strong> des QCM</span>
              </li>
              <li class="flex items-start gap-3">
                <svg class="w-6 h-6 text-yellow-300 flex-shrink-0 mt-0.5" fill="currentColor" viewBox="0 0 20 20">
                  <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd"></path>
                </svg>
                <span><strong>Partage</strong> de QCM par lien</span>
              </li>
              <li class="flex items-start gap-3">
                <svg class="w-6 h-6 text-yellow-300 flex-shrink-0 mt-0.5" fill="currentColor" viewBox="0 0 20 20">
                  <path fill-rule="evenodd" d="M10 18a8 8 0 100-16 8 8 0 000 16zm3.707-9.293a1 1 0 00-1.414-1.414L9 10.586 7.707 9.293a1 1 0 00-1.414 1.414l2 2a1 1 0 001.414 0l4-4z" clip-rule="evenodd"></path>
                </svg>
                <span><strong>Support prioritaire</strong></span>
              </li>
            </ul>
  
            <button 
              class="w-full bg-white text-indigo-600 py-3 px-6 rounded-lg font-semibold hover:bg-gray-100 transition-colors shadow-lg"
            >
              Passer à Pro
            </button>
          </div>
  
        </div>
  
        <!-- FAQ Section -->
        <div class="mt-20 max-w-3xl mx-auto">
          <h2 class="text-3xl font-bold text-gray-900 text-center mb-12">
            Questions fréquentes
          </h2>
          
          <div class="space-y-6">
            <div class="bg-white rounded-xl shadow-md p-6 border border-gray-100">
              <h3 class="text-lg font-semibold text-gray-900 mb-2">
                💳 Quels moyens de paiement acceptez-vous ?
              </h3>
              <p class="text-gray-600">
                Nous acceptons toutes les cartes bancaires (Visa, Mastercard, American Express) 
                via notre partenaire sécurisé Stripe.
              </p>
            </div>
  
            <div class="bg-white rounded-xl shadow-md p-6 border border-gray-100">
              <h3 class="text-lg font-semibold text-gray-900 mb-2">
                🔄 Puis-je changer d'abonnement ?
              </h3>
              <p class="text-gray-600">
                Oui ! Vous pouvez passer du plan Gratuit au plan Pro à tout moment, 
                ou annuler votre abonnement Pro sans frais.
              </p>
            </div>
  
            <div class="bg-white rounded-xl shadow-md p-6 border border-gray-100">
              <h3 class="text-lg font-semibold text-gray-900 mb-2">
                🎓 Y a-t-il des réductions pour les étudiants ?
              </h3>
              <p class="text-gray-600">
                Nous proposons une réduction de 50% pour les étudiants. 
                Contactez-nous avec votre carte étudiante pour en bénéficier.
              </p>
            </div>
          </div>
        </div>
  
      </div>
    </div>
  </template>
  
  <script setup>
  // Emits
  defineEmits(['navigate'])
  </script>