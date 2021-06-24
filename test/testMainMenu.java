import info1.Application;
import info1.network.Player;
import info1.ships.*;
import info1.utils.GameManager;
import info1.view.Menu;
import info1.view.ViewManager;
import info1.view.menus.MainMenu;

public class testMainMenu {

    public static void main(String[] args) {
        Application app = new Application();

        GameManager.setPlayer(new Player("OndiraiQueCaMarche"));
        GameManager.subscribe();

        NavyFleet navyFleet = new NavyFleet();
            try {
                navyFleet.add(new AircraftCarrier("cdg","A1", "A5" ));
                navyFleet.add(new Destroyer("dest","B1","B2"));
                navyFleet.add(new Destroyer("dest2","B4","B5"));

                navyFleet.add(new Cruiser("cruis","C1","C3"));
                navyFleet.add(new Cruiser("cruis2","C5","C7"));
                navyFleet.add(new Submarine("sub","D1"));
                navyFleet.add(new Battleship("bagar","E1","E4"));

            } catch (BadCoordException | CoordsBadShipException e) {
                e.printStackTrace();
            }

        GameManager.setFleet(navyFleet);
        app.getViewManager().switchTo(Menu.MAIN);

    }
}
