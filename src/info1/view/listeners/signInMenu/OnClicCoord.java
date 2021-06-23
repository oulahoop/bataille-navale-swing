package info1.view.listeners.signInMenu;

import info1.Application;
import info1.ships.*;
import info1.utils.GameManager;
import info1.view.menus.SignInMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnClicCoord implements ActionListener {

    private SignInMenu fenetre;
    private OnPlacerAction placement;
    private Application app = Application.getApp();

    public OnClicCoord(SignInMenu sim, OnPlacerAction opa){
        fenetre = sim;
        placement = opa;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (GameManager.getFleet() == null){ GameManager.setFleet(new NavyFleet()); }
        if (!GameManager.getFleet().isComplete()){
            String xy = ((JButton)e.getSource()).getName();
            String nameBateau = placement.getBateau_a_placer();
            String nameRotation = placement.getRotation_bateau();
            try {
                this.BatoAPlacer(nameBateau,xy,nameRotation);
            } catch (BadCoordException | CoordsBadShipException badCoordException) {
                badCoordException.printStackTrace();
            }
            System.out.println(GameManager.getFleet().toString());
        }

    }

    public void BatoAPlacer(String name,String coord,String placement) throws BadCoordException, CoordsBadShipException {
        String coordFin;
        switch(name.toLowerCase()){
            case "aircraftcarrier" :
                coordFin = aidePlacement(coord,placement,5);
                System.out.println(coord + "---->" + coordFin);
                GameManager.getFleet().add(new Battleship("nom",coord,coordFin));
                for(IShip s : GameManager.getFleet().getShips()){
                    for(ICoord c : s.getCoords()) {
                        fenetre.getButtons().get(c.getX()-1+(c.getY()-1)*10).setBackground(Color.BLACK);
                    }
                }
            case "battleship":
                coordFin = aidePlacement(coord,placement,4);
                GameManager.getFleet().add(new Battleship("nom",coord,coordFin));
                for(IShip s : GameManager.getFleet().getShips()){
                    for(ICoord c : s.getCoords()) {
                        fenetre.getButtons().get(c.getX()-1+(c.getY()-1)*10).setBackground(Color.BLACK);
                    }
                }
                break;
            case "cruiser":
                coordFin = aidePlacement(coord,placement,3);
                GameManager.getFleet().add(new Cruiser("nom",coord,coordFin));
                for(IShip s : GameManager.getFleet().getShips()){
                    for(ICoord c : s.getCoords()) {
                        fenetre.getButtons().get(c.getX()-1+(c.getY()-1)*10).setBackground(Color.BLACK);
                    }
                }
                break;
            case"destroyer":
                coordFin = aidePlacement(coord,placement,2);
                GameManager.getFleet().add(new Destroyer("nom",coord,coordFin));
                for(IShip s : GameManager.getFleet().getShips()){
                    for(ICoord c : s.getCoords()) {
                        fenetre.getButtons().get(c.getX()-1+(c.getY()-1)*10).setBackground(Color.BLACK);
                    }
                }
                break;
            case"submarine": ;
                GameManager.getFleet().add(new Submarine("nom",coord));
                for(IShip s : GameManager.getFleet().getShips()){
                    for(ICoord c : s.getCoords()) {
                        fenetre.getButtons().get(c.getX()-1+(c.getY()-1)*10).setBackground(Color.BLACK);
                    }
                }
                break;
        }

    }

    private String aidePlacement(String coord, String placement,int size){
        switch (placement.toLowerCase()){
            //LETTRE
            case "droite" :
                char a = ((char) ((int) coord.charAt(0)+size-1));
                char b = coord.charAt(1);
                String s = coord.length()>2 ? String.valueOf(coord.charAt(2)) : "";
                return String.valueOf(a)+String.valueOf(b)+String.valueOf(s);
            case "gauche" : //Bug ??
                char a1 = ((char) ((int) coord.charAt(0)-size+1));
                char b1 = coord.charAt(1);
                String s1 = coord.length()>2 ? String.valueOf(coord.charAt(2)) : "";
                return String.valueOf(a1)+String.valueOf(b1)+String.valueOf(s1);
            //CHIFFRE
            case "haut" :
                char a2 = coord.charAt(0);
                String b2 = String.valueOf(coord.charAt(1)) + String.valueOf(coord.length()>2?coord.charAt(2):"");
                b2 = String.valueOf(Integer.parseInt(b2) - size +1);
                return String.valueOf(a2)+String.valueOf(b2);
            case "bas" :
                char a3 = coord.charAt(0);
                String b3 = String.valueOf(coord.charAt(1)) + String.valueOf(coord.length()>2 ? String.valueOf(coord.charAt(2)) : "");
                b3 = String.valueOf(Integer.parseInt(b3)+size -1);
                return String.valueOf(a3)+String.valueOf(b3);
        }
        return "";
    }
}
