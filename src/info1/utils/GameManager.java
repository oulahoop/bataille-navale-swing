package info1.utils;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
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
    private Game game = null;
    private Player player = null;
    private NavyFleet fleet = null;

    public GameManager() {}
    public Game getGame() { return game; }
    public Player getPlayer() { return this.player; }
    public INavyFleet getFleet() { return this.fleet;}
    public String getUrl() { return url; }
    public void setGame(Game game) { this.game = game; }
    public void setPlayer(Player player){ this.player = player; }
    public void setFleet(NavyFleet fleet){ this.fleet = fleet; }

    public boolean join(Game game, Player player, INavyFleet fleet) {
        try {
            Network.joinGame(url, game, player, fleet) ;
            this.game = game;
            if(Math.abs(Network.getInfo(url, game, player)) != 100 || Network.getInfo(url, game, player) != -9999) return true;
        } catch(UnirestException | UncompleteFleetException | BadCoordException | BadIdException e) {
            e.printStackTrace();
        }
        this.game = null;
        return false;
    }

    public boolean join(Game game) {
        try {
            Network.joinGame(url, game, player, fleet) ;
            this.game = game;
            if(Math.abs(Network.getInfo(url, game, player)) != 100 || Network.getInfo(url, game, player) != -9999) return true;

        } catch(UnirestException | UncompleteFleetException | BadCoordException | BadIdException e) {
            e.printStackTrace();
        }
        this.game = null;
        return false;
    }

    public boolean canPlay() {
        try {
            if(Network.getInfo(url, game, player) == 10) return true;
        } catch(UnirestException | BadIdException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int shoot(Coord coord) {
        //app.getViewManager().shootAnimation();
        int result = -9999;
        try { result = Network.playOneTurn(url, game, player, coord);
        } catch (BadCoordException | UnirestException e) { e.printStackTrace(); }
        return result;
        /*switch(result) {
            case 0: miss(coord); break;
            case 1: hit(coord);  break;
            case 10: sunk(coord); break;
            case 100: won(coord); break;
        }*/
    }
    public void miss(Coord coord) {
        //app.getViewManager().missAnimation();
        //app.getViewManager().miss(coord);
        waiting();
    }
    public void hit(Coord coord) {
        //app.getViewManager().hitAnimation();
        //app.getViewManager().hit(coord);
        waiting();
    }
    public void sunk(Coord coord) {
        //app.getViewManager().hitAnimation();
        //app.getViewManager().sunk(coord);
        waiting();
    }
    public void won(Coord coord) {
        //app.getViewManager().winAnimation();
        //app.getViewManager().sunk(coord);
    }

    public void initialize(){
        if (player != null && fleet != null){
            try {
                game = Network.initNewGame(url, player, fleet);
            } catch (UnirestException | UncompleteFleetException | BadCoordException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean gamePerdu() {
        try { return Network.getInfo(url, game, player) == 100;
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

    public void subscribe(){
        try {
            Network.suscribeNewPlayer(url, player);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
    }

    public ICoord getLastCoords() {
        try {
            HttpResponse<JsonNode> response = Unirest.get(url.trim() + "/partie/joue/" + game.getId()).header("Accept", "application/json").basicAuth("root", "").asJson();
            System.out.println(response.getStatus());
            /*if(response.getStatus() == 200) {
                JSONObject jObj = ((JsonNode) response.getBody()).getObject();
                if (jObj.has("error") && jObj.getString("error").equals("partie non disponible")) {
                    throw new BadIdException();
                } else {
                    String etat = jObj.getString("etat");
                    String nom;
                    if (etat.equals("initie")) {
                        nom = jObj.getJSONObject("initiateur").getString("nom");
                        return nom.equals(player.getName()) ? 1 : -1;
                    } else if (etat.equals("joins")) {
                        nom = jObj.getJSONObject("joueurCourant").getString("nom");
                        return nom.equals(player.getName()) ? 10 : -10;
                    } else {
                        nom = jObj.getJSONObject("gagnant").getString("nom");
                        return nom.equals(player.getName()) ? 100 : -100;
                    }
                }
            } else {
                return -9999;
            }*/
        } catch (UnirestException /*| BadIdException*/ e) { e.printStackTrace(); }
        return null;
    }

}