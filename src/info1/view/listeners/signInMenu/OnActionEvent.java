package info1.view.listeners.signInMenu;

import com.mashape.unirest.http.exceptions.UnirestException;
import info1.Application;
import info1.network.Network;
import info1.network.Player;
import info1.ships.NavyFleet;
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

    public OnActionEvent(SignInMenu sim){
        fenetre = sim;
    }

    public static boolean isFrancais() {
        return francais;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Application app = Application.getApp();
        JButton boutton = ((JButton)e.getSource());
        String nameButton = boutton.getName();
        if(nameButton.equalsIgnoreCase("Belge")){
            fenetre.enableFrench(true);
            fenetre.enableBelgium(false);
            fenetre.getBateauxPanel().removeAll();
            francais=false;
            app.getGameManager().setFleet(new NavyFleet());
            fenetre.setPlacerButtons(new ArrayList<>());
            fenetre.setRotation(new ArrayList<>());
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
            for(JButton b : fenetre.getButtons()){
                b.setBackground(new Color(0x78939A));
            }
            app.getViewManager().update();
        }

        if(nameButton.equalsIgnoreCase("Français")){
            fenetre.enableBelgium(true);
            fenetre.enableFrench(false);
            fenetre.getBateauxPanel().removeAll();
            fenetre.setPlacerButtons(new ArrayList<>());
            fenetre.setRotation(new ArrayList<>());
            francais = true;
            app.getGameManager().setFleet(new NavyFleet());
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
            for(JButton b : fenetre.getButtons()){
                b.setBackground(new Color(0x78939A));
            }
            app.getViewManager().update();
        }

        if(nameButton.equalsIgnoreCase("Jouer")){
            if(!fenetre.getName().getText().equalsIgnoreCase("")) {
                if (app.getGameManager().getFleet().isComplete()) {
                    Player player = new Player(fenetre.getName().getText());
                    try {
                        if (Network.suscribeNewPlayer(app.getGameManager().getUrl(), player)){
                            app.getGameManager().setPlayer(player);
                            app.getViewManager().switchTo(Menu.MAIN);
                    }
                    } catch (UnirestException unirestException) {
                        System.out.println(unirestException.getMessage());
                    }
                }else{
                    app.getViewManager().alert("Veuillez finir votre flotte ! ", false);
                }
            }else{
                app.getViewManager().alert("Veuillez insérer un pseudo !", false);
            }

        }
    }
}
