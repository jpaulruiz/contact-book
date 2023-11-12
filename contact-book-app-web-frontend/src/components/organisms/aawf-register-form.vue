<template>
    <form 
        class="form-class"
        name="register-form" 
        method="POST" 
        @submit.prevent="formSubmit(newData)"
        aria-labelledby="createAccountForm"
        aria-describedby="This is a form for creating new account">
            <aawf-label-input 
                input-type="text"
                input-name="Username"
                aria-label="usernameInputField"
                aria-described="Input field for username"
                :required=true
                @update:model-value="e => newData.username = e" />
            <aawf-label-input 
                input-type="password"
                input-name="Password"
                aria-label="passwordInputField"
                aria-described="Input field for password"
                :required=true
                @update:model-value="e => newData.password = e" />
            <aawf-label-input 
                input-type="password"
                input-name="Confirm Password"
                aria-label="repeatPasswordInputField"
                aria-described="Repeat password for validation"
                :required=true
                @update:model-value="e => newData.confirmPassword = e" />
            <aawf-button
                type="submit"
                id="form-submit"
                name="form-submit"
                aria-label="addButton"
                aria-described="Button for form submission">
                Register
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
        username: '',
        password: '',
        confirmPassword: ''
    }
    
</script>

<style scoped>
    button {
        margin-top: 0.4rem;
    }
</style>