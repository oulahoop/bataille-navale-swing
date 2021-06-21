package info1.utils;

import com.mashape.unirest.http.exceptions.UnirestException;
import info1.Application;
import info1.network.BadIdException;
import info1.network.Game;
import info1.network.Network;
import info1.network.Player;
import info1.ships.BadCoordException;
import info1.ships.Coord;
import info1.ships.INavyFleet;
import info1.ships.UncompleteFleetException;
import info1.view.Menu;

public class GameManager {

    private Application app = Application.getApp();
    private String url = "http://37.187.38.219/api/v0";
    private Game game;
    private Player player;

    public GameManager(){ }

    public boolean join(Game game, Player player, INavyFleet fleet) {
        try {
            Network.joinGame(url, game, player, fleet) ;
            this.game  = game;
            this.player = player;
            if(Network.getInfo(url, game, player) == 1 || Network.getInfo(url, game, player) == -1) return true;

        } catch(UnirestException | UncompleteFleetException | BadCoordException | BadIdException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void leave() {
        app.getViewManager().switchTo(Menu.SIGN_IN);
    }

    public boolean canPlay() {
        try {
            if(Network.getInfo(url, game, player) == 10) return true;
        } catch(UnirestException | BadIdException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean shoot(Coord coord) {
        try {
            switch(Network.playOneTurn(url, game, player, coord)){
                case 0 : return false;
                case 1 : return true;
                case 10 :
                    //DrowShip();
                    return true;
                case 100 :
                    //gameWon();
                    return true;
            }
        } catch(UnirestException | BadCoordException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean gameEnded() {
        try {
            return Network.getInfo(url, game, player) == 100 || Network.getInfo(url, game, player) == -100;
        } catch(UnirestException | BadIdException e) {
            e.printStackTrace();
        }
        return false;
    }
}