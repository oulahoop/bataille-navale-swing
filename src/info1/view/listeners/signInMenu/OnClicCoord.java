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
            String nameRotation = placement.getRotation_bateau();

            //On appelle la fonction pour placer le bateau
            try {
                this.BateauAPlacer(nameBateau, xy, nameRotation);
            } catch (BadCoordException | CoordsBadShipException badCoordException) {
                badCoordException.printStackTrace();
            }
        }
    }

    /**
     * Permet de placer un bateau en fonction de son nom, sa coordonnée de début et de son orientation
     * @param name nom du bateau à placer
     * @param coord coordonnée du début du bateau
     * @param placement l'orientation vers laquelle placer le bateau en fonction du début
     * @throws BadCoordException
     * @throws CoordsBadShipException
     */
    public void BateauAPlacer(String name,String coord,String placement) throws BadCoordException, CoordsBadShipException {

        //coordFin est les coordonnées de fin
        String coordFin;
        //number et le nombre max de bateau (il est différent si la flotte est belge ou française)
        int number;

        switch(name.toLowerCase()){
            //Si le nom du bouton placer est AircraftCarrier (donc si le joueur veut placer un aircraftcarrier
            case "aircraftcarrier" :
                //Si on a la possibilité de placer le bateau
                if(GameManager.getFleet().getShips(ShipCategory.AIRCRAFT_CARRIER).size()<1 && OnActionEvent.isFrancais()) {
                    //On calcul la coordonné de fin
                    coordFin = calculCoordFin(coord,placement,5);
                    //On ajoute le bateau à la fleet
                    boolean gauche = placement.equalsIgnoreCase("gauche");
                    GameManager.getFleet().add(new AircraftCarrier("nom", !gauche ? coord : coordFin, !gauche ? coordFin : coord));
                    //Si la taille maximal de cette catégorie de bateau a été atteinte, on disable la possibilité de cliquer sur le bouton
                    if(GameManager.getFleet().getShips(ShipCategory.AIRCRAFT_CARRIER).size()==1){fenetre.getPlacerButtons().get(0).setEnabled(false);}
                    //on fait l'affichage du bateau sur les boutons
                    affichageButton();
                }
                break;
            //Pareil que pour aircraftcarrier
            case "battleship":
                if (GameManager.getFleet().getShips(ShipCategory.BATTLESHIP).size()<1) {
                    coordFin = calculCoordFin(coord,placement,4);
                    GameManager.getFleet().add(new Battleship("nom", coord, coordFin));
                    if(GameManager.getFleet().getShips(ShipCategory.BATTLESHIP).size()==1){fenetre.getPlacerButtons().get(OnActionEvent.isFrancais() ? 1 : 0).setEnabled(false);}
                    affichageButton();
                }
                break;
            case "cruiser":
                if(GameManager.getFleet().getShips(ShipCategory.CRUISER).size()<2) {
                    coordFin = calculCoordFin(coord,placement,3);
                    GameManager.getFleet().add(new Cruiser("nom", coord, coordFin));
                    if(GameManager.getFleet().getShips(ShipCategory.CRUISER).size()==2){fenetre.getPlacerButtons().get(OnActionEvent.isFrancais() ? 2 : 1).setEnabled(false);}
                    affichageButton();
                }
                break;
            case"destroyer":
                number = OnActionEvent.isFrancais() ? 2 : 3;
                if(GameManager.getFleet().getShips(ShipCategory.DESTROYER).size()<number) {
                    coordFin = calculCoordFin(coord,placement,2);
                    GameManager.getFleet().add(new Destroyer("nom", coord, coordFin));
                    if(GameManager.getFleet().getShips(ShipCategory.DESTROYER).size()==number){fenetre.getPlacerButtons().get(OnActionEvent.isFrancais() ? 3 : 2).setEnabled(false);};
                    affichageButton();
                }
                break;
            case"submarine": ;
                number = OnActionEvent.isFrancais() ? 1 : 4;
                if(GameManager.getFleet().getShips(ShipCategory.SUBMARINE).size()<number) {
                    GameManager.getFleet().add(new Submarine("nom",coord));
                    if(GameManager.getFleet().getShips(ShipCategory.SUBMARINE).size()==number){fenetre.getPlacerButtons().get(OnActionEvent.isFrancais() ? 4 : 3).setEnabled(false);}
                    affichageButton();
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
    private String calculCoordFin(String coord, String rotation,int size){
        //Calcul du placement de la coordonnée de fin en fonction de la rotation choisi
        switch (rotation.toLowerCase()){
            //LETTRE
            //Lorsqu'on va vers la gauche ou la droite, c'est le caractère qui change (par exemple si la coordonnée de début est A1, pour une rotation droite,
            //avec un aircraftcarrier (donc size de 5)
            case "droite" :
                char a = ((char) ((int) coord.charAt(0)+size-1));
                char b = coord.charAt(1);
                String s = coord.length()>2 ? String.valueOf(coord.charAt(2)) : "";
                return String.valueOf(a)+String.valueOf(b)+String.valueOf(s);
            case "gauche" : //Bug ??
                char a1 = ((char) ((int) coord.charAt(0)-size+1));
                char b1 = coord.charAt(1);
                String s1 = coord.length()>2 ? String.valueOf(coord.charAt(2)) : "";
                return String.valueOf(a1) + String.valueOf(b1) + s1;
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

    /**
     * Methode privée permettant d'affichier en couleur (Noir ici) le bateau
     */
    private void affichageButton(){
        for (IShip s : GameManager.getFleet().getShips()) {
            for (ICoord c : s.getCoords()) {
                fenetre.getButtons().get(c.getX() - 1 + (c.getY() - 1) * 10).setBackground(Color.BLACK);
            }
        }
    }
}
