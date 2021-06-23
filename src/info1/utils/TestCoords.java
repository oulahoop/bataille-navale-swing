package info1.utils;

import com.mashape.unirest.http.exceptions.UnirestException;
import info1.Application;
import info1.network.Network;
import info1.network.Player;
import info1.ships.*;

public class TestCoords {
    public static void main(String[] args) {
        try {
            new Application();
            Application app = Application.getApp();
            Player player = new Player("LeeniiuxTest1");
            Player player2 = new Player("LeeniiuxTest2");
            System.out.println("Player subscribed : " + Network.suscribeNewPlayer(app.getGameManager().getUrl(), player));
            System.out.println("Player subscribed : " + Network.suscribeNewPlayer(app.getGameManager().getUrl(), player2));
            app.getGameManager().setPlayer(player);

            NavyFleet fleet = new NavyFleet();
            for (int i = 1; i < 11; i++) {
                try {
                    fleet.add(new Destroyer("dest" + i,"A"+i, "B"+i ));
                } catch (BadCoordException | CoordsBadShipException e) {
                    e.printStackTrace();
                }
            }
            app.getGameManager().setGame(Network.initNewGame(app.getGameManager().getUrl(), player, fleet));
            System.out.println(app.getGameManager().getGame().getId());
            app.getGameManager().join(app.getGameManager().getGame(), player2, fleet);
            System.out.println(Network.playOneTurn(app.getGameManager().getUrl(), app.getGameManager().getGame(), player, new Coord("A1")));
            System.out.println("Getting coords !");
            app.getGameManager().getLastCoords();
        } catch(UnirestException | BadCoordException | UncompleteFleetException e) { e.printStackTrace(); }
    }
}
