# 🧠  QCM Generator – Application Web de Génération Automatique de QCM

QCM Generator est une application complète permettant de transformer un texte de cours ou un fichier PDF en un quiz interactif généré par IA.
L’objectif est d’aider les étudiants à réviser plus efficacement grâce à des questions pertinentes, corrigées automatiquement, et sauvegardées dans un historique.

Ce projet est à la fois **pédagogique et ludique**, et vise à faciliter la révision et l’apprentissage.

---

## 🚀 Fonctionnalités principales

✅ Génération de QCM
	•	📄 Upload de texte manuel
	•	📚 Upload de PDF avec extraction automatique (PDFBox)
	•	🤖 Génération intelligente via OpenAI (GPT-4o-mini)
	•	✅ 4 choix par question, 1 seule bonne réponse
	•	🎮 Quiz interactif entièrement jouable dans le navigateur
	•	🧠 Corrections affichées question par question
	•	🎯 Message personnalisé selon le score final
	
🕘 Historique complet
	•	💾 Sauvegarde automatique des QCM (localStorage)
	•	📜 Consultation des 10 derniers QCM
	•	📊 Statistiques de performance
	•	🔄 Rejouer un ancien QCM
	•	🗑️ Suppression d’une entrée de l’historique
	
⭐ Avis des utilisateurs
	•	⭐ Système de notation (1 à 5 étoiles)
	•	💬 Commentaires utilisateurs
	•	📈 Statistiques globales (note moyenne, nombre total d’avis)
	•	🗄️ Stockage PostgreSQL (données partagées entre tous les visiteurs)
---

## 🧩 Architecture du projet

qcm-generator/
├─ backend/   → Serveur Java Spring Boot (API REST)
├─ frontend/  → Interface Vue.js 3 + TailwindCSS
└─ README.md  → Documentation du projet

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

🌍 Déploiement
	•	Frontend déployé sur Vercel
	•	Backend & base de données déployés sur Render
	•	Communication sécurisée via API REST

🎯 Objectif du projet

Ce projet est conçu pour :
	•	aider les étudiants à réviser efficacement
	•	permettre la création automatique de QCM à partir de cours réels
	•	offrir un outil moderne, rapide et agréable à utiliser
	•	servir d’excellent portfolio full-stack

👨‍💻 Auteurs & Collaboration

Projet initié par [Oussou-3RV]




