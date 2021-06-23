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
        GameManager gameManager = app.getGameManager();

        gameManager.setPlayer(new Player("test"));
        gameManager.subscribe();

        NavyFleet navyFleet = new NavyFleet();
        for (int i = 1; i < 11; i++) {
            try {
                navyFleet.add(new Destroyer("dest" + i,"A"+i, "B"+i ));
            } catch (BadCoordException | CoordsBadShipException e) {
                e.printStackTrace();
            }
        }
        gameManager.setFleet(navyFleet);
        app.getViewManager().switchTo(Menu.MAIN);

    }
}
