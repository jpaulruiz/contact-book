<template>
    <form 
        class="form-class"
        name="contact-form"
        method="POST"
        @submit.prevent="formSubmit(newData)"
        aria-labelledby="uploadForm"
        aria-describedby="upload form">
            <aawf-label-input 
                input-type="text"
                input-name="First Name"
                aria-label=""
                aria-described=""
                @update:model-value="e => newData.firstName = e" />
            <aawf-label-input 
                input-type="text"
                input-name="Middle Name"
                aria-label=""
                aria-described=""
                @update:model-value="e => newData.middleName = e" />
            <aawf-label-input 
                input-type="text"
                input-name="Last Name"
                aria-label=""
                aria-described=""
                @update:model-value="e => newData.lastName = e" />
            <aawf-label-input 
                input-type="text"
                input-name="Phone Number"
                aria-label=""
                aria-described=""
                @update:model-value="e => newData.phoneNumber = e" />
            <aawf-label-input 
                input-type="email"
                input-name="Email"
                aria-label=""
                aria-described=""
                @update:model-value="e => newData.email = e" />
            <aawf-label-input 
                input-type="text"
                input-name="Group"
                aria-label=""
                aria-described=""
                @update:model-value="e => newData.group = e" />
            <aawf-button
                type="submit"
                id="form-submit"
                name="form-submit"
                aria-label="addButton"
                aria-described="Button for form submission">
                Add Contact
            </aawf-button>
            <slot></slot>
    </form>
</template>

<script setup lang="ts">
    import AawfLabelInput from '../molecules/aawf-label-input.vue'
    import AawfButton from '../atoms/aawf-button.vue'

    defineProps({
        formSubmit: {
            type: Function,
            default() {
                return 'Default Function'
            }
        }
    })

    const newData = {
        firstName: '',
        middleName: '',
        lastName: '',
        phoneNumber: '',
        email: '',
        group: ''
    }
</script>

<style scoped>
    button {
        margin-top: 0.4rem;
    }
</style>