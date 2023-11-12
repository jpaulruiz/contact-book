<template>
    <aawf-label 
        :id="labelId"
        :name="inputName"/>
    <aawf-input 
        :type="inputType" 
        :name="inputName"
        :aria-label="ariaLabel"
        :aria-describedby="ariaDescribed"
        :model-value="modelValue"
        :required="$attrs.required"
        @update:model-value="$emit('update:modelValue', $event)"/>
</template>

<script setup lang="ts">
    import AawfLabel from '../atoms/aawf-label.vue'
    import AawfInput from '../atoms/aawf-input.vue'

    defineProps({
        inputType: {
            type: String,
            default: 'text'
        },
        inputName: {
            type: String,
            default: 'input-name'
        },
        modelValue: {
            type: String,
            default: ''
        },
        labelId: {
            type: String,
            default: '0'
        },
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