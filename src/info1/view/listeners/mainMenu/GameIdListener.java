package info1.view.listeners.mainMenu;

import info1.Application;
import info1.utils.GameManager;
import info1.view.ViewManager;
import info1.view.menus.MainMenu;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.CompletableFuture;

public class GameIdListener implements KeyListener {
    private final ViewManager viewManager;
    private final MainMenu menu;
    private final GameManager gameManager;

    public GameIdListener(MainMenu menu, ViewManager vm){
        this.viewManager = vm;
        this.menu = menu;
        this.gameManager = Application.getApp().getGameManager();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        CompletableFuture.runAsync(() -> {
            synchronized(this) {
                try {
                    wait(10);
                    if(e.getSource() instanceof JTextField) menu.research(menu.getGameId());
                } catch(InterruptedException ex) { ex.printStackTrace(); }
            }
        });
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
