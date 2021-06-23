package info1.view.listeners.mainMenu;

import info1.view.menus.MainMenu;

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

    public ValueChanged(MainMenu menu){ this.menu = menu; }

    @Override
    public void stateChanged(ChangeEvent e) {

    }
}
