<script setup>
import { onMounted, ref } from 'vue'

// Import des composants
import AppHeader from './components/AppHeader.vue'
import AppFooter from './components/AppFooter.vue'
import HomePage from './components/HomePage.vue'
import PricingPage from './components/PricingPage.vue'
import ContactPage from './components/ContactPage.vue'
import GeneratorPage from './components/GeneratorPage.vue'
import AboutPage from './components/AboutPage.vue'
import LoginPage from './components/LoginPage.vue'

import { isAuthenticated } from './services/authService'


const isLoggedIn = ref(false)

onMounted(() => {
  isLoggedIn.value = isAuthenticated
})
// Page actuelle
const currentPage = ref('home')

// Navigation entre les pages
const navigateTo = (page) => {
  // Si l'utilisateur veut aller sur le générateur sans être connecté
  if (page === 'generator' && !isLoggedIn.value) {
    currentPage.value = 'login' // Rediriger vers la page de connexion
    return
  }

  currentPage.value = page
  window.scrollTo({ top: 0, behavior: 'smooth' })
}

// Écouter les événements de navigation custom
document.addEventListener('navigate', (e) => {
  navigateTo(e.detail)
})
</script>

<template>
  <!-- Conteneur principal avec fond professionnel -->
  <div class="min-h-screen relative overflow-hidden bg-gradient-to-br from-slate-50 via-blue-50 to-indigo-50">

    <!-- Effets de lumière en arrière-plan -->
    <div
      class="fixed top-0 left-1/4 w-96 h-96 bg-gradient-to-br from-blue-200/20 to-indigo-200/20 rounded-full blur-3xl pointer-events-none">
    </div>
    <div
      class="fixed bottom-0 right-1/4 w-96 h-96 bg-gradient-to-tr from-purple-200/20 to-pink-200/20 rounded-full blur-3xl pointer-events-none">
    </div>

    <!-- Contenu principal -->
    <div class="relative z-10">

      <!-- HEADER -->
      <AppHeader :currentPage="currentPage" @navigate="navigateTo" />

      <!-- CONTENU DES PAGES -->
      <main class="min-h-screen">
        <!-- Page d'accueil -->
        <HomePage v-if="currentPage === 'home'" @navigate="navigateTo" />

        <!-- Page Fonctionnalités -->
        <!-- <FeaturesPage 
            v-if="currentPage === 'features'"
            @navigate="navigateTo"
          /> -->

        <!-- Page Tarifs -->
        <PricingPage v-if="currentPage === 'pricing'" @navigate="navigateTo" />

        <!-- Page À propos -->
        <AboutPage v-if="currentPage === 'about'" />

        <!-- Page Contact -->
        <ContactPage v-if="currentPage === 'contact'" @navigate="navigateTo" />

        <!-- Page Générateur -->
        <GeneratorPage v-if="currentPage === 'generator'" />

        <!-- Page de connexion -->
        <LoginPage v-if="currentPage === 'login' "/>

        <!-- <LoginPage v-if="currentPage === 'login' "/> -->
      </main>

      <!-- FOOTER -->
      <AppFooter @navigate="navigateTo" />

    </div>
  </div>
</template>

<style>
@import 'tailwindcss/base';
@import 'tailwindcss/components';
@import 'tailwindcss/utilities';

/* Smooth scroll */
html {
  scroll-behavior: smooth;
}

/* Amélioration des transitions */
* {
  transition-property: color, background-color, border-color, text-decoration-color, fill, stroke, opacity, box-shadow, transform;
  transition-timing-function: cubic-bezier(0.4, 0, 0.2, 1);
  transition-duration: 150ms;
}
</style>