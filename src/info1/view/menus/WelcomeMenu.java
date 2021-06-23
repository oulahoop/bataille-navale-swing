package info1.view.menus;

import info1.view.ViewManager;
import info1.view.listeners.welcomeMenu.OnMouseEvent;

import javax.swing.*;

public class WelcomeMenu {

    JPanel principal = new JPanel();

    //TODO ajouter un image en fond

    public WelcomeMenu(ViewManager frame) {
        principal.setSize(frame.getSize());
        principal.setPreferredSize(principal.getSize());

        principal.setVisible(true);
        principal.setName("WelcomeMenu");

        principal.addMouseListener(new OnMouseEvent(frame));

        frame.setContentPane(principal);
        frame.update();
    }
}
