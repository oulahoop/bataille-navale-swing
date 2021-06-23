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
        System.out.println(((JButton)e.getSource()).getName());
        placer(((JButton)e.getSource()).getName(),e);
        System.out.println("Après fonction : " + rotation_bateau + " et " + bateau_a_placer);
    }


    private void placer(String bateauName, ActionEvent e){
        for(JComboBox<String> jcb : fenetre.getRotation()){
            if(jcb.getName().equalsIgnoreCase(bateauName)){
                rotation_bateau = (String) jcb.getSelectedItem();
                bateau_a_placer = ((JButton)e.getSource()).getName();
            }
        }
        OnClicCoord occ = new OnClicCoord(fenetre,this);
        for(JButton jb : fenetre.getButtons()){
            jb.addActionListener(occ);
        }
    }

    public String getRotation_bateau() {
        return rotation_bateau;
    }

    public String getBateau_a_placer() {
        return bateau_a_placer;
    }
}
