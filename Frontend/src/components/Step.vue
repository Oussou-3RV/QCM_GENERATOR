<template>
    <div class="relative">
        <div class="bg-white rounded-2xl p-8 shadow-lg hover:shadow-xl transition-shadow">
            <div :class="['w-12 h-12 rounded-full flex items-center justify-center font-bold text-xl mb-6', badgeClass]">
                {{ numero }}
            </div>
            <h3 class="text-xl font-bold text-gray-900 mb-3">{{ titre }}</h3>
            <p class="text-gray-600">
                {{ description }}
            </p>
        </div>
        <!-- Flèche (desktop) -->
        <div v-if="flecheNext" class="hidden md:block absolute top-1/2 -right-8 transform -translate-y-1/2">
            <svg class="w-8 h-8 text-indigo-600" fill="none" stroke="currentColor" viewBox="0 0 24 24">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M9 5l7 7-7 7"></path>
            </svg>
        </div>
    </div>
</template>

<script setup>
defineProps({
    numero: Number,
    titre: String,
    description: String,
    badgeClass: String,
    flecheNext : Boolean
})
</script>

<style lang="scss" scoped></style>