package info1.view.menus;


import info1.view.ViewManager;
import info1.view.listeners.OnMouseEvent;

import javax.swing.*;

public class WelcomeMenu {
    public WelcomeMenu(ViewManager frame) {
        JPanel principal = new JPanel();
        principal.setVisible(true);
        principal.setName("WelcomeMenu");
        principal.addMouseListener(new OnMouseEvent());
        frame.setContentPane(principal);
        frame.repaint();
    }
}
