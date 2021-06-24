package info1.view.listeners.signInMenu;

import com.mashape.unirest.http.exceptions.UnirestException;
import info1.Application;
import info1.network.Network;
import info1.network.Player;
import info1.ships.NavyFleet;
import info1.utils.GameManager;
import info1.view.Menu;
import info1.view.menus.SignInMenu;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        String nameButton = boutton.getName() == null ? boutton.getText() : boutton.getName();



        //Si la perseonne veut une composition belge
        if(nameButton.equalsIgnoreCase("Belge")){
            fenetre.setEnableBelgium(false);
            fenetre.setEnableFrench(true);
            GameManager.setFleet(new NavyFleet());
            fenetre.setIsFrench(false);
            fenetre.update();
            fenetre.reset();
            app.getViewManager().update();
            //On retire tout dans l'affichage des bateaux, dans les listes et on fait une nouvelle flotte pour enlever la précédente
            //On clear les background des boutons au cas ou le joueur à ajouter des bateaux avant de changer de composition

            //On update la vue pour que ça s'affiche
        }

        //Si la personne veut une composition française
        if(nameButton.equalsIgnoreCase("Francais")){
            fenetre.setEnableFrench(false);
            fenetre.setEnableBelgium(true);
            GameManager.setFleet(new NavyFleet());
            fenetre.setIsFrench(true);
            fenetre.update();
            fenetre.reset();
            app.getViewManager().update();
            }

        //Si la personne clique sur le bouton jouer
        if(nameButton.equalsIgnoreCase("Jouer")){
            //Il faut qu'il est mis un nom de joueur
            if(!fenetre.getPlayerName().equalsIgnoreCase("")) {
                //Il faut qu'il est sa flotte pleine
                if (GameManager.getFleet().isComplete()) {
                    Player player = new Player(fenetre.getPlayerName());
                    try {
                        if (Network.suscribeNewPlayer(GameManager.getUrl(), player)){
                            GameManager.setPlayer(player);
                            app.getViewManager().switchTo(Menu.MAIN);
                        }
                    } catch (UnirestException unirestException) {
                        System.out.println(unirestException.getMessage());
                    }
                }else{
                    JOptionPane.showMessageDialog(Application.getApp().getViewManager(), "Veuillez finir votre flotte ! ","Erreur de flotte",JOptionPane.ERROR_MESSAGE);
                }
            }else{
                JOptionPane.showMessageDialog(Application.getApp().getViewManager(),"Veuillez insérer un pseudo !","Erreur de pseudo",JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
