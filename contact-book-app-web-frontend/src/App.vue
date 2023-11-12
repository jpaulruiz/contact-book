<script setup lang="ts">
import { RouterLink, RouterView } from 'vue-router'
</script>

<template>
  <header>
    <h1>Contact Book App</h1>
    <div class="wrapper">
      <!-- <nav>
        <RouterLink to="/">Logon Form</RouterLink>
        <RouterLink to="/register-form">Register Form</RouterLink>
      </nav> -->
    </div>
  </header>
  <section>
    <RouterView />
  </section>
</template>

<style scoped>
  header {
    display: block;
    position: relative;
    top: 0;
    text-align: center;
    background-color: #f0f0f0;
    padding: 10px;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    height: 8vh;
  }

  h1 {
    font-size: 1.5rem;
    font-weight: bold;
  }

  nav {
    display: flex;
    flex-direction: column;
    width: 20vw;
  }
</style>
