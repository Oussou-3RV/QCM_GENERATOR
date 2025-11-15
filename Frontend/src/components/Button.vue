<template>
    <button
          @click="$emit('click')"
          :class="[
            'flex-1 py-3 px-4 rounded-lg font-semibold transition-all text-sm md:text-base',
            active
              ? 'bg-indigo-600 text-white shadow-md'
              : 'text-gray-600 hover:bg-gray-100'
          ]"
        >
        <span v-if="icon">{{ icon }}</span>
        {{ label }}
        </button>
</template>

<script setup>
defineProps({
    label: {type: String, required: true},
    icon: {type: String, default: " "},
    active: {type: Boolean, default: false}
})
</script>

