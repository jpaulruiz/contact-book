<template>
    <component :is="tag">
        <slot></slot>
    </component>
</template>

<script setup lang="ts">
    defineProps({
        tag: {
            type: String,
            default: 'li',
            validator(value: string) {
                return ['li', 'ul', 'ol', 'a'].includes(value)
            }
        }
    })
</script>