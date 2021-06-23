package info1;

import info1.view.ViewManager;

public class Application {
    private final ViewManager viewManager = new ViewManager();

    private static Application app;
    public Application() {
        app = this;
    }

    public static Application getApp() { return app; }
    public ViewManager getViewManager() { return viewManager; }

    public static void main(String[] args) { new Application(); }
}