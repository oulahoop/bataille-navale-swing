package info1.view;

import info1.view.menus.*;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;

import java.util.concurrent.CompletableFuture;

/**
 * Classe qui definie le Frame de l'application dans lequel tout les affichages ont lieu
 */
public class ViewManager extends JFrame {

    public ViewManager() {
        this.setSize(new Dimension(1280, 720));
        this.setPreferredSize(this.getSize());
        this.setLocationRelativeTo(null);
        this.pack();
        this.setVisible(true);
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

    /**
     * Méthode qui permet l'affichage d'une "popUp"
     * @param message Le message à afficher dans l'alert
     */
    public void alert(String message) {
        CompletableFuture.runAsync(() -> {
            synchronized(this) {
                try {
                    JTextField text = new JTextField();
                    text.setPreferredSize(new Dimension(this.getWidth(), 50));
                    text.setText(message);
                    text.setEditable(false);
                    text.setBackground(new Color(0xFF7869));
                    text.setHorizontalAlignment(JTextField.CENTER);
                    Border border = BorderFactory.createEmptyBorder();
                    text.setBorder(border);

                    this.getContentPane().add(text);
                    update();

                    for(int i=-50; i<0; i++) {
                        text.setLocation(0, i);
                        wait(3);
                        update();
                    }
                    wait(1500);
                    for(int i=0; i>-50; i--) {
                        text.setLocation(0, i);
                        wait(3);
                        update();
                    }
                    this.remove(text);
                    update();
                } catch(InterruptedException e) { e.printStackTrace(); }
            }
        });
    }
}
