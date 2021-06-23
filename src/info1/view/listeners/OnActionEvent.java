package info1.view.listeners;

import com.mashape.unirest.http.exceptions.UnirestException;
import info1.Application;
import info1.network.Network;
import info1.network.Player;
import info1.utils.GameManager;
import info1.view.menus.SignInMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnActionEvent implements ActionListener {

    private SignInMenu fenetre;

    public OnActionEvent(SignInMenu sim){
        fenetre = sim;
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
            String[] boatName = new String[]{"BattleShip","Cruiser","Destroyer","Submarine"};
            for(int i = 1; i<5;i++){
                ImageIcon ii = new ImageIcon("lib/belge/"+String.valueOf(i)+".png");
                JLabel jl = new JLabel(ii);
                jl.setText(boatName[i-1]);
                fenetre.getBateauxPanel().add(jl);
            }
            app.getViewManager().update();
        }
        if(nameButton.equalsIgnoreCase("Français")){
            fenetre.enableBelgium(true);
            fenetre.enableFrench(false);
            fenetre.getBateauxPanel().removeAll();
            String[] boatName = new String[]{"AirCraftCarrier","BattleShip","Cruiser","Destroyer","Submarine"};
            for(int i = 1; i<=5;i++){
                ImageIcon ii = new ImageIcon("lib/francais/"+String.valueOf(i)+".png");
                JLabel jl = new JLabel(ii);
                jl.setText(boatName[i-1]);
                fenetre.getBateauxPanel().add(jl);
            }
            app.getViewManager().update();
        }

        if(nameButton.equalsIgnoreCase("Jouer")){
            if(!fenetre.getName().getText().equalsIgnoreCase("")){
                //TODO verify fleet
                Player player = new Player(fenetre.getName().getText());
                try { if(Network.suscribeNewPlayer(GameManager.getUrl(), player)) GameManager.setPlayer(player);
                } catch (UnirestException unirestException) { unirestException.printStackTrace(); }
            }else{
                app.getViewManager().alert("Veuillez insérer un pseudo !", false);
            }

        }
    }
}
