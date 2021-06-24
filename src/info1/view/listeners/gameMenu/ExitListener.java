package info1.view.listeners.gameMenu;

import info1.Application;
import info1.utils.GameManager;
import info1.view.Menu;
import info1.view.ViewManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * Listener permettant de quitter la partie en cours avant qu'elle ne soit terminée.
 */
public class ExitListener implements ActionListener {

    private final ViewManager viewManager;

    public ExitListener(ViewManager vm){
        this.viewManager = vm;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(JOptionPane.showConfirmDialog(Application.getApp().getViewManager(),
                "Voulez-vous vraiment quitter ?") == JOptionPane.YES_OPTION) {
            viewManager.switchTo(Menu.MAIN);
            GameManager.setGame(null);
        }
    }
}
