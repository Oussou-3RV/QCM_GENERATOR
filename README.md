# 🧠 Générateur de QCM à partir d’un cours

## 🎯 Objectif du projet
Ce projet a pour but de créer une **application web** capable de **générer automatiquement des QCM (Questions à Choix Multiples)** à partir d’un texte de cours fourni par l’utilisateur.

L’application permet à l’utilisateur de :
1. Coller ou importer un texte de cours 📚  
2. Générer automatiquement plusieurs questions à choix multiples ✍️  
3. Répondre aux questions dans l’interface web ✅  
4. Obtenir son **score final** ou afficher la **bonne réponse après chaque question**  

Ce projet est à la fois **pédagogique et ludique**, et vise à faciliter la révision et l’apprentissage.

---

## ⚙️ Fonctionnalités (MVP)
**MVP (Minimum Viable Product)** — version minimale mais fonctionnelle du projet :

1. L’utilisateur colle un texte de cours dans un champ.
2. Le backend (Java) découpe le texte et génère **3 questions aléatoires**.
3. Chaque question a **4 propositions**, dont une correcte.
4. L’utilisateur choisit ses réponses.
5. Le site affiche le **score final**.

---

## 🧩 Architecture du projet

qcm-generator/
├─ backend/   → Serveur Java Spring Boot (API REST)
├─ frontend/  → Interface Vue.js 3 + TailwindCSS
└─ README.md  → Documentation du projet

## 🖥️ Technologies utilisées

| Composant  | Technologie | Description |
|------------|--------------|--------------|
| **Frontend** | Vue.js 3 + TailwindCSS | Interface utilisateur moderne et responsive |
| **Backend** | Java + Spring Boot | API REST qui traite le texte et génère les QCM |
| **Communication** | Axios (HTTP + JSON) | Envoi du texte au backend et réception des questions |
| **Langage principal** | JavaScript / Java | Pour le développement complet du projet |

---

## 🚀 Installation et mise en route

1️⃣ Cloner le projet
```bash
  git clone https://github.com/<Oussou-3RV>/qcm-generator.git
  cd qcm-generator
2️⃣ Créer les dossiers
  mkdir backend frontend
3️⃣ Backend (Spring Boot)

Dans le dossier backend, tu mettras le code Java suivant :
	•	QcmGeneratorApplication.java
	•	QcmController.java
	•	QcmService.java
	•	Question.java

Le backend écoute sur http://localhost:8080.

4️⃣ Frontend (Vue.js)

Dans le dossier frontend :
  npm create vue@latest
  npm install axios tailwindcss
  npx tailwindcss init -p
Puis copie le contenu de App.vue fourni par le projet pour :
	•	Coller ton texte
	•	Générer les QCM
	•	Répondre et voir ton score

Le frontend écoute sur http://localhost:5173.

🔮 Prochaines étapes

Étape                 |           Description
1. Base de données         Sauvegarder les QCM et les résultats des utilisateurs
2. IA / NLP                Utiliser un modèle IA (OpenAI, HuggingFace, etc.) pour générer des questions plus intelligentes
3. Interface avancée       Améliorer le design et ajouter un mode “révision” ou “statistiques”
4. Authentification        Permettre à chaque utilisateur d’avoir son espace personnel

👨‍💻 Auteurs & Collaboration

Projet initié par [Oussou-3RV]

Conçu pour expérimenter :
	•	Le développement frontend avec Vue.js
	•	Le développement backend avec Spring Boot
	•	Les bases du traitement automatique du langage (NLP)




