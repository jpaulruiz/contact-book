import { ref } from 'vue'
import { defineStore } from 'pinia'
import router from '@/router'
import axios from 'axios'

type RegistrationData = {
  username: string;
  password: string;
  confirmPassword: string;
};

type LogonData = {
  username: string;
  password: string;
};

const useAccount = defineStore('task', () => {
    const message = ref('')
    const success = ref(false)
    const data = ref(null)
    const authToken = ref('')
    const createAccount = async (e: RegistrationData) => {
      const formData = new URLSearchParams()
      formData.append('username', e.username)
      formData.append('password', e.password)
      formData.append('confirmPassword', e.confirmPassword)

      await fetch('http://localhost:9000/account-registration', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/x-www-form-urlencoded',
        },
        body: formData
      })
      .then(response => response.json())
      .then(data => {
        message.value = data.message
        if (message.value !== '') {
          const searchWord = /(successfully)/g
          if (message.value.search(searchWord) > -1) {
            success.value = true
          } else {
            success.value = false
          }
        }
      })
      .catch(error => {
        console.log(error)
      })
    }

    const logonAccount = async (e: LogonData) => {
      //header contains credentials: {"username":"admin","password":"admin","auth-token":""}
      const formData = new FormData()
      formData.append('username', e.username)
      formData.append('password', e.password)

      await axios.post('http://localhost:9000/account-logon', formData, {
        withCredentials: true
      })
      .then((data) => {
        message.value = data.data.message
        if (data.data.message !== '') {
          const searchWord = /(successfully)/g
          if (data.data.message.search(searchWord) > -1) {
            success.value = true
            router.push('/dashboard')
          } else {
            success.value = false
            router.push('/')
          }
        }
      })
      .catch((error) => {
        console.log(error)
      })
    }

    const logoutAccount = async () => {
      await axios.get('http://localhost:9000/account-logout', {
        withCredentials: true
      })
      .then((response) => {
        if (response.status == 200) router.push('/')
      })
      .catch((error) => {
        console.log(error)
      })
    }

    const createContact = async (e: any) => {
      const formData = new FormData()
      formData.append('firstName', e.firstName)
      formData.append('middleName', e.middleName)
      formData.append('lastName', e.lastName)
      formData.append('email', e.email)
      formData.append('phoneNumber', e.phoneNumber)
      formData.append('group', e.group)

      await fetch('http://localhost:9000/account-new', {
        method: 'POST',
        body: formData
      })
      .then(response => response.json())
      .then(res => {
        //redirect
        if (res.message !== '') {
          const searchWord = /(successfully)/g
          if (res.message.search(searchWord) > -1) {
            alert('Account has been created.')
            const socket = new WebSocket("ws://localhost:9000/ws")

            socket.addEventListener("open", (event) => {
              socket.send(JSON.stringify({ random: "just a random string trigger" }))
            })

            socket.addEventListener("message", (event) => {
              data.value = JSON.parse(event.data)
              socket.close()
            })

            router.push('/dashboard')
          } else {
            alert('Account could not be created.')
          }
        }
      })
      .catch(error => {
        console.log(error)
      })
    }

    // const getContacts = async () => {
    //   await fetch ('http://localhost:9000/account', {
    //     method: 'GET'
    //   })
    //   .then(response => response.json())
    //   .then(d => {
    //     data.value = d
    //   })
    //   .catch(error => {
    //     console.log(error)
    //   })
    // }

    // setInterval(async () => {
    //   await getContacts()
    // }, 500)
    
    const socket = new WebSocket("ws://localhost:9000/ws")

    socket.addEventListener("open", (event) => {
      socket.send(JSON.stringify({ random: "just a random string trigger" }))
    })

    socket.addEventListener("message", (event) => {
      data.value = JSON.parse(event.data)
      socket.close()
    })

    return {
      message,
      success,
      data,
      createAccount,
      logonAccount,
      logoutAccount,
      createContact
    }


})

export default useAccount
