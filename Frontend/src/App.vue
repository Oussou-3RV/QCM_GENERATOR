<script setup>
import { ref } from 'vue'

const message = ref('Bienvenue sur QCM Generator ! 🚀')
</script>

<template>
  <div class="min-h-screen bg-gradient-to-br from-blue-50 to-indigo-100 flex items-center justify-center">
    <div class="bg-white rounded-2xl shadow-xl p-8 max-w-2xl">
      <h1 class="text-4xl font-bold text-indigo-900 mb-4">
        📚 QCM Generator
      </h1>
      <p class="text-xl text-gray-700">
        {{ message }}
      </p>
    </div>
  </div>
</template>