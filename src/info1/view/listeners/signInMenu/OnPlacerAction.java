package info1.view.listeners.signInMenu;

import info1.ships.AircraftCarrier;
import info1.ships.BadCoordException;
import info1.ships.CoordsBadShipException;
import info1.ships.IShip;
import info1.view.menus.SignInMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnPlacerAction implements ActionListener {
    private SignInMenu fenetre;

    private String rotation_bateau;
    private String bateau_a_placer;

    public OnPlacerAction(SignInMenu sim){
        fenetre = sim;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        bateau_a_placer = ((JButton) e.getSource()).getText();
        /*
        OnClicCoord occ = new OnClicCoord(fenetre,this);
        for(JButton jb : fenetre.getButtons()){
            jb.addActionListener(occ);
        }*/
    }

    /**
     * Methode permettant de get le nom du bateau à placer
     * @return String du nom de la classe du bateau à placer
     */
    public String getBateau_a_placer() {
        return bateau_a_placer;
    }
}
