package info1.view.listeners.gameMenu;

import com.mashape.unirest.http.exceptions.UnirestException;
import info1.Application;
import info1.network.BadIdException;
import info1.network.Network;
import info1.ships.BadCoordException;
import info1.ships.Coord;
import info1.utils.GameManager;
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
                                miss(coord);
                                break;
                            case 1:
                                hit(coord);
                                break;
                            case 10:
                                sunk(coord);
                                break;
                            case 100:
                                won(coord);
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
                            selected.setBackground(new Color(0x78939A));
                        }
                        selected = (JButton) e.getSource();
                        selected.setBackground(new Color(0x253662));
                    }
                }
            }

    /**
     * Affiche le message de victoire et renvoie au menu principal.
     * @param coord , la coordonnée du tir qui coule le dernier bateau.
     */
    private void won(Coord coord) {
       JOptionPane.showMessageDialog(Application.getApp().getViewManager(),
               "La flotte ennemie est en déroute, nous avons gagné la guerre!");
       Application.getApp().getViewManager().switchTo(Menu.MAIN);
    }

    /**
     * Change la couleur de la case à laquelle un bateau a été coulé.
     * @param coord , la coordonnée du tir qui coule le bateau.
     */
    private void sunk(Coord coord) {
        selected.setBackground(new Color(0xff7700));
    }

    /**
     * Change la couleur de la case à laquelle un bateau est touché.
     * @param coord , la coordonnée du tir qui touche un bateau.
     */
    private void hit(Coord coord) {
        selected.setBackground(new Color(0xffcc00));
    }

    /**
     * Change la couleur de la case à laquelle un tir est raté.
     * @param coord , la coordonnée du tir raté.
     */
    private void miss(Coord coord) {
        selected.setBackground(new Color(0xfcfcfc));
    }
}