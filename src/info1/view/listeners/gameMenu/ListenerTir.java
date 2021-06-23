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
    public ListenerTir(GameMenu gm){
        fenetre = gm;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String nom = ((JButton)e.getSource()).getName();

        if(nom.equalsIgnoreCase("Tirer")){
            if(selected != null){
                selected.setText("boom");
                fenetre.activerTir(false);
                gameMan = Application.getApp().getGameManager();
                try {
                    Coord coord  =new Coord(selected.getName());
                    System.out.println(selected.getName());
                    System.out.println(coord.toString());
                    gameMan.shoot(coord);
                } catch (BadCoordException badCoordException) {
                    badCoordException.printStackTrace();
                }
            }
        }
        else{
            if(((JButton)e.getSource()).getText().equals("")) {
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
