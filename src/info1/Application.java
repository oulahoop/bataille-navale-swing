package info1;

import info1.network.Network;
import info1.utils.GameManager;
import info1.view.ViewManager;

public class Application {
    private ViewManager viewManager = new ViewManager();
    private GameManager gameManager = new GameManager();
    private Network network = new Network();

    private static Application app;
    public Application() {
        app = this;
    }

    public static Application getApp() { return app; }
    public ViewManager getViewManager() { return viewManager; }
    public GameManager getGameManager() { return gameManager; }
    public Network getNetwork() { return network; }
}
