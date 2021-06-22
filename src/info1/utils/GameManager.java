package info1.utils;

import com.mashape.unirest.http.exceptions.UnirestException;
import info1.Application;
import info1.network.BadIdException;
import info1.network.Game;
import info1.network.Network;
import info1.network.Player;
import info1.ships.*;
import info1.view.Menu;

import java.util.concurrent.CompletableFuture;

public class GameManager {

    private Application app = Application.getApp();
    private String url = "http://37.187.38.219/api/v0";
    private Game game;
    private Player player;

    public GameManager() {}
    public Game getGame() { return game; }

    public boolean join(Game game, Player player, INavyFleet fleet) {
        try {
            Network.joinGame(url, game, player, fleet) ;
            this.game = game;
            this.player = player;
            if(Network.getInfo(url, game, player) == 1 || Network.getInfo(url, game, player) == -1) return true;
        } catch(UnirestException | UncompleteFleetException | BadCoordException | BadIdException e) {
            e.printStackTrace();
        }
        this.game = null;
        return false;
    }

    public void leave() {
        if(gameEnded()) {
            this.game = null;
            this.player = null;
            app.getViewManager().switchTo(Menu.SIGN_IN);
        }
    }

    public boolean canPlay() {
        try {
            if(Network.getInfo(url, game, player) == 10) return true;
        } catch(UnirestException | BadIdException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void shoot(Coord coord) {
        app.getViewManager().disableCell(coord);
        app.getViewManager().shootAnimation();
        int result = -9999;
        try { result = Network.playOneTurn(url, game, player, coord);
        } catch (BadCoordException | UnirestException e) { e.printStackTrace(); }

        switch(result) {
            case 0: miss(); break;
            case 1: hit(); break;
            case 10: sunk(); break;
            case 100: won(); break;
        }
    }
    public void miss() {

    }
    public void hit() {

    }
    public void sunk() {

    }
    public void won() {

    }



    public boolean gameEnded() {
        try { return Math.abs(Network.getInfo(url, game, player)) == 100;
        } catch(UnirestException | BadIdException e) { e.printStackTrace(); }
        return false;
    }

    public void waiting() {
        CompletableFuture.runAsync(() -> {
           synchronized(this) {
               app.getViewManager().enableShoot(canPlay());
               try { while(!canPlay()) { wait(100); }
               } catch(InterruptedException e) { e.printStackTrace(); }
               app.getViewManager().enableShoot(canPlay());
           }
        });
    }

    public ICoord getLastCoords() {
        return null;
    }
}