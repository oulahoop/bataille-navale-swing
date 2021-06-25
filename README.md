# Projet S2 : Bataille Navale

![French_battleship_Brennus_NH_64443](img/French_battleship_Brennus_NH_64443.jpg)

## Présentation rapide 

Notre projet conciste a développer une application cliente permettant de jouer 
en réseau à la bataille navale : https://fr.wikipedia.org/wiki/Bataille_navale_(jeu)

Un serveur de jeu nous est accessible en ligne sous la forme d'une API Rest
 (http://37.187.38.219/api/v0) et une librairie Java 
 interfaçant le serveur vous est fournie (en partie).

Notre equipe est composée de 4 membres : 

   - ADAM Jérémy
   - BERVOAS Nicolas
   - BROIS Sylvain
   - CAUBERE Maël

## Travail à faire

### initialisation du projet

   Le depot Git porte le nom suivant : projetS2-2021-19-JFB

### implémentation de `ships.*`

   Nous avons fini l'implémentation des differenes classes du paquage 'ships'

![package ships](documentation/package_ships.png)

### utilisation de `info1.network.Network` ...

Nous avons uttilisé la librairie 'Network' qui nous est fournie afin de : 
   - initialiser un joueur
   - initialiser une partie
   - rejoindre une partie 
   - Jouer un tour en effectuant un tir

![package network](documentation/package_network.png)

Plus de détail sur l'usage de la classe Network dans ![HowToUseNetwork](documentation/HowtoUseNetwork.md).

### ... et développement d'une interface graphique

Développez une interface graphique en Swing vous permettant de jouer


### Schéma de la construction des différentes vues

#### Ecran de bienvenue 
<img src="https://imgur.com/EafB1qL.png">

#### Ecran de gestion de flotte et de création de joueur
<img src="https://imgur.com/HYXGRb0.png">

#### Ecran de recherche de partie ou de création de partie
<img src="https://imgur.com/YR7AkeT.png">

#### Ecran d'attente de joueur lorsque l'on créer une partie
<img src="https://imgur.com/lIwVGwZ.png">

#### Ecran de jeu 
<img src="https://imgur.com/eDNsVUI.png">






_Image du bateau issue de https://en.wikipedia.org/wiki/French_battleship_Brennus_
