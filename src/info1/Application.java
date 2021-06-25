package info1;

import info1.utils.GameManager;

import info1.view.ViewManager;

import javax.swing.*;


public class Application {
    private final ViewManager viewManager = new ViewManager();

    private static Application app;
    public Application() {
        app = this;
        //Afin de savoir si il faut initialiser le proxy
        if(JOptionPane.showConfirmDialog(this.getViewManager(),
                "Etes vous connecté sur le reseau de l'iut",
                "proxy configuration", JOptionPane.YES_NO_OPTION)== JOptionPane.YES_OPTION){
            GameManager.initProxy();
        }
    }
    public static Application getApp() { return app; }
    public ViewManager getViewManager() { return viewManager; }

    public static void main(String[] args) {
        new Application();
    }
}