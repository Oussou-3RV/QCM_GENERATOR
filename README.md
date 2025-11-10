# 🧠 QCM Generator – Application Web de Génération Automatique de QCM

QCM Generator est une application complète permettant de transformer un texte de cours ou un fichier PDF en un quiz interactif généré par IA.  
L’objectif est d’aider les étudiants à réviser plus efficacement grâce à des questions pertinentes, corrigées automatiquement et sauvegardées dans un historique.

Ce projet est à la fois pédagogique et ludique, et vise à faciliter la révision et l’apprentissage.

---

## 🚀 Fonctionnalités principales

### ✅ Génération de QCM
• 📄 Upload de texte manuel  
• 📚 Upload de PDF avec extraction automatique (PDFBox)  
• 🤖 Génération intelligente via OpenAI (GPT-4o-mini)  
• ✅ 4 choix par question, 1 seule bonne réponse  
• 🎮 Quiz interactif dans le navigateur  
• 🧠 Corrections question par question  
• 🎯 Message personnalisé selon le score  

### 🕘 Historique des QCM
• 💾 Sauvegarde automatique (localStorage)  
• 📜 Consultation des 10 derniers QCM  
• 📊 Statistiques de performance  
• 🔄 Rejouer un ancien QCM  
• 🗑️ Suppression d’une entrée  

### ⭐ Avis utilisateurs
• ⭐ Système de notation (1 à 5 étoiles)  
• 💬 Commentaires  
• 📈 Statistiques globales (note moyenne, nombre d’avis)  
• 🗄️ Stockage PostgreSQL (données partagées entre tous les visiteurs)

---

## 🧩 Architecture du projet

qcm-generator/
│
├── backend/                    # Spring Boot API
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/qcm/
│   │   │   │   ├── controller/    # REST Controllers
│   │   │   │   ├── service/       # Business Logic
│   │   │   │   ├── model/         # JPA Entities
│   │   │   │   ├── repository/    # Data Access
│   │   │   │   └── config/        # Configuration
│   │   │   └── resources/
│   │   │       └── application.properties
│   │   └── test/
│   ├── pom.xml
│   └── Dockerfile
│
├── frontend/                   # Vue.js 3 App
│   ├── src/
│   │   ├── components/        # Vue Components
│   │   ├── views/             # Page Views
│   │   |
│   │   ├── services/          # API Services
│   │   ├── assets/            # Images, CSS
│   │   └── App.vue
│   ├── public/
│   ├── package.json
│   ├── vite.config.js
│   └── tailwind.config.js
│
└── README.md

## 🖥️ Technologies utilisées

🎨 Frontend
	•	Vue.js 3 (Composition API)
	•	TailwindCSS
	•	Axios
	•	localStorage
	•	Hébergé sur Vercel

🔧 Backend
	•	Java 17
	•	Spring Boot
	•	Spring Data JPA
	•	REST API
	•	OpenAI API (GPT-4o-mini)
	•	Apache PDFBox (extraction PDF)
	•	PostgreSQL
	•	Hébergé sur Render

---

## 🛠️ Installation & Exécution
```bash
📌 Backend (Spring Boot)
	1.	Cloner le projet
		git clone https://github.com/<TON-REPO>
		cd Backend
	2.	Configurer les variables d’environnement :
		DATABASE_URL=jdbc:postgresql://dpg-xxx
		OPENAI_API_KEY=sk-xxxxx
	3.	Lancer le serveur :
		mvn spring-boot:run

🎨 Frontend (Vue.js)
	1.	Aller dans le dossier frontend :
		cd Frontend
	2.	Installer les dépendances :
		npm install
	3.	Configurer les fichiers .env :
		VITE_API_URL=http://localhost:8080
	4.	Lancer le serveur local :
		npm run dev
```
🌍 Déploiement

• Frontend déployé sur Vercel
• Backend + base de données déployés sur Render
• Communication sécurisée via API REST

🎯 Objectif du projet

Ce projet est conçu pour :
• aider les étudiants à réviser efficacement
• créer automatiquement des QCM à partir de cours réels
• offrir un outil moderne et agréable
• servir de portfolio full-stack professionnel

👨‍💻 Auteurs & Collaboration

Projet initié par [Oussou-3RV]




