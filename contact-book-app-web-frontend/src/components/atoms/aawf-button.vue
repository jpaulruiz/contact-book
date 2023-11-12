<template>
    <button
        v-bind="$attrs"
        @click="$emit('update:modelValue', $event.target.id)"
        >
        <slot></slot>
    </button>
</template>

<script setup lang="ts">
    defineProps({       
        ariaLabel: {
            type: String,
            default: ''
        },
        ariaDescribed: {
            type: String,
            default: ''
        }
    })

    defineEmits([
        'update:modelValue'
    ])
</script>