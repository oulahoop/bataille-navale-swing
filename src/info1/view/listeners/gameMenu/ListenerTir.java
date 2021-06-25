package info1.view.listeners.gameMenu;

import com.mashape.unirest.http.exceptions.UnirestException;
import info1.Application;
import info1.network.BadIdException;
import info1.network.Network;
import info1.ships.BadCoordException;
import info1.ships.Coord;
import info1.utils.GameManager;
import info1.view.ConstantColor;
import info1.view.Menu;
import info1.view.menus.GameMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.CompletableFuture;

/**
 * Listener permettant au joueur de tirer sur la grille adverse. 
 */
public class ListenerTir implements ActionListener {
    private GameMenu fenetre;
    private JButton selected;

    public ListenerTir(GameMenu gm) {
        fenetre = gm;
    }

    /**
     * Mise en place du listener, il récupère l'information du bouton sélectionné,
     * actionne le bouton de tir qui récupère la coordonnée du bouton afin de tirer au meme endroit chez l'adversaire.
     * @param e
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String nom = ((JButton) e.getSource()).getName();

            if (nom.equalsIgnoreCase("Tirer")) {
                if (selected != null && selected.isEnabled()) {
                    try {
                        Coord coord = new Coord(selected.getName());
                        switch (GameManager.shoot(coord)) {
                            case 0:
                                miss();
                                break;
                            case 1:
                                hit();
                                break;
                            case 10:
                                System.out.println("sunk");
                                sunk();
                                break;
                            case 100:
                                won();
                                break;
                        }
                        fenetre.hit(selected);
                        selected =null;
                        fenetre.waiting();

                        } catch(BadCoordException badCoordException){
                            badCoordException.printStackTrace();
                        }
                    }
                } else {
                    if (((JButton) e.getSource()).getText().isEmpty()) {
                        if (selected != null) {
                            selected.setIcon(null);
                            selected.setBackground(new Color(ConstantColor.BASECOLOR.getColor()));
                        }
                        selected = (JButton) e.getSource();

                        ImageIcon get = new ImageIcon("src/info1/utils/img/scope.png");
                        Image image = get.getImage();
                        image = image.getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH);
                        get = new ImageIcon(image);

                        selected.setIcon(get);
                        selected.setBackground(new Color(ConstantColor.SCOPED.getColor()));
                    }
                }
            }

    /**
     * Affiche le message de victoire et renvoie au menu principal.
     */
    private void won() {
       JOptionPane.showMessageDialog(Application.getApp().getViewManager(),
               "La flotte ennemie est en déroute, nous avons gagné la guerre!");
       Application.getApp().getViewManager().switchTo(Menu.MAIN);
    }

    /**
     * Change la couleur de la case à laquelle un bateau a été coulé.
     */
    private void sunk() {
        ImageIcon get = new ImageIcon("src/info1/utils/img/skull.png");
        Image image = get.getImage();
        image = image.getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH);
        get = new ImageIcon(image);
        selected.setIcon(get);
        selected.setBackground(new Color(ConstantColor.SUNK.getColor()));
    }

    /**
     * Change la couleur de la case à laquelle un bateau est touché.
     */
    private void hit() {
        ImageIcon get = new ImageIcon("src/info1/utils/img/boom.png");
        Image image = get.getImage();
        image = image.getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH);
        get = new ImageIcon(image);

        selected.setIcon(get);

        selected.setBackground(new Color(ConstantColor.HIT.getColor()));
    }

    /**
     * Change la couleur de la case à laquelle un tir est raté.
     */
    private void miss() {
        ImageIcon get = new ImageIcon("src/info1/utils/img/goutte.png");
        Image image = get.getImage();
        image = image.getScaledInstance(45, 45, java.awt.Image.SCALE_SMOOTH);
        get = new ImageIcon(image);
        selected.setIcon(get);

        selected.setBackground(new Color(ConstantColor.MISSED.getColor()));

        //selected.setBackground(new Color(ConstantColor.MISSED.getColor()));
    }
}