package info1.view.listeners.mainMenu;

import info1.Application;
import info1.utils.GameManager;
import info1.view.Menu;
import info1.view.ViewManager;
import info1.view.menus.MainMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuActionListener implements ActionListener {

    private final ViewManager viewManager;
    private final MainMenu menu;

    public MenuActionListener(MainMenu menu, ViewManager vm){
        this.viewManager = vm;
        this.menu = menu;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()instanceof JButton){
            JButton clicked = (JButton) e.getSource();
            switch (clicked.getName()){
                case "search"  :
                    menu.research(menu.getGameSearch());
                    break;
                case "refresh" :
                    menu.refresh();
                    break;
                case "createGame" :
                    GameManager.initialize();
                    if(GameManager.getGame() != null){
                        viewManager.switchTo(Menu.WAITING);
                    }
                    break;
                case "quitter" :
                    if(JOptionPane.showConfirmDialog(Application.getApp().getViewManager(),
                            "Voulez-vous vraiment quitter ?") == JOptionPane.YES_OPTION) {
                        viewManager.switchTo(Menu.SIGN_IN);
                    }
                    break ;
            }
        }

    }
}
