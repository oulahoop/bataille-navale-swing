package info1.view.listeners.mainMenu;

import info1.Application;
import info1.network.Game;
import info1.utils.GameManager;
import info1.view.menus.MainMenu;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
/*
    clic sur le bouton refrech =  refresh
    clic sur le bouton recherce = research(getGameid) if gameID.empty refresh
    clic sur le bouton creer game =  initialize game
    clic sur liste = popup 'voulez vous rejoindre cette partie' oui/non

 */

public class ValueChanged implements ChangeListener {

    private MainMenu menu;
    private GameManager gameManager;

    public ValueChanged(MainMenu menu){
        this.menu = menu;
        this.gameManager = Application.getApp().getGameManager();
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Game game = menu.getSelectedGame();
        if(JOptionPane.showConfirmDialog(Application.getApp().getViewManager(),
                "voulez vous vraiment rejoindre cette partie ?") == 0){
            gameManager.join(game, gameManager.getPlayer(), gameManager.getFleet());
        }
    }
}
