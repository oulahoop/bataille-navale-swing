package info1.view.listeners.signInMenu;

import com.mashape.unirest.http.exceptions.UnirestException;
import info1.Application;
import info1.network.Network;
import info1.network.Player;
import info1.ships.NavyFleet;
import info1.utils.GameManager;
import info1.view.Menu;
import info1.view.ViewManager;
import info1.view.menus.SignInMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnActionEvent implements ActionListener {

    private final SignInMenu fenetre;


    /**
     * Constructeur du listener OnActionEvent
     * @param sim SignInMenu la fenetre à modifier
     */
    public OnActionEvent(SignInMenu sim){
        fenetre = sim;
    }


    /**
     * Sur l'événement d'un bouton pressé, il change la composition si c'est un de ces boutons cliqués
     * ou il lance la vue suivante si le bouton jouer est cliqué et que les conditions sont respectées
     * @param e L'action performé sur le bouton (clique)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        //On récupère des variables pour simplifier la visibilité
        ViewManager app = Application.getApp().getViewManager();
        JButton boutton = ((JButton)e.getSource());
        String nameButton = boutton.getName() == null ? boutton.getText() : boutton.getName();



        //Si la perseonne veut une composition belge
        if(nameButton.equalsIgnoreCase("Belge")){

            //On met les boutons
            fenetre.setEnableBelgium(false);
            fenetre.setEnableFrench(true);

            //On clear l'ancienne flotte
            GameManager.setFleet(new NavyFleet());

            //On affiche la flotte belge
            fenetre.setIsFrench(false);
            //On reset la vue (le plateau de jeu, les boutons de placement..)
            fenetre.reset();

            //On update la vue
            fenetre.update();
            app.update();
        }

        //Si la personne veut une composition française
        if(nameButton.equalsIgnoreCase("Francais")){
            //On active et desactive les boutons de choix
            fenetre.setEnableFrench(false);
            fenetre.setEnableBelgium(true);

            //On clear l'ancienne flotte
            GameManager.setFleet(new NavyFleet());

            //On affiche la flotte française
            fenetre.setIsFrench(true);

            //On reset la vue (plateau de jeu, bouton de placement..)
            fenetre.reset();

            //On update la vue
            fenetre.update();
            app.update();
            }

        //Si la personne clique sur le bouton jouer
        if(nameButton.equalsIgnoreCase("Jouer")){
            //Il faut qu'il est mis un nom de joueur
            if(!fenetre.getPlayerName().equalsIgnoreCase("")) {
                //Il faut qu'il est sa flotte pleine
                if (GameManager.getFleet().isComplete()) {
                    Player player = new Player(fenetre.getPlayerName());
                    try {
                        if(!Network.listActivePlayers(GameManager.getUrl()).contains(player)) {
                                if (Network.suscribeNewPlayer(GameManager.getUrl(), player)) {
                                    GameManager.setPlayer(player);
                                    app.switchTo(Menu.MAIN);
                                }
                        }else{JOptionPane.showMessageDialog(app , "Ce pseudonyme est deja pris ","Erreur de pseudo",JOptionPane.ERROR_MESSAGE); }
                    } catch (UnirestException unirestException) {
                        unirestException.printStackTrace();
                    }
                }else{
                    JOptionPane.showMessageDialog(app , "Veuillez finir votre flotte ! ","Erreur de flotte",JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(app,"Veuillez insérer un pseudo !","Erreur de pseudo",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
