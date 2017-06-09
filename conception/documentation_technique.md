#QRServices



## Présentation générale

QRServices est une application web permettant l'hébergement de services dynamiquements créés par des utilisateurs pouvant être distribués par l'intermédiaire de QR-codes.

Les services mis à disposition dans QRServices sont :
- Un compteur de like
- Un livre d'or
- Une inscription de newsletter

Les utilisateurs peuvent accéder à une interface de management permettant de gérer leurs services.



## Installation du projet

### Base MySQL

Afin de déployer QRServices, une base MySQL est nécessaire.
Par défaut, l'application essaiera d'atteindre la base locale avec le port 3306, en utilisant l'utilisateur "root" avec un mot de passe vide.
L'utilisateur doit avoir accès en lecture et en écriture à une base "qrservices", dont la structure des tables est livrée dans le fichier SQL de l'archive.
Il est possible de modifier cette configuration en éditant le fichier "hibernate.cfg.xml" présent dans l'artefact.

### Déploiement de l'application web

Un serveur tomcat doit être configuré et fonctionnel au préalable.
Une fois celui-ci prêt, y déployer l'artefact war (le placer dans le dossier webapps).



## Structure du projet

### Stack du projet

L'application web est une application Java EE configurée sous Maven (L'archive WAR a été générée à partir de Maven) et prévue pour un serveur Tomcat 8. La partie front repose sur les servlet ainsi que JSP ; la couche de persistance utilise Hibernate en tant qu'ORM, et la base est en MySQL. 

### Architecture de l'application

Le projet utilise une architecture en trois couches qui correspondent à la présentation, au traitement et à l'accès des données. Chacune de ces couches est localisée dans un package.

L'accès aux données est situé dans le package "com.lefonddeletang.qrservices.model". Il utilise Hibernate pour la persistance en base de données et est séparé en un package de classe utilitaire, un package de JavaBean désignant les modèles des objets, et un package contenant les méthodes de mise à jour CRUD des entrées en base. Pour fonctionner, le model a besoin du fichier de configuration hibernate.cfg.xml.

Le traitement n'est composé que du package "com.lefonddeletang.qrservices.controller" et contient la logique reliant la présentation à l'accès aux données.

La présentation, présente dans le package "com.lefonddeletang.qrservices.view", est composée de servlets dans un package à cet effet ainsi que d'une classe filtrant les URL d'accès aux services. De plus, les pages JSP ainsi que les ressources JS/CSS accessibles epuis le web sont présentes dans le dossier src/main/webapp.

### Contribution au projet

Le projet est versionné sur Github disponible via le lien suivant : "https://github.com/zessirb/QRServices". N'hésitez pas à nous proposez des Pull Request :)

### Utilisation du projet

Pour obtenir plus de détails sur l'utilisation de l'application web et des QR-codes, se réferrer à la documentation utilisateur.
