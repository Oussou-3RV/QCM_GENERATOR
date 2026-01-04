<template>
    <!-- Header fixe du site web -->
    <header class="fixed top-0 left-0 right-0 z-50 bg-white/95 backdrop-blur-lg border-b border-gray-200 shadow-sm ">
      <div class="max-w-7xl mx-auto px-4 sm:px-6 lg:px-8 ">
        <div class="flex items-center justify-between h-20">
          
          <!-- Logo et nom du site -->
          <div class="flex items-center cursor-pointer group hover:scale-105 transition-all" @click="$emit('navigate', 'home')">
            <!-- Logo -->
            <div>
                <i class="fa-solid fa-hexagon-nodes fa-2xl" style="color: #6600ff;"></i>
            </div>
            
            <!-- Nom du site -->
            <h1 class="text-2xl font-bold bg-gradient-to-r from-indigo-700 to-purple-600 bg-clip-text text-transparent">
                QCM Generator
            </h1>
          </div>
  
          <!-- Navigation principale (desktop) -->
          <nav class="hidden md:flex items-center gap-1">
            <a 
              v-for="item in menuItems" 
              :key="item.id"
              @click="$emit('navigate', item.id)"
              :class="[
                'px-4 py-2 rounded-lg font-medium text-sm transition-all cursor-pointer',
                currentPage === item.id 
                  ? 'bg-indigo-50 text-indigo-700' 
                  : 'text-gray-700 hover:bg-gray-50 hover:text-indigo-600'
              ]"
            >
              {{ item.label }}
            </a>
          </nav>
  
          <!-- CTA Button (Call To Action) -->
          <div class="hidden md:flex items-center gap-3">
            <button 
              @click="$emit('navigate', 'generator')"
              class="bg-gradient-to-r from-indigo-600 to-purple-600 text-white px-6 py-2.5 rounded-lg font-semibold shadow-md hover:shadow-lg hover:scale-105 transition-all"
            >
              Créer un QCM
            </button>
          </div>
  
          <!-- Menu hamburger (mobile) -->
          <button 
            @click="mobileMenuOpen = !mobileMenuOpen"
            class="md:hidden p-2 rounded-lg hover:bg-gray-100 transition-colors"
          >
            <svg class="w-6 h-6 text-gray-700" fill="none" stroke="currentColor" viewBox="0 0 24 24">
              <path v-if="!mobileMenuOpen" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M4 6h16M4 12h16M4 18h16"></path>
              <path v-else stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M6 18L18 6M6 6l12 12"></path>
            </svg>
          </button>
        </div>
      </div>
  
      <!-- Menu mobile -->
      <transition
        enter-active-class="transition ease-out duration-200"
        enter-from-class="opacity-0 -translate-y-2"
        enter-to-class="opacity-100 translate-y-0"
        leave-active-class="transition ease-in duration-150"
        leave-from-class="opacity-100 translate-y-0"
        leave-to-class="opacity-0 -translate-y-2"
      >
        <div v-if="mobileMenuOpen" class="md:hidden border-t border-gray-200 bg-white">
          <div class="px-4 py-4 space-y-2">
            <a 
              v-for="item in menuItems" 
              :key="item.id"
              @click="handleMobileNavigation(item.id)"
              :class="[
                'block px-4 py-3 rounded-lg font-medium text-sm transition-all cursor-pointer',
                currentPage === item.id 
                  ? 'bg-indigo-50 text-indigo-700' 
                  : 'text-gray-700 hover:bg-gray-50'
              ]"
            >
              {{ item.label }}
            </a>
            <button 
              @click="handleMobileNavigation('generator')"
              class="w-full bg-gradient-to-r from-indigo-600 to-purple-600 text-white px-4 py-3 rounded-lg font-semibold shadow-md"
            >
              Créer un QCM
            </button>
          </div>
        </div>
      </transition>
    </header>
  
    <!-- Espaceur pour compenser le header fixe -->
    <div class="h-20"></div>
  </template>
  
  <script setup>
  import { ref } from 'vue'
  
  // Props
  const props = defineProps({
    currentPage: {
      type: String,
      default: 'home'
    }
  })
  
  // Emits
  defineEmits(['navigate'])
  
  // State
  const mobileMenuOpen = ref(false)
  
  // Menu items
  const menuItems = [
    { id: 'home', label: 'Accueil' },
    { id: 'features', label: 'Fonctionnalités' },
    { id: 'pricing', label: 'Tarifs' },
    { id: 'about', label: 'À propos' },
    { id: 'contact', label: 'Contact' }
  ]
  
  // Fonction pour gérer la navigation mobile
  const handleMobileNavigation = (page) => {
    mobileMenuOpen.value = false
    // Petit délai pour l'animation
    setTimeout(() => {
      document.dispatchEvent(new CustomEvent('navigate', { detail: page }))
    }, 150)
  }
  </script>
  
  <style scoped>
  /* Ajout d'une ombre au scroll */
  header {
    transition: box-shadow 0.3s ease;
  }
  
  header:hover {
    box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.05);
  }
  </style>