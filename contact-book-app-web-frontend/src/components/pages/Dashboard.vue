<template>
    <top-level-view>
        <h2>Dashboard</h2>
        <section>
            <aawf-button 
                @update:model-value="showModal = true">
                New Contact
            </aawf-button>
            <aawf-button
                @update:model-value="acc.logoutAccount">
                Logout
            </aawf-button>
        </section>
        <section>
            <Teleport to="body">
                <aawf-modal :show="showModal" @close="showModal = false">
                    <template #header>
                        <h3>Add new contact</h3>
                    </template>
                    <template #body>
                        <aawf-contact-form 
                            :form-submit="acc.createContact" 
                        />
                    </template>
                </aawf-modal>
            </Teleport>
        </section>
        <div class="wrapper">
            <div v-for="d in acc.data" class="contact-wrapper">
                <div>First Name: {{ d.firstName }}</div>
                <div>Middle Name: {{ d.middleName }}</div>
                <div>Last Name: {{ d.lastName }}</div>
                <div>Phone Number: {{ d.phoneNumber }}</div>
                <div>Email: {{ d.email }}</div>
                <div>Group: {{ d.group }}</div>
            </div>
        </div>
    </top-level-view>
</template>

<script setup lang="ts">
    import TopLevelView from '../templates/top-level-view.vue'
    import AawfContactForm from '../organisms/aawf-contact-form.vue'
    import AawfModal from '../molecules/aawf-modal.vue'
    import AawfButton from '../atoms/aawf-button.vue'
    import useAccount from '../../stores/account.ts'
    import { ref } from 'vue'

    const acc = useAccount()

    const showModal = ref(false)

</script>

<style scoped>
    .wrapper {
        display: flex;
        flex-direction: column;
    }

    h2 {
        text-align: center;
    }

    section {
        display: flex;
        justify-content: center;
    }

    .contact-wrapper {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: center;
    }

</style>