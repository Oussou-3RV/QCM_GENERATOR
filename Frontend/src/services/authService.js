import axios from 'axios'

const API_URL = import.meta.env.VITE_API_URL

// Signup
export const signup = async (email, password, name) => {
  const response = await axios.post(`${API_URL}/api/auth/signup`, {
    email,
    password,
    name
  })
  
  // Stocker le token
  if (response.data.token) {
    localStorage.setItem('token', response.data.token)
    localStorage.setItem('user', JSON.stringify(response.data.user))
  }
  
  return response.data
}

// Login
export const login = async (email, password) => {
  const response = await axios.post(`${API_URL}/api/auth/login`, {
    email,
    password
  })
  
  if (response.data.token) {
    localStorage.setItem('token', response.data.token)
    localStorage.setItem('user', JSON.stringify(response.data.user))
  }
  
  return response.data
}

// Logout
export const logout = () => {
  localStorage.removeItem('token')
  localStorage.removeItem('user')
}

// Vérifier si connecté
export const isAuthenticated = () => {
  return !!localStorage.getItem('token')
}

// Récupérer l'utilisateur actuel
export const getCurrentUser = () => {
  const user = localStorage.getItem('user')
  return user ? JSON.parse(user) : null
}