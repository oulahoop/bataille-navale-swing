package info1;

import info1.network.Network;
import info1.utils.GameManager;
import info1.view.ViewManager;

public class Application {
    private final ViewManager viewManager = new ViewManager();
    private final GameManager gameManager = new GameManager();

    private static Application app;
    public Application() {
        app = this;
    }

    public static Application getApp() { return app; }
    public ViewManager getViewManager() { return viewManager; }
    public GameManager getGameManager() { return gameManager; }
}