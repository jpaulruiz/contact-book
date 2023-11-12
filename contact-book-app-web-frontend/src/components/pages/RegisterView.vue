<template>
    <top-level-view>
        <div class="wrapper">
            <div class="container">
                <h2>REGISTRATION</h2>
                <aawf-register-form 
                    :form-submit="acc.createAccount">
                    <span :class="acc.success ? 'green' : 'red'">
                        {{ acc.message }}
                    </span>
                    Already have an account? Login <router-link to="/">here</router-link>
                </aawf-register-form>
            </div>
        </div>
    </top-level-view>
</template>

<script setup lang="ts">
    import TopLevelView from '../templates/top-level-view.vue'
    import AawfRegisterForm from '../organisms/aawf-register-form.vue'
    import useAccount from '../../stores/account.ts'

    const acc = useAccount()
    //reset everything
    acc.message = ''
    acc.success = false
</script>

<style scoped>
    .red {
        color: #cc0000;
    }

    .green {
        color: #4bb543;
    }

    h2 {
        text-align: center;
        letter-spacing: 2px;
    }

    .container {
        padding: 1rem;
        background: rgba(240, 240, 240, 1);
        border-radius: 16px;
        box-shadow: 0 0 2px rgba(0, 0, 0, 0.1);
        backdrop-filter: blur(5px);
        border: 1px solid rgba(240, 240, 240, 0.3);
    }

    .wrapper {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
        height: 92vh;
    }
</style>