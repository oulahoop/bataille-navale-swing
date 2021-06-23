package info1.utils;

import com.mashape.unirest.http.exceptions.UnirestException;

import info1.network.BadIdException;
import info1.network.Game;
import info1.network.Network;
import info1.network.Player;
import info1.ships.*;

import java.util.List;

/**
 * Classe regroupant un ensemble de methodes static qui uttilisent la classe "Network" tout en incluant la gestion des éxceptions
 */
public class GameManager {

    private static final String url = "http://37.187.38.219/api/v0";
    private static Game game = null;
    private static Player player = null;
    private static NavyFleet fleet = null;

    /**
     * Setter de l'attribut "game"
     * Est uttilisé dans le cas ou il faut rejoindre une game préalablement "get"
     * @param game La partie à stocker dans l'attribut "game"
     */
    public static void setGame(Game game) { GameManager.game = game; }

    /**
     * Setter de l'attribut "player"
     * Est uttilisé lors de la création du joueur courant
     * @param player Le joueur à stocker en dans l'attribut "player"
     */
    public static  void setPlayer(Player player){ GameManager.player = player; }

    /**
     * Setter de l'attribut "fleet"
     * Est uttilisé lors de la création de la "fleet" courante par le joueur courant
     * @param fleet La "fleet" à stocker dans l'attribut "fleet"
     */
    public static  void setFleet(NavyFleet fleet){ GameManager.fleet = fleet; }

    /**
     * Getter de l'attribut "game"
     * @return la partie courante
     */
    public static  Game getGame() { return game; }

    /**
     * Getter de l'attribut "player"
     * @return le joueur courant
     */
    public static  Player getPlayer() { return player; }

    /**
     * Getter de l'attribut "fleet"
     * @return la "fleet" courante
     */
    public static  INavyFleet getFleet() { return fleet;}

    /**
     * Getter de l'attribut "url"
     * Est uttiliser dans le cas ou les methodes de la classe "Network" sont directement appelé
     * @return L'url du serveur
     */
    public static String getUrl() { return url; }

    /**
     * Méthode qui permet de faire rejoindre une partie au joueur courant
     * Permet de gérer les exceptions de la class "Network" à l'appel des méthodes "joinGame()" et "getInfo()"
     * @param game La partie a rejoindre
     * @return true en cas de succes, false en cas contraire
     */
    public static  boolean join(Game game) {
        try {
            Network.joinGame(url, game, player, fleet) ;
            GameManager.game = game;
            if(Math.abs(Network.getInfo(url, game, player)) != 100 || Network.getInfo(url, game, player) != -9999) return true;

        } catch(UnirestException | UncompleteFleetException | BadCoordException | BadIdException e) {
            e.printStackTrace();
        }
        GameManager.game = null;
        return false;
    }

    /**
     * Méthode qui permet de savoir si c'est au tour du joueur courant de jouer
     * Permet également en gérer les exeptions de la classe "Network" à l'appel de la méthode "getInfo()"
     * @return true si c'est au tour du joueur courant, false sinon
     */
    public static  boolean canPlay() {
        try {
            if(Network.getInfo(url, game, player) == 10) return true;
        } catch(UnirestException | BadIdException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Méthode qui permet d'effectuer un tir (jouer un tour) à la Coordonnée passée en parametre
     * Permet également en gérer les exeptions de la classe "Network" à l'appel de la méthode "playOneTurn()"
     * @param coord La coordonnée à la quel il faut effectuer le tir
     * @return int, le résultat de la methode "playOneTurn()"
     */
    public static  int shoot(Coord coord) {
        int result = -9999;
        try {
            result = Network.playOneTurn(url, game, player, coord);
        } catch (BadCoordException | UnirestException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Méthode qui permet d'initialiser une partie
     * Permet de également de gérer les exceptions renvoyées par la methode "initNewGame()" de la classe "Network"
     */
    public static  void initialize(){
        if (player != null && fleet != null){
            try {
                game = Network.initNewGame(url, player, fleet);
            } catch (UnirestException | UncompleteFleetException | BadCoordException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Méthode qui permet de savoir si la game est perdu
     * Permet de également de gérer les exceptions renvoyées par la methode "getInfo()" de la classe "Network"
     * @return true si la partie courante est perdu, false sinon
     */
    public static  boolean gameLost() {
        try {
            return Network.getInfo(url, game, player) == -100;
        } catch(UnirestException | BadIdException e) { e.printStackTrace(); }
        return false;
    }

    /**
     * Méthode qui permet de "Subscribe" un Player au serveur
     * Permet de également de gérer l'exception renvoyée par la methode "suscribeNewPlayer()" de la classe "Network"
     */
    public static void subscribe(){
        try {
            Network.suscribeNewPlayer(url, player);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    /**
     * Méthode qui permet de savoir si un joueur a rejoind la partie Courante
     * Permet de également de gérer les exceptions renvoyées par la methode "getInfo()" de la classe "Network"
     * @return true si un joueur a rejoind, false sinon
     */
    public static boolean hasGuest(){
        try {
            return !(Math.abs(Network.getInfo(GameManager.getUrl(), GameManager.getGame(), GameManager.getPlayer())) == 10);
        } catch(UnirestException | BadIdException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Méthode qui permet de recupérer la liste des parties initialisées sur le serveur
     * Permet de également de gérer l'exception renvoyée par la methode "listInitializatedGames()" de la classe "Network"
     * @return Une liste de Partie, null si Exception
     */
    public static List<Game> GetGames(){
        try {
            return Network.listInitializedGames(url);
        } catch(UnirestException e) { e.printStackTrace(); }
        return null;
    }
}