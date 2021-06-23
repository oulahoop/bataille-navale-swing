package info1.view.listeners.gameMenu;

import info1.Application;
import info1.ships.BadCoordException;
import info1.ships.Coord;
import info1.utils.GameManager;
import info1.view.menus.GameMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerTir implements ActionListener {
    private GameMenu fenetre;
    private JButton selected;
    private GameManager gameMan;

    public ListenerTir(GameMenu gm) {
        fenetre = gm;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nom = ((JButton) e.getSource()).getName();
        if(gameMan.canPlay()){

            if (nom.equalsIgnoreCase("Tirer")) {
                if (selected != null) {
                    gameMan = Application.getApp().getGameManager();
                    try {
                        Coord coord = new Coord(selected.getName());
                        switch (gameMan.shoot(coord)) {
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
                                break;}
                        selected.setText("p");
                        fenetre.activerTir(false);

                        } catch(BadCoordException badCoordException){
                            badCoordException.printStackTrace();
                        }
                    }
                } else {
                    if (((JButton) e.getSource()).getText().equals("")) {
                        if (selected != null) {
                            selected.setEnabled(true);
                            selected.setBackground(new Color(0x78939A));
                        }
                        selected = (JButton) e.getSource();
                        selected.setEnabled(false);
                        selected.setBackground(new Color(0x253662));
                        fenetre.activerTir(true);
                    }
                }
            }
        }


    private void won(Coord coord) {
        JOptionPane.showMessageDialog(Application.getApp().getViewManager(),
                "La flotte ennemie est en déroute nous avons gagné la guerre!");
    }

    private void sunk(Coord coord) {
        JOptionPane.showMessageDialog(Application.getApp().getViewManager(),
                "Bateau ennemi coulé, un pas de plus vers la victoire commandant!");
    }

    private void hit(Coord coord) {
        selected.setText("O");
        JOptionPane.showMessageDialog(Application.getApp().getViewManager(),
                "Cible touchée!");
    }

    private void miss(Coord coord) {
        selected.setText("X");
        JOptionPane.showMessageDialog(Application.getApp().getViewManager(),
                "Tir raté!");
    }
}