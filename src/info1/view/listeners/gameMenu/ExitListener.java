package info1.view.listeners.gameMenu;

import info1.Application;
import info1.utils.GameManager;
import info1.view.Menu;
import info1.view.ViewManager;
import info1.view.menus.MainMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExitListener implements ActionListener {

    private final ViewManager viewManager;
    private final GameManager gameManager;

    public ExitListener(ViewManager vm){
        this.viewManager = vm;
        this.gameManager = Application.getApp().getGameManager();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(JOptionPane.showConfirmDialog(Application.getApp().getViewManager(),
                "Voulez-vous vraiment quitter ?") == JOptionPane.YES_OPTION) {
            viewManager.switchTo(Menu.MAIN);
            gameManager.setGame(null);
        }
    }
}
