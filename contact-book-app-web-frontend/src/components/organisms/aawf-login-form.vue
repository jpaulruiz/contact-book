<template>
    <form 
        class="form-class"
        name="login-form" 
        method="POST" 
        @submit.prevent="formSubmit(newData)"
        aria-labelledby="loginAccountForm"
        aria-describedby="Login form">
            <aawf-label-input 
                input-type="text"
                input-name="Username"
                aria-label="usernameInputField"
                aria-described="Input field for username"
                required
                @update:model-value="e => newData.username = e" />
            <aawf-label-input 
                input-type="password"
                input-name="Password"
                aria-label="passwordInputField"
                aria-described="Input field for password"
                required
                @update:model-value="e => newData.password = e" />
            <aawf-button
                type="submit"
                id="form-submit"
                name="form-submit"
                aria-label="addButton"
                aria-described="Button for form submission">
                Login
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
        password: ''
    }
    
</script>

<style scoped>
    button {
        margin-top: 0.4rem;
    }
</style>