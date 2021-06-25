package info1.view.listeners.mainMenu;


import info1.Application;
import info1.network.Game;
import info1.utils.GameManager;
import info1.view.Menu;
import info1.view.ViewManager;
import info1.view.menus.MainMenu;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class ValueChanged implements ListSelectionListener {

    private final ViewManager viewManager;
    private final MainMenu menu;

    public ValueChanged(MainMenu menu, ViewManager vm){
        this.viewManager = vm;
        this.menu = menu;
    }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        Game game = menu.getSelectedGame();

        if(JOptionPane.showConfirmDialog(Application.getApp().getViewManager(),
                "Rejoindre la partie de : " + game.getInitiator().getName(),
                "Answer or i will call the police",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION){
            if(GameManager.join(game)){
                viewManager.switchTo(Menu.GAME);
            }else{
                JOptionPane.showMessageDialog(viewManager, "La game que tu essaies de rejoindre n'est pas correctement initialisée");
            }

        }
    }

}
