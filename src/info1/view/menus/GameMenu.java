package info1.view.menus;


import info1.ships.ICoord;
import info1.ships.IShip;
import info1.ships.Ship;
import info1.view.Menu;
import info1.view.ViewManager;
import info1.utils.GameManager;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import info1.Application;
import info1.view.listeners.gameMenu.ExitListener;
import info1.view.listeners.gameMenu.ListenerTir;

/**
 * Mise en place de l'interface de jeu. Contient la grille de flotte et la  grille de tir.
 */

public class GameMenu{

    //mise en place des composants//
    JPanel principal;
    JPanel droite;
    JPanel gauche;
    JPanel interfaceJoueur;
    JPanel interfaceAdversaire;
    JLabel contour;
    //---------------------------//

    //grille joueur//
    JPanel grilleJoueur;
    JButton boutonJoueur;
    List<JButton> boutons = new ArrayList();

    //--------------------------//

    //grille adversaire//
    JPanel grilleAdversaire;
    JButton boutonAdversaire;
    //-------------------------//

    //interface de tir//
    JPanel interfaceTir;
    JLabel rappelDernierTir;
    JButton nouveauTir;
    //-------------------------------//
    JPanel exitpanel = new JPanel(new BorderLayout());
    JButton exit = new JButton("quitter");

    /**
     * Constructeur de la vue GameMenu, permettant la mise en place de l interface de jeu.
     * @param viewManager, le viewManager est la Frame commune à toutes nos fenêtres.
     */
    public GameMenu(ViewManager viewManager){

        //settings du panel principal//
        principal = new JPanel(new GridLayout(1,2));
        principal.setBorder(BorderFactory.createTitledBorder("Battleship"));

        principal.setSize(viewManager.getSize());
        principal.setPreferredSize(principal.getSize());

        gauche = new JPanel(new FlowLayout(FlowLayout.CENTER));
        interfaceJoueur = new JPanel(new BorderLayout());

        droite = new JPanel(new FlowLayout(FlowLayout.CENTER));
        interfaceAdversaire = new JPanel(new BorderLayout());

        //settings grille joueur//
        grilleJoueur = new JPanel(new GridLayout(11,11));
        grilleJoueur.setBorder(BorderFactory.createTitledBorder("Votre flotte"));

        //ajout des lettres en haut des colonnes//
        grilleJoueur.add(new JLabel());
        for (int i = 0; i < 10; i++) {
            contour = new JLabel(String.valueOf((char)(65+i)),SwingConstants.CENTER);
            contour.setOpaque(true);
            grilleJoueur.add(contour);
        }
        for(int i = 0; i < 10; i++){
            grilleJoueur.add(new JLabel(String.valueOf(i+1), SwingConstants.CENTER));
            for(int j = 0;j<10;j++){
                boutonJoueur = new JButton();
                boutonJoueur.setBackground(new Color(0x78939A));
                boutonJoueur.setPreferredSize(new Dimension(50,50));
                int number = i+1;
                boutonJoueur.setName((char)(65+j)+ "" + number);
                boutonJoueur.setEnabled(false);
                boutons.add(boutonJoueur);
                grilleJoueur.add(boutonJoueur);
            }
        }

        //settings grille adversaire//
        grilleAdversaire = new JPanel(new GridLayout(11,11));
        grilleAdversaire.setBorder(BorderFactory.createTitledBorder("Cible"));

        grilleAdversaire.add(new JLabel());
        for (int i = 0; i < 10; i++) {
            contour = new JLabel(String.valueOf((char)(65+i)),SwingConstants.CENTER);
            contour.setOpaque(true);
            grilleAdversaire.add(contour);
        }
        ListenerTir controleur = new ListenerTir(this);
        for(int i = 0; i < 10; i++){
            grilleAdversaire.add(new JLabel(String.valueOf(i+1), SwingConstants.CENTER));
            for(int j = 0;j<10;j++){
                boutonAdversaire = new JButton();
                boutonAdversaire.setBackground(new Color(0x78939A));
                boutonAdversaire.setPreferredSize(new Dimension(50,50));
                int number = i+1;
                boutonAdversaire.setName((char)(65+j)+ "" + number);
                boutonAdversaire.addActionListener(controleur);
                grilleAdversaire.add(boutonAdversaire);

            }
        }

        //Menu de tir//
        interfaceTir = new JPanel(new GridLayout(1,3));
        //interfaceTir.setBorder(BorderFactory.createTitledBorder("Interface de tir"));
        rappelDernierTir = new JLabel("dernier tir : ");
        nouveauTir = new JButton("FEU!");
        nouveauTir.setBackground(new Color(0xff0000));
        nouveauTir.setEnabled(false);
        nouveauTir.setName("Tirer");
        nouveauTir.addActionListener(controleur);
        nouveauTir.setPreferredSize(new Dimension(principal.getWidth()/10, principal.getHeight()/10));

        interfaceTir.add(rappelDernierTir);
        interfaceTir.add(new JPanel());
        interfaceTir.add(nouveauTir);

        //quitter

        exit.setPreferredSize(new Dimension(principal.getWidth()/10, principal.getHeight()/10));
        exit.addActionListener(new ExitListener(viewManager));
        exitpanel.add(exit, BorderLayout.WEST);

        interfaceJoueur.add(exitpanel, BorderLayout.SOUTH);


        //mise en place de la vue//
        interfaceJoueur.add(grilleJoueur,BorderLayout.CENTER);
        gauche.add(interfaceJoueur);
        interfaceAdversaire.add(grilleAdversaire,BorderLayout.CENTER);
        interfaceAdversaire.add(interfaceTir, BorderLayout.SOUTH);
        droite.add(interfaceAdversaire);
        principal.add(gauche);
        principal.add(droite);
        viewManager.setContentPane(principal);

        placerBateaux();

        waiting();


        viewManager.pack();
        viewManager.setResizable(false);
        viewManager.setVisible(true);
        viewManager.repaint();

    }

    /**
     * Méthode permettant de récupérer l'info de tour pour savoir quand le joueur peut jouer.
     * Cela permet d'empêcher au joueur de tirer à l'infini.
     */
    public void waiting() {
        nouveauTir.setEnabled(false);
        nouveauTir.setBackground(new Color(0x7a7a7a));

        CompletableFuture.runAsync(() -> {

            while(!(GameManager.canPlay() || GameManager.gameLost())) {
                synchronized(this) {
                    try {
                        wait(500);
                    } catch(InterruptedException ex) { ex.printStackTrace(); }
                }
            }
            if(GameManager.gameLost()){
                JOptionPane.showMessageDialog(Application.getApp().getViewManager(), "Nous sommes en déroute, nous devons vite quitter le champ de bataille");
                Application.getApp().getViewManager().switchTo(Menu.MAIN);
            }

            nouveauTir.setEnabled(true);
            nouveauTir.setBackground(new Color(0xff0000));
        });
    }

    /**
     * Permet de désassocier le listener du bouton courant en cas de tir réussi.
     * @param selected, le bouton séléctionné sur la grille.
     */
    public void hit(JButton selected) {
        selected.removeActionListener(selected.getActionListeners()[0]);
    }
    public void placerBateaux(){
        for (IShip ship : GameManager.getFleet().getShips()) {
            for (ICoord coord : ship.getCoords()) {
                for (JButton bouton : boutons){
                    if(bouton.getName().equals(coord.toString())){
                        bouton.setBackground(Color.BLACK);
                    }
                }
            }
        }
    }
}
