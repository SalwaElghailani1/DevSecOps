# DevSecOps
# TP DevSecOps : Analyse Automatique de la Qualité et Sécurité du Code

## Introduction
Ce travail pratique (TP) consiste à mettre en œuvre une approche DevSecOps de manière pratique.  
L’objectif principal est d’analyser automatiquement la qualité et la sécurité d’un code source en utilisant un pipeline CI/CD basé sur GitHub Actions et un conteneur SonarQube.  
Cette approche permet de détecter les problèmes de code dès les premières étapes du développement.

## Objectifs du TP
- Mettre un projet de code sous gestion de version avec **GitHub**.
- Mettre en place un pipeline **CI/CD** avec **GitHub Actions** et **Jenkins**.
- Tester automatiquement le code à chaque modification.
- Analyser la qualité et la sécurité du code avec **SonarQube**.
- Comprendre l’importance de l’intégration de la sécurité dans le cycle de développement.

---

## Environnement et Outils Utilisés
- **GitHub** pour le contrôle de version.
- **GitHub Actions** pour l’automatisation CI/CD.
- **Java JDK 17**
- **Maven** pour la compilation et les tests.
- **SonarQube** exécuté dans un conteneur Docker.
- **Jenkins** pour l’intégration continue et l’automatisation des builds.


---

## 1. Création et dépôt du code sur GitHub
Dans un premier temps, le code source du projet a été développé puis déposé sur un dépôt GitHub.  
L’utilisation de GitHub permet de centraliser le code et de déclencher automatiquement des actions à chaque modification.

> La figure ci-dessous montre le dépôt GitHub contenant le code source du projet utilisé dans ce TP.

---

## 2. Mise en place du pipeline GitHub Actions
Un workflow GitHub Actions a été configuré afin d’automatiser le build et l’analyse du projet.  
Ce pipeline est déclenché à chaque push sur la branche `main`.

Le pipeline comporte les étapes suivantes :

1. **Récupération du code source**  
   Le code est récupéré depuis le dépôt GitHub à l’aide de l’action `actions/checkout`.  
   L’option `fetch-depth: 0` permet une analyse plus complète par SonarQube.

2. **Configuration de l’environnement Java**  
   L’environnement de travail est configuré avec Java JDK 17, nécessaire pour compiler et tester le projet.

3. **Gestion du cache**  
   Les dépendances Maven ainsi que les fichiers SonarQube sont mis en cache afin d’optimiser le temps d’exécution du pipeline.

4. **Build et tests**  
   Le projet est compilé et les tests sont exécutés à l’aide de Maven.

5. **Analyse du code avec SonarQube**  
   Une analyse automatique est lancée afin de détecter les bugs, les vulnérabilités de sécurité et les mauvaises pratiques de code.  
   Les informations sensibles comme le token SonarQube sont stockées de manière sécurisée dans les secrets GitHub.

> La figure suivante présente le fichier de configuration GitHub Actions permettant d’automatiser le build et l’analyse du code.

### Exécution du pipeline
Après chaque modification du code et chaque push sur GitHub, le pipeline s’exécute automatiquement.  
L’exécution réussie du pipeline confirme que le code est compilable et que l’analyse de sécurité a été effectuée correctement.

---

## 3. Mise en place du pipeline CI/CD avec Jenkins
Dans cette partie, nous avons configuré un pipeline CI/CD en utilisant Jenkins comme serveur d'automatisation.  
Jenkins permet une intégration continue flexible et extensible, notamment via des plugins.

### 3.1 Installation et configuration de Jenkins
Jenkins a été installé et configuré pour gérer les builds automatiques.  
Le plugin GitHub Integration a été utilisé pour connecter Jenkins au dépôt GitHub.

### 3.2 Création d'un pipeline Jenkins
Un nouveau job de type Pipeline a été créé.  
Le pipeline est défini via un fichier `Jenkinsfile` qui décrit les étapes suivantes :
- Checkout du code source depuis GitHub.
- Build et tests avec Maven.
- Analyse SonarQube via le scanner intégré.
- Notification en cas de succès ou d'échec.

### 3.3 Intégration de SonarQube dans Jenkins
Le plugin SonarQube Scanner a été installé et configuré dans Jenkins.  
Les paramètres SonarQube (URL, token) ont été ajoutés dans la configuration globale de Jenkins.

### 3.4 Déclenchement automatique via Webhook
Un webhook GitHub a été configuré pour notifier Jenkins à chaque push sur la branche principale, déclenchant ainsi le pipeline automatiquement.

- Interface principale de Jenkins
- Configuration du pipeline Jenkins
- Contenu du Jenkinsfile
- Intégration de SonarQube dans Jenkins
- Exécution du pipeline Jenkins
  ![Jenkins Dashboard](static/images/2.png)
- Résultats SonarQube depuis Jenkins
  ![Résultats SonarQube](static/images/4.png)
---
E
## Conclusion
Ce TP nous a permis de mettre en œuvre une approche DevSecOps en utilisant deux outils d’intégration continue : GitHub Actions et Jenkins.  
L’automatisation de l’analyse de code avec SonarQube a montré son importance pour détecter rapidement les vulnérabilités et améliorer la qualité du code.

Les défis rencontrés concernaient principalement la configuration des tokens et des webhooks, mais ceux-ci ont été résolus avec une documentation adéquate.  
Cette expérience renforce l’importance de la sécurité intégrée dès les premières phases du développement.
