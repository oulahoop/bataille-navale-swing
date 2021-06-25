package info1.view;

import info1.view.menus.*;

import javax.swing.*;

import java.awt.*;


/**
 * Classe qui definie le Frame de l'application dans lequel tout les affichages ont lieu
 */
public class ViewManager extends JFrame {

    /**
     *Constructeur de la Classe ViemManager
     * Définition des diff"rents parametre du frame
     * */
    public ViewManager() {
        this.setSize(new Dimension(1280, 720));
        this.setPreferredSize(this.getSize());
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);
        new WelcomeMenu(this);
    }

    /**
     * Permet de changer le menu affiché dans le frame
     * @param menu Le menu à afficher
     */
    public void switchTo(Menu menu) {
        switch(menu) {
            case WELCOME -> new WelcomeMenu(this);
            case SIGN_IN -> new SignInMenu(this);
            case MAIN -> new MainMenu(this);
            case GAME -> new GameMenu(this);
            case WAITING -> new WaitingScreen(this);
        }
    }

    /**
     * Méthode qui permet de mettre a jours la vue
     */
    public void update() {
        this.setPreferredSize(this.getSize());
        this.pack();
        this.setVisible(true);
        repaint();
    }
}
