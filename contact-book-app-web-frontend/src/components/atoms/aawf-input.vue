<template>
    <input
        v-bind="$attrs"
        :value="modelValue"
        @change="$emit('update:modelValue', $event.target.value)"
        >
</template>

<script setup lang="ts">
    defineProps({
        modelValue: {
            type: String,
            default: ''
        }
    })
    defineEmits([
        'update:modelValue'
    ])
</script>