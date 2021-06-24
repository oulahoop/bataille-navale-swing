package info1.view.listeners.signInMenu;

import info1.view.menus.SignInMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SensListener implements ActionListener {
    private SignInMenu fenetre;

    public SensListener(SignInMenu sim){
            fenetre = sim;
        }
    @Override
    public void actionPerformed(ActionEvent e) {
        switch (((JButton)e.getSource()).getName().toLowerCase()){
            case "asens" : fenetre.setAircraftSens(!fenetre.getAircraftSens()); break;
            case "bsens" : fenetre.setBattleShipSens(!fenetre.getBattleShipSens()); break;
            case "csens" : fenetre.setCruiserSens(!fenetre.getCruiserSens()); break;
            case "dsens" : fenetre.setDestroyerSens(!fenetre.getDestroyerSens()); break;
            case "ssens" : fenetre.setSubmarinSens(!fenetre.getSubmarinSens()); break;
        }
    }
}
