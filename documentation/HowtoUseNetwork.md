# Documentation de **Network**

La serveur de jeu est un serveur REST disponible à l'adresse suivante :
http://37.187.38.219/api/v0

La logique d'échange est la suivante : 

## 1ere phase : initialisation

Des joueurs s'inscrivent sur le serveur via

`Network.suscribeNewPlayer(...)`

On peut lister les joueurs déjà inscrits via 

`Network.listActivePlayers(...)`

Des joueurs initient des parties sur le serveur via 

`Network.initNewGame(...)`

On peut lister les parties initialisées via 

`Network.listInitializedGames(...)`

Un joueur peut rejoindre une partie initialisée

`Network.joinGame(...)`

alors la phase de jeu commence...


## 2ième phase : dérouler d'une partie
A tour de rôle les deux joueurs de la partie vont pouvoir jouer.


Avant de jouer, il faut vérifier l'état de la partie via

`Network.getInfo(...)`

Puis on peut jouer si c'est à notre tour via 

`Network.playOneTurn(...)`



## Les limitations du serveur sont les suivantes

* le joueur qui a initialisé une partie ne sera pas prévenu qu'un joueur
 a rejoint sa partie ;

* lorsqu'un joueur a joué l'autre joueur n'est pas prévenu que c'est 
à son tour de jouer ;

* lorsqu'un joueur touche (ou coule) un navire adverse, 
l'autre joueur n'est pas prévenu ;

* lorsqu'un joueur gagne la partie, l'autre joueur n'est pas 
prévenu non plus.

**NB : il faut utiliser `Network.getInfo(...)` pour s'informer
 périodiquement de l'état courant de la partie**
