# QRServices

## Présentation générale

QRServices est une application hébergeant des services dynamiquement créés par les utilisateurs et accessibles par l'intermédiaire de QR-codes.

Le projet est développé en Java EE et nécessite un serveur Tomcat pour fonctionner.

## To-do liste

### Mise en place de la base de données
Installation d'une base mysql avec une connection par ORM Hibernate

### Réaliser un premier module
A cette fin, développer la logique et la vue nécessaire pour faire fonctionner l'un des modules possibles (compteur de like, sondage, livre d'or, redirection, ...)

### Réaliser une interface de management
L'interface doit permettre à un utilisateur d'ajouter/modifier/supprimer des modules parmi la liste proposée.

### Gestion des QRCodes
Etre capable de générer des QRCodes redirigeant vers des pages de services

### Gestion de l'authentification
Implémenter un login permettant d'identifier les créateurs de services