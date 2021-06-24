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


    /**
     * Listener permettant de placer un bateau dès lorsqu'un joueur clique sur un bouton
     * @param sim la fenetre pour modifier la vue
     * @param opa le listener pour récupérer le bateau à placer et sa position
     */
    public OnClicCoord(SignInMenu sim, OnPlacerAction opa){
        fenetre = sim;
        placement = opa;
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //Pour éviter les bugs de fleet null, si elle vaut null : on en créer une nouvelle
        if (GameManager.getFleet() == null){ GameManager.setFleet(new NavyFleet()); }

        //Si elle n'est pas complète alors on peut ajouter le bateau
        if (!GameManager.getFleet().isComplete()){
            //On stock les informations du bateau à placer et la rotation sur le clic
            String xy = ((JButton)e.getSource()).getName();
            String nameBateau = placement.getBateau_a_placer();

            //On appelle la fonction pour placer le bateau
            try {
                this.BateauAPlacer(nameBateau, xy);
            } catch (BadCoordException | CoordsBadShipException badCoordException) {
                badCoordException.printStackTrace();
            }
        }
    }

    /**
     * Permet de placer un bateau en fonction de son nom, sa coordonnée de début et de son orientation
     * @param name nom du bateau à placer
     * @param coord coordonnée du début du bateau
     * @throws BadCoordException
     * @throws CoordsBadShipException
     */
    public void BateauAPlacer(String name,String coord) throws BadCoordException, CoordsBadShipException {

        //coordFin est les coordonnées de fin
        String coordFin;
        //number et le nombre max de bateau (il est différent si la flotte est belge ou française)
        int number;

        switch(name.toLowerCase()){

            //Si le nom du bouton placer est AircraftCarrier (donc si le joueur veut placer un aircraftcarrier
            case "porte-avion" :
                //Si on a la possibilité de placer le bateau
                if(GameManager.getFleet().getShips(ShipCategory.AIRCRAFT_CARRIER).size()<1 && fenetre.getIsFrench()) {
                    //On calcul la coordonné de fin
                    coordFin = calculCoordFin(coord, fenetre.getAircraftSens(), 5);
                    //On ajoute le bateau à la fleet
                    GameManager.getFleet().add(new AircraftCarrier("nom",coord,coordFin));
                    //Si la taille maximal de cette catégorie de bateau a été atteinte, on disable la possibilité de cliquer sur le bouton
                    if(GameManager.getFleet().getShips(ShipCategory.AIRCRAFT_CARRIER).size()==1){fenetre.setEnableAircraft(false);}
                    //on fait l'affichage du bateau sur les boutons
                    fenetre.placeShip((new AircraftCarrier("nom",coord,coordFin)).getCoords());
                }
                break;
            //Pareil que pour aircraftcarrier
            case "cuirassé":
                if (GameManager.getFleet().getShips(ShipCategory.BATTLESHIP).size()<1) {
                    coordFin = calculCoordFin(coord, fenetre.getBattleShipSens(), 4);
                    GameManager.getFleet().add(new Battleship("nom", coord, coordFin));
                    if(GameManager.getFleet().getShips(ShipCategory.BATTLESHIP).size()==1){fenetre.setEnableBattleShip(false);}
                    fenetre.placeShip((new Battleship("nom",coord,coordFin)).getCoords());
                }
                break;
            case "croiseur":
                if(GameManager.getFleet().getShips(ShipCategory.CRUISER).size()<2) {
                    coordFin = calculCoordFin(coord, fenetre.getCruiserSens(), 3);
                    GameManager.getFleet().add(new Cruiser("nom", coord, coordFin));
                    if(GameManager.getFleet().getShips(ShipCategory.CRUISER).size()==2){fenetre.setEnableCruiser(false);}
                    fenetre.placeShip((new Cruiser("nom",coord,coordFin)).getCoords());
                }
                break;
            case"torpilleur":
                number = fenetre.getIsFrench() ? 2 : 3;
                if(GameManager.getFleet().getShips(ShipCategory.DESTROYER).size()<number) {
                    coordFin = calculCoordFin(coord, fenetre.getDestroyerSens(), 2);
                    GameManager.getFleet().add(new Destroyer("nom", coord, coordFin));
                    if(GameManager.getFleet().getShips(ShipCategory.DESTROYER).size()==number){fenetre.setEnableDestroyer(false);};
                    fenetre.placeShip((new Destroyer("nom",coord,coordFin)).getCoords());
                }
                break;
            case"sous-marin": ;
                number = fenetre.getIsFrench() ? 1 : 4;
                if(GameManager.getFleet().getShips(ShipCategory.SUBMARINE).size()<number) {
                    GameManager.getFleet().add(new Submarine("nom",coord));
                    if(GameManager.getFleet().getShips(ShipCategory.SUBMARINE).size()==number){fenetre.setEnableSubmarin(false);}
                    fenetre.placeShip((new Submarine("nom",coord)).getCoords());
                }
                break;
        }

    }


    /**
     * Fonction privé permettant à partir d'une coordonnée de départ, d'une orientation et d'une taille la coordonnée de fin
     * @param coord coordonnée du début du bateau
     * @param rotation sens dans lequel placer le bateau
     * @param size taille du bateau à placer
     * @return
     */
    private String calculCoordFin(String coord, boolean rotation,int size) {
        //Calcul du placement de la coordonnée de fin en fonction de la rotation choisi
        //LETTRE
        //Lorsqu'on va vers la gauche ou la droite, c'est le caractère qui change (par exemple si la coordonnée de début est A1, pour une rotation droite,
        //avec un aircraftcarrier (donc size de 5)
        if (rotation) { //Horizontal**e**
            char a = ((char) ((int) coord.charAt(0) + size - 1));
            char b = coord.charAt(1);
            String s = coord.length() > 2 ? String.valueOf(coord.charAt(2)) : "";
            return String.valueOf(a) + String.valueOf(b) + String.valueOf(s);
        } else { //Vertical
            char a2 = coord.charAt(0);
            String b2 = String.valueOf(coord.charAt(1)) + String.valueOf(coord.length() > 2 ? coord.charAt(2) : "");
            b2 = String.valueOf(Integer.parseInt(b2) - size + 1);
            return String.valueOf(a2) + String.valueOf(b2);
        }
    }

}
