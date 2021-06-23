package info1.view.listeners.mainMenu;

import com.mashape.unirest.http.exceptions.UnirestException;
import info1.Application;
import info1.network.Game;
import info1.network.Network;
import info1.utils.GameManager;
import info1.view.Menu;
import info1.view.ViewManager;
import info1.view.menus.MainMenu;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
/*
    clic sur le bouton refrech =  refresh
    clic sur le bouton recherce = research(getGameid) if gameID.empty refresh
    clic sur le bouton creer game =  initialize game
    clic sur liste = popup 'voulez vous rejoindre cette partie' oui/non

 */

public class ValueChanged implements ListSelectionListener {

    private final ViewManager viewManager;
    private MainMenu menu;
    private GameManager gameManager;

    public ValueChanged(MainMenu menu, ViewManager vm){
        this.viewManager = vm;
        this.menu = menu;
        this.gameManager = Application.getApp().getGameManager();
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        Game game = menu.getSelectedGame();

        if(JOptionPane.showConfirmDialog(Application.getApp().getViewManager(),
                "Rejoindre la partie de : " + game.getInitiator().getName(),
                "Answer or i will call the police",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            if(gameManager.join(game, gameManager.getPlayer(), gameManager.getFleet())){
                viewManager.switchTo(Menu.GAME);
            }else{
                JOptionPane.showMessageDialog(viewManager, "La game que tu essaies de rejoindre n'est pas correctement initialisée");
            }

        }
        }

}
