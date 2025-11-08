// ========================================
// SERVICE DE GESTION DE L'HISTORIQUE
// Utilise localStorage pour sauvegarder les QCM
// ========================================

const STORAGE_KEY = 'qcm_history'
const MAX_HISTORY_ITEMS = 10 // Limiter à 10 QCM max

/**
 * Récupérer tout l'historique
 * @returns {Array} Liste des QCM sauvegardés
 */
export const getHistory = () => {
    try {
      const data = localStorage.getItem(STORAGE_KEY)
      return data ? JSON.parse(data) : []
    } catch (error) {
      console.error('Erreur lors de la lecture de l\'historique:', error)
      return []
    }
  }
  
  /**
 * Sauvegarder un nouveau QCM dans l'historique
 * @param {Object} quizData - Les données du quiz
 */
export const saveToHistory = (quizData) => {
    try {
      const history = getHistory()
      
      // Créer un ID unique
      const id = Date.now().toString()
      
      // Extraire un titre depuis les questions (premiers mots)
      const firstQuestion = quizData.questions[0]?.question || 'Quiz'
      const courseTitle = firstQuestion.substring(0, 50) + (firstQuestion.length > 50 ? '...' : '')
      
      // Créer l'objet à sauvegarder
      const historyItem = {
        id,
        date: new Date().toISOString(),
        courseTitle,
        questionsCount: quizData.questions.length,
        score: quizData.score,
        percentage: Math.round((quizData.score / quizData.questions.length) * 100),
        questions: quizData.questions,
        userAnswers: quizData.userAnswers
      }
      
      // Ajouter au début du tableau (plus récent en premier)
      history.unshift(historyItem)
      
      // Limiter à MAX_HISTORY_ITEMS
      const limitedHistory = history.slice(0, MAX_HISTORY_ITEMS)
      
      // Sauvegarder dans localStorage
      localStorage.setItem(STORAGE_KEY, JSON.stringify(limitedHistory))
      
      return historyItem
    } catch (error) {
      console.error('Erreur lors de la sauvegarde dans l\'historique:', error)
      return null
    }
  }

  /**
 * Supprimer un QCM de l'historique
 * @param {string} id - L'ID du QCM à supprimer
 */
export const deleteFromHistory = (id) => {
    try {
      const history = getHistory()
      const updatedHistory = history.filter(item => item.id !== id)
      localStorage.setItem(STORAGE_KEY, JSON.stringify(updatedHistory))
      return true
    } catch (error) {
      console.error('Erreur lors de la suppression:', error)
      return false
    }
  }

  /**
 * Récupérer un QCM spécifique par son ID
 * @param {string} id - L'ID du QCM
 * @returns {Object|null} Le QCM ou null
 */
export const getQuizById = (id) => {
    try {
      const history = getHistory()
      return history.find(item => item.id === id) || null
    } catch (error) {
      console.error('Erreur lors de la récupération du quiz:', error)
      return null
    }
  }
  
  /**
   * Vider tout l'historique
   */
  export const clearHistory = () => {
    try {
      localStorage.removeItem(STORAGE_KEY)
      return true
    } catch (error) {
      console.error('Erreur lors du nettoyage de l\'historique:', error)
      return false
    }
  }
  
