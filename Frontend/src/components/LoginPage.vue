<template>
    <div class="min-h-screen flex items-center justify-center py-12 px-4">
      <div class="max-w-md w-full bg-white rounded-2xl shadow-xl p-8">
        <h2 class="text-3xl font-bold text-center mb-8">
          Connexion
        </h2>
        
        <form @submit.prevent="handleLogin">
          <!-- Email -->
          <div class="mb-4">
            <label class="block text-sm font-medium text-gray-700 mb-2">
              Email
            </label>
            <input
              type="email"
              v-model="email"
              required
              class="w-full px-4 py-3 border border-gray-300 rounded-lg"
            />
          </div>
  
          <!-- Password -->
          <div class="mb-6">
            <label class="block text-sm font-medium text-gray-700 mb-2">
              Mot de passe
            </label>
            <input
              type="password"
              v-model="password"
              required
              class="w-full px-4 py-3 border border-gray-300 rounded-lg"
            />
          </div>
  
          <!-- Error message -->
          <div v-if="error" class="mb-4 p-3 bg-red-50 text-red-700 rounded-lg">
            {{ error }}
          </div>
  
          <!-- Submit button -->
          <button
            type="submit"
            :disabled="loading"
            class="w-full bg-gradient-to-r from-indigo-600 to-purple-600 text-white py-3 rounded-lg font-semibold"
          >
            {{ loading ? 'Connexion...' : 'Se connecter' }}
          </button>
        </form>
  
        <!-- Lien vers signup -->
        <p class="text-center mt-6 text-gray-600">
          Pas encore de compte ? 
          <button @click="$emit('switch-to-signup')" class="text-indigo-600 font-semibold">
            S'inscrire
          </button>
        </p>
      </div>
    </div>
  </template>
  
  <script setup>
  import { ref } from 'vue'
  import { login } from '../services/authService'
  
  const email = ref('')
  const password = ref('')
  const loading = ref(false)
  const error = ref('')
  
  const emit = defineEmits(['login-success', 'switch-to-signup'])
  
  const handleLogin = async () => {
    loading.value = true
    error.value = ''
    
    try {
      await login(email.value, password.value)
      emit('login-success')
    } catch (err) {
      error.value = err.response?.data?.message || 'Erreur de connexion'
    } finally {
      loading.value = false
    }
  }
  </script>