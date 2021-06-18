# Projet S2 : Bataille Navale

![French_battleship_Brennus_NH_64443](img/French_battleship_Brennus_NH_64443.jpg)

## Présentation rapide 

Il va s'agir de développer une application cliente permettant de jouer 
en réseau à la bataille navale : https://fr.wikipedia.org/wiki/Bataille_navale_(jeu)

Un serveur de jeu est accessible en ligne sous la forme d'une API Rest
 (http://37.187.38.219/api/v0) et une librairie Java 
 interfaçant le serveur vous est fournie (en partie).


## Travail à faire

### initialisation du projet

1. créer un git spécifique à votre groupe sur le gitlab de l'université : 
 https://gitlab.univ-nantes.fr/
 
2. ajouter comme "maintainer" votre enseignant-référent et M. Arnaud Lanoix    

3. déposer sur votre git, l'ensemble des sources récupérées ici :
https://gitlab.univ-nantes.fr/iut.info1.project/battleship-student-project-2021/-/archive/master/battleship-student-project-2021-master.zip

 **Vous respecterez la structure imposée en terme de dossiers/paquetages**
 
  NB :le source récupéré contient un repertoire lib/ 
     contenant une archive jar `battleship-library-1.x.jar`
     
  il faut ajouter ce .jar comme une librairie dans votre IDE préféré
     
  il s'agit de code qui vous est fourni, que vous devrez utiliser
  mais que vous ne pouvez pas modifier


### implémentation de `ships.*`

2. il vous faut (terminer de) développer le modèle de données représentant 
la flotte de bateaux ; pour cela, il vous faut coder 
les classes suivantes (dans `src/info1/ships/`) : 
    *  `Coord` implémentant `ICoord`
    *  `Ship` implémentant `IShip` et (si nécessaire) modifier les classes "filles" fournies
    *  `NavyFleet` implémentant `INavyFleet`
    
Les interfaces à implémenter sont présentes dans `battleship-library-1.x.jar` ; 
elles sont sont documentées dans la ![javadoc](documentation/javadoc)

NB :des cas de tests écrit en Junit 5.4 vous 
permettront de valider votre implémentation :
   * `CoordTest`
   * `NavyFleetTest`
   * `ShipTest`

![package ships](documentation/package_ships.png)


### utilisation de `info1.network.Network` ...


vous pouvez maintenant utiliser la classe `info1.network.Network` pour échanger avec le serveur, 
càd créer ou rejoindre une partie, puis jouer, càd effectuer un tir 
sur une coordonnée précise.

![package network](documentation/package_network.png)

Plus de détail sur l'usage de la classe Network dans ![HowToUseNetwork](documentation/HowtoUseNetwork.md)

La classe de tests `RunningGameTest` illustre également l'utilisation de `info1.network.Network`.

### ... et développement d'une interface graphique

Développer une interface graphique en Swing vous permettant de jouer, cad

1. positionner des bateaux

2. créer une flotte de bateaux

3. initialiser une partie sur le serveur

4. rejoindre une partie initialisée sur le serveur

5. jouer via le serveur

6. gagner ;-)



## Remarques 

Chaque groupe aura un enseignant-référent qui passera
vous voir régulièrement afin de vous assister 
mais aussi évaluer votre travail.





_Image du bateau issue de https://en.wikipedia.org/wiki/French_battleship_Brennus_
