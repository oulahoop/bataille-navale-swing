package info1.view.listeners.signInMenu;

import com.mashape.unirest.http.exceptions.UnirestException;
import info1.Application;
import info1.network.Network;
import info1.network.Player;
import info1.ships.NavyFleet;
import info1.utils.GameManager;
import info1.view.Menu;
import info1.view.menus.MainMenu;
import info1.view.menus.SignInMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class OnActionEvent implements ActionListener {

    private SignInMenu fenetre;
    private static boolean francais = true;


    /**
     * Constructeur du listener OnActionEvent
     * @param sim SignInMenu la fenetre à modifier
     */
    public OnActionEvent(SignInMenu sim){
        fenetre = sim;
    }


    /**
     * Si la composition choisi est française ou non
     * @return true si elle est français, false si non
     */
    public static boolean isFrancais() {
        return francais;
    }

    /**
     * Sur l'événement d'un bouton pressé, il change la composition si
     * c'est un de ces boutons cliqués
     * ou il lance la vue suivante si le bouton jouer est cliqué
     * @param e L'action performé sur le bouton (clique)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Application app = Application.getApp();
        JButton boutton = ((JButton)e.getSource());
        String nameButton = boutton.getName();



        //Si la perseonne veut une composition belge
        if(nameButton.equalsIgnoreCase("Belge")){
            fenetre.enableFrench(true);
            fenetre.enableBelgium(false);

            //On retire tout dans l'affichage des bateaux, dans les listes et on fait une nouvelle flotte pour enlever la précédente
            fenetre.getBateauxPanel().removeAll();
            fenetre.setPlacerButtons(new ArrayList<>());
            fenetre.setRotation(new ArrayList<>());
            GameManager.setFleet(new NavyFleet());
            francais=false;

            //On "refait" l'affichage mais en version belge
            String[] boatName = new String[]{"Battleship","Cruiser","Destroyer","Submarine"};
            for(int i = 0; i<4;i++){
                JPanel jp = new JPanel();
                jp.setPreferredSize(new Dimension(550,75));
                //jp.setBackground(Color.BLACK);
                ImageIcon ship = new ImageIcon("lib/belge/"+(i+1)+".png");
                JLabel jl = new JLabel(ship);
                jl.setText(boatName[i]);
                jl.setName(boatName[i]);
                jp.add(jl);
                JComboBox<String> jcb= new JComboBox<>();
                jcb.addItem("Haut");
                jcb.addItem("Droite");
                jcb.addItem("Gauche");
                jcb.addItem("Bas");
                jcb.setName(boatName[i]);
                fenetre.getRotation().add(jcb);
                jp.add(jcb);
                JButton jb = new JButton("Placer");
                jb.setName(boatName[i]);
                fenetre.getPlacerButtons().add(jb);
                jb.addActionListener(fenetre.getCtrl());
                jp.add(jb);

                fenetre.getBateauxPanel().add(jp);
            }
            //On clear les background des boutons au cas ou le joueur à ajouter des bateaux avant de changer de composition
            for(JButton b : fenetre.getButtons()){
                b.setBackground(new Color(0x78939A));
            }
            //On update la vue pour que ça s'affiche
            app.getViewManager().update();
        }

        //Si la personne veut une composition française
        if(nameButton.equalsIgnoreCase("Français")){
            fenetre.enableBelgium(true);
            fenetre.enableFrench(false);
            //On retire tout du JPanel des bateaux à placer, des lists de bouton placé/des rotations et on refait une nouvelle flotte dans GameManager
            fenetre.getBateauxPanel().removeAll();
            fenetre.setPlacerButtons(new ArrayList<>());
            fenetre.setRotation(new ArrayList<>());
            francais = true;
            GameManager.setFleet(new NavyFleet());

            //On "refait" l'affichage en version française
            String[] boatName = new String[]{"AircraftCarrier","BattleShip","Cruiser","Destroyer","Submarine"};
            for(int i = 0; i<5;i++){
                JPanel jp = new JPanel();
                jp.setPreferredSize(new Dimension(550,75));
                //jp.setBackground(Color.BLACK);
                ImageIcon ship = new ImageIcon("lib/francais/"+(i+1)+".png");
                JLabel jl = new JLabel(ship);
                jl.setText(boatName[i]);
                jl.setName(boatName[i]);
                jp.add(jl);
                JComboBox<String> jcb= new JComboBox<>();
                jcb.addItem("Haut");
                jcb.addItem("Droite");
                jcb.addItem("Gauche");
                jcb.addItem("Bas");
                jcb.setName(boatName[i]);
                fenetre.getRotation().add(jcb);
                jp.add(jcb);
                JButton jb = new JButton("Placer");
                jb.setName(boatName[i]);
                System.out.println(jb.getName());
                fenetre.getPlacerButtons().add(jb);
                jb.addActionListener(fenetre.getCtrl());
                jp.add(jb);
                fenetre.getBateauxPanel().add(jp);
            }
            //On clear le background des buttons si des bateaux avaient été placé avant le changement de composition
            for(JButton b : fenetre.getButtons()){
                b.setBackground(new Color(0x78939A));
            }
            //On update le tout pour l'affichage
            app.getViewManager().update();
        }


        //Si la personne clique sur le bouton jouer
        if(nameButton.equalsIgnoreCase("Jouer")){
            //Il faut qu'il est mis un nom de joueur
            if(!fenetre.getName().getText().equalsIgnoreCase("")) {
                //Il faut qu'il est sa flotte pleine
                if (GameManager.getFleet().isComplete()) {
                    Player player = new Player(fenetre.getName().getText());
                    try {
                        if (Network.suscribeNewPlayer(GameManager.getUrl(), player)){
                            GameManager.setPlayer(player);
                            app.getViewManager().switchTo(Menu.MAIN);
                        }
                    } catch (UnirestException unirestException) {
                        System.out.println(unirestException.getMessage());
                    }
                }else{
                    app.getViewManager().alert("Veuillez finir votre flotte ! ");
                }
            }else{
                app.getViewManager().alert("Veuillez insérer un pseudo !");
            }
        }
    }
}
