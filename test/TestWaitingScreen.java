import info1.Application;

import info1.utils.GameManager;
import info1.view.Menu;

public class TestWaitingScreen {

    public static void main(String[] args) {
        Application app = new Application();
        app.getViewManager().switchTo(Menu.WAITING);
    }
}
