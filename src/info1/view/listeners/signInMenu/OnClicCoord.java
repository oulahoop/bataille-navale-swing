package info1.view.listeners.signInMenu;

import info1.Application;
import info1.ships.*;
import info1.utils.GameManager;
import info1.view.menus.SignInMenu;

import javax.swing.*;
import javax.swing.text.IconView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnClicCoord implements ActionListener {

    private SignInMenu fenetre;
    private OnPlacerAction placement;
    private static int id = 0;


    /**
     * Listener permettant de placer un bateau dès lorsqu'un joueur clique sur un bouton
     * @param sim la fenetre pour modifier la vue
     * @param opa le listener pour récupérer le bateau à placer et sa position
     */
    public OnClicCoord(SignInMenu sim, OnPlacerAction opa){
        fenetre = sim;
        placement = opa;
    }

    /**
     * Listener qui récupère le clique sur un bouton du plateau de jeu et place le bateau si possible
     * @param e l'action récupéré
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //Pour éviter les bugs de fleet null, si elle vaut null : on en créer une nouvelle
        if (GameManager.getFleet() == null){ GameManager.setFleet(new NavyFleet()); }

        //Si elle n'est pas complète alors on peut ajouter le bateau
        if (!GameManager.getFleet().isComplete()){
            //On stock les informations du bateau à placer et de la coordonnée xy du bouton (qui sera la coordonnée de début du bateau)
            String xy = ((JButton)e.getSource()).getName();
            String nameBateau = placement.getBateau_a_placer();

            //On appelle la fonction pour placer le bateau
            try {
                this.BateauAPlacer(nameBateau, xy);
            } catch (BadCoordException | CoordsBadShipException badCoordException) {
                //Si on ne peut pas placer le bateau, alors un message d'erreur apparait
                JOptionPane.showMessageDialog(Application.getApp().getViewManager(),"Erreur de placement de bateau !","Erreur",JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    /**
     * Methode permettant de placer un bateau en fonction de son nom, sa coordonnée de début et de son orientation
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
        //A l'ajout du bateau, nous regardons s'il y a eu un problème de placement
        int error;

        id++;
        switch(name.toLowerCase()){
            //Si le nom du bouton placer est porte-avion (donc si le joueur veut placer un porte-avion)
            case "porte-avion" :
                //Si on a la possibilité de placer le bateau
                if(GameManager.getFleet().getShips(ShipCategory.AIRCRAFT_CARRIER).size()<1 && fenetre.getIsFrench()) {
                    //On calcul la coordonné de fin
                    coordFin = calculCoordFin(coord, fenetre.getAircraftSens(), 5);

                    //On ajoute le bateau à la fleet en stockant le retour au cas ou il y aurait une erreur
                    error = GameManager.getFleet().add(new AircraftCarrier("nom" + id,coord,coordFin));
                    //S'il n'y a pas d'erreur ont le place sur les boutons, sinon on affiche un message d'erreur
                    if (error==0) fenetre.placeShip((new AircraftCarrier("nom"+ id,coord,coordFin)).getCoords());
                    else JOptionPane.showMessageDialog(Application.getApp().getViewManager(), "Vos bateaux s'interchoque CHEF ! Choisissez une autre position", "Erreur de placement", JOptionPane.ERROR_MESSAGE);

                    //Si la taille maximal de cette catégorie de bateau a été atteinte, on enleve la possibilité de cliquer sur le bouton
                    if(GameManager.getFleet().getShips(ShipCategory.AIRCRAFT_CARRIER).size()==1){fenetre.setEnableAircraft(false);}
                }
                break;
            //Pareil que pour les portes-avions mais avec les différents bateaux tout en calculant les nécéssités pour les flottes belges ou française
            case "cuirassé":
                if (GameManager.getFleet().getShips(ShipCategory.BATTLESHIP).size()<1) {
                    coordFin = calculCoordFin(coord, fenetre.getBattleShipSens(), 4);
                    error = GameManager.getFleet().add(new Battleship("nom"+ id, coord, coordFin));
                    if (error==0) fenetre.placeShip((new Battleship("nom"+ id,coord,coordFin)).getCoords());
                    else JOptionPane.showMessageDialog(Application.getApp().getViewManager(), "Vos bateaux s'interchoque CHEF ! Choisissez une autre position", "Erreur de placement", JOptionPane.ERROR_MESSAGE);
                    if(GameManager.getFleet().getShips(ShipCategory.BATTLESHIP).size()==1){fenetre.setEnableBattleShip(false);}
                }
                break;
            case "croiseur":
                if(GameManager.getFleet().getShips(ShipCategory.CRUISER).size()<2) {
                    coordFin = calculCoordFin(coord, fenetre.getCruiserSens(), 3);
                    error = GameManager.getFleet().add(new Cruiser("nom"+ id, coord, coordFin));
                    if (error==0) fenetre.placeShip((new Cruiser("nom"+ id,coord,coordFin)).getCoords());
                    else JOptionPane.showMessageDialog(Application.getApp().getViewManager(), "Vos bateaux s'interchoque CHEF ! Choisissez une autre position", "Erreur de placement", JOptionPane.ERROR_MESSAGE);
                    if(GameManager.getFleet().getShips(ShipCategory.CRUISER).size()==2){fenetre.setEnableCruiser(false);}
                }
                break;
            case"torpilleur":
                number = fenetre.getIsFrench() ? 2 : 3;
                if(GameManager.getFleet().getShips(ShipCategory.DESTROYER).size()<number) {
                    coordFin = calculCoordFin(coord, fenetre.getDestroyerSens(), 2);
                    error = GameManager.getFleet().add(new Destroyer("nom"+ id, coord, coordFin));
                    if (error==0) fenetre.placeShip((new Destroyer("nom"+ id,coord,coordFin)).getCoords());
                    else JOptionPane.showMessageDialog(Application.getApp().getViewManager(), "Vos bateaux s'interchoque CHEF ! Choisissez une autre position", "Erreur de placement", JOptionPane.ERROR_MESSAGE);
                    if(GameManager.getFleet().getShips(ShipCategory.DESTROYER).size()==number){fenetre.setEnableDestroyer(false);};
                }
                break;
            case"sous-marin": ;
                number = fenetre.getIsFrench() ? 1 : 4;
                if(GameManager.getFleet().getShips(ShipCategory.SUBMARINE).size()<number) {
                    error = GameManager.getFleet().add(new Submarine("nom"+ id,coord));
                    if (error==0) fenetre.placeShip((new Submarine("nom"+ id,coord)).getCoords());
                    else JOptionPane.showMessageDialog(Application.getApp().getViewManager(), "Vos bateaux s'interchoque CHEF ! Choisissez une autre position", "Erreur de placement", JOptionPane.ERROR_MESSAGE);
                    if(GameManager.getFleet().getShips(ShipCategory.SUBMARINE).size()==number){fenetre.setEnableSubmarin(false);}
                }
                break;
        }

    }


    /**
     * Methode privée permettant à partir d'une coordonnée de départ, d'une orientation et d'une taille de calculer la coordonnée de fin
     * @param coord coordonnée du début du bateau
     * @param rotation sens dans lequel placer le bateau
     * @param size taille du bateau à placer
     * @return
     */
    private String calculCoordFin(String coord, boolean rotation,int size) {
        //Si rotation est true, c'est donc un placement horizontal
        if (rotation) {
            //On change donc la lettre dans le positionnement en ajoutant la size-1 du bateau au caractère ASCII
            char a = ((char) ((int) coord.charAt(0) + size - 1));

            //On récupère la position y de la coordonnée en regardant s'il y a 10 sans faire de StringIndexOutOfBoundsException
            char b = coord.charAt(1);
            String s = coord.length() > 2 ? String.valueOf(coord.charAt(2)) : "";
            return String.valueOf(a) + String.valueOf(b) + s;
        } else { //Sinon, c'est vertical
            //On récupère le caractère de la ligne (coordonnée X)
            char a2 = coord.charAt(0);
            //On modifie la coordonnée Y en enlevant bien la taille-1 du bateau à placer (car placement vers le "haut" de la grille)
            String b2 = String.valueOf(coord.charAt(1)) + String.valueOf(coord.length() > 2 ? coord.charAt(2) : "");
            b2 = String.valueOf(Integer.parseInt(b2) - size + 1);
            return String.valueOf(a2) + b2;
        }
    }

}
