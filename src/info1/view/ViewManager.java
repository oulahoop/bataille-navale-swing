package info1.view;

import info1.view.menus.GameMenu;
import info1.view.menus.MainMenu;
import info1.view.menus.SignInMenu;
import info1.view.menus.WelcomeMenu;

import javax.swing.*;

public class ViewManager extends JFrame {
    public ViewManager() {}

    public void switchTo(Menu menu) {
        switch(menu) {
            case WELCOME: new WelcomeMenu(this); break;
            case SIGN_IN: new SignInMenu(this); break;
            case MAIN: new MainMenu(this); break;
            case GAME: new GameMenu(this); break;
        }
    }
}
