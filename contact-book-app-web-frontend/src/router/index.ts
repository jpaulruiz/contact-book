import { createRouter, createWebHistory } from 'vue-router'
import axios from 'axios'

const isAuthenticated = async () => {
  let res = false
  await axios.get("http://localhost:9000/authentication", {
    withCredentials: true
  })
  .then(response => {
    if (response) res = true
    else res = false
  })
  .catch(error => {
    console.error('Error:', error)
  })
  return res
}
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'logon-form',
      component: () => import('../components/pages/LoginView.vue')
    },
    {
      path: '/register-form',
      name: 'register-form',
      // route level code-splitting
      // this generates a separate chunk (About.[hash].js) for this route
      // which is lazy-loaded when the route is visited.
      component: () => import('../components/pages/RegisterView.vue')
    },
    {
      path: '/dashboard',
      name: 'dashboard',
      component: () => import('../components/pages/Dashboard.vue'),
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/:notFound',
      name: 'not-found',
      component: () => import('../components/pages/NotFound.vue')
    }
  ]
})

router.beforeEach(async (to, from, next) => {
  if (to.meta.requiresAuth) {
    if (await isAuthenticated()) {
      return next()
    } else {
      return next('/')
    }
  }

  if (to.name === 'logon-form' && await isAuthenticated()) {
    return next('/dashboard')
  }
  console.clear()
  return next()
})

export default router
