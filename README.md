# Projet S2 : Bataille Navale

![French_battleship_Brennus_NH_64443](img/French_battleship_Brennus_NH_64443.jpg)

## Présentation rapide 

Il va s'agir de développer une application cliente permettant de jouer 
en réseau à la bataille navale : https://fr.wikipedia.org/wiki/Bataille_navale_(jeu)

Un serveur de jeu est accessible en ligne sous la forme d'une API Rest
 (http://37.187.38.219/api/v0) et une librairie Java 
 interfaçant le serveur vous est fournie (en partie).


## Travail à faire

1. initialiser votre projet

* créer un git spécifique à votre groupe sur le gitlab de l'université : 
 https://gitlab.univ-nantes.fr/
 
* ajouter comme "developer" votre enseignant-référent et M. Arnaud Lanoix    

* déposer sur votre git, l'ensemble des sources récupérées ici :
https://gitlab.univ-nantes.fr/iut.info1.project/battleship-student-project-2021/-/archive/master/battleship-student-project-2021-master.zip

 **Vous respecterez la structure imposée en terme de dossiers/paquetages**
 
  NB :le source récupéré contient un repertoire lib/ 
     contenant une archive jar `battleship-library-1.x.jar`
     
     il faut ajouter ce .jar comme une librairie dans votre IDE préféré
     
     il s'agit de code qui vous est fourni, que vous devrez utiliser
      mais que vous ne pouvez pas modifier

2. il vous faut (terminer de) développer un modèle de données représentant 
une flotte de bateaux ; pour cela, il vous faut précisément implémenter 
les classes suivantes (dans `src/info1/ships/`) : 
    *  `Coord` 
    *  `Ship` et (si nécessaire) modifier les classes "filles" fournies
    *  `NavyFleet`

NB :des cas de tests écrit en Junit 5.4 vous 
permettront de valider votre implémentation.

![package ships](documentation/package_ships.png)

3. vous pouvez maintenant utiliser la classe `info1.network.Network` pour échanger avec le serveur, 
càd créer ou rejoindre une partie, puis jouer, càd effectuer un tir 
sur une coordonnée précise.

![package network](documentation/package_network.png)

Plus de détail sur l'usage de la classe Network par ![ici](documentation/HowtoUseNetwork.md)



## Remarques 

Chaque groupe aura un enseignant-référent qui passera
vous voir régulièrement afin de vous assister 
mais aussi évaluer votre travail.









Image issue de https://en.wikipedia.org/wiki/French_battleship_Brennus
