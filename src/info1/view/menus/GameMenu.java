package info1.view.menus;


import info1.view.ViewManager;
import info1.utils.GameManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.concurrent.CompletableFuture;

import info1.Application;
import info1.view.listeners.gameMenu.ListenerTir;


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

    public GameMenu(ViewManager viewManager){

        //settings du panel principal//
        principal = new JPanel(new GridLayout(1,2));
        principal.setBorder(BorderFactory.createTitledBorder("Battleship"));
        gauche = new JPanel(new FlowLayout(FlowLayout.CENTER));
        interfaceJoueur = new JPanel(new BorderLayout());

        droite = new JPanel(new FlowLayout(FlowLayout.CENTER));
        interfaceAdversaire = new JPanel(new BorderLayout());
        //settings grille joueur//
        grilleJoueur = new JPanel(new GridLayout(11,11));
        grilleJoueur.setBorder(BorderFactory.createTitledBorder("Votre flotte"));

        grilleJoueur.add(new JLabel());
        //ajout des lettres en haut des colonnes//
        contour = new JLabel("--A--",SwingConstants.CENTER);
        contour.setOpaque(true);
        grilleJoueur.add(contour);
        contour = new JLabel("--B--",SwingConstants.CENTER);
        contour.setOpaque(true);
        grilleJoueur.add(contour);
        contour = new JLabel("--C--",SwingConstants.CENTER);
        contour.setOpaque(true);
        grilleJoueur.add(contour);
        contour = new JLabel("--D--",SwingConstants.CENTER);
        contour.setOpaque(true);
        grilleJoueur.add(contour);
        contour = new JLabel("--E--",SwingConstants.CENTER);
        contour.setOpaque(true);
        grilleJoueur.add(contour);
        contour = new JLabel("--F--",SwingConstants.CENTER);
        contour.setOpaque(true);
        grilleJoueur.add(contour);
        contour = new JLabel("--G--",SwingConstants.CENTER);
        contour.setOpaque(true);
        grilleJoueur.add(contour);
        contour = new JLabel("--H--",SwingConstants.CENTER);
        contour.setOpaque(true);
        grilleJoueur.add(contour);
        contour = new JLabel("--I--",SwingConstants.CENTER);
        contour.setOpaque(true);
        grilleJoueur.add(contour);
        contour = new JLabel("--J--",SwingConstants.CENTER);
        contour.setOpaque(true);
        grilleJoueur.add(contour);

        for(int i = 0; i < 10; i++){
            grilleJoueur.add(new JLabel("--" +String.valueOf(i+1) + "--", SwingConstants.CENTER));
            for(int j = 0;j<10;j++){
                boutonJoueur = new JButton();
                boutonJoueur.setBackground(new Color(0x78939A));
                boutonJoueur.setPreferredSize(new Dimension(50,50));
                int number = i+1;
                boutonJoueur.setName((char)(65+j)+ "" + number);
                boutonJoueur.setEnabled(false);
                grilleJoueur.add(boutonJoueur);
            }
        }

        //settings grille adversaire//
        grilleAdversaire = new JPanel(new GridLayout(11,11));
        grilleAdversaire.setBorder(BorderFactory.createTitledBorder("Cible"));

        grilleAdversaire.add(new JLabel());
        contour = new JLabel("--A--",SwingConstants.CENTER);
        contour.setOpaque(true);
        grilleAdversaire.add(contour);
        contour = new JLabel("--B--",SwingConstants.CENTER);
        contour.setOpaque(true);
        grilleAdversaire.add(contour);
        contour = new JLabel("--C--",SwingConstants.CENTER);
        contour.setOpaque(true);
        grilleAdversaire.add(contour);
        contour = new JLabel("--D--",SwingConstants.CENTER);
        contour.setOpaque(true);
        grilleAdversaire.add(contour);
        contour = new JLabel("--E--",SwingConstants.CENTER);
        contour.setOpaque(true);
        grilleAdversaire.add(contour);
        contour = new JLabel("--F--",SwingConstants.CENTER);
        contour.setOpaque(true);
        grilleAdversaire.add(contour);
        contour = new JLabel("--G--",SwingConstants.CENTER);
        contour.setOpaque(true);
        grilleAdversaire.add(contour);
        contour = new JLabel("--H--",SwingConstants.CENTER);
        contour.setOpaque(true);
        grilleAdversaire.add(contour);
        contour = new JLabel("--I--",SwingConstants.CENTER);
        contour.setOpaque(true);
        grilleAdversaire.add(contour);
        contour = new JLabel("--J--",SwingConstants.CENTER);
        contour.setOpaque(true);
        grilleAdversaire.add(contour);
        ListenerTir controleur = new ListenerTir(this);
        for(int i = 0; i < 10; i++){
            grilleAdversaire.add(new JLabel("--" +String.valueOf(i+1) + "--", SwingConstants.CENTER));
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
        interfaceTir = new JPanel(new GridLayout(2,1));
        interfaceTir.setBorder(BorderFactory.createTitledBorder("Interface de tir"));
        rappelDernierTir = new JLabel("dernier tir : ");
        nouveauTir = new JButton("FEU!");
        nouveauTir.setBackground(new Color(0xff0000));
        nouveauTir.setEnabled(false);
        nouveauTir.setName("Tirer");
        nouveauTir.addActionListener(controleur);
        interfaceTir.add(rappelDernierTir);
        interfaceTir.add(nouveauTir);

        //mise en place de la vue//
        interfaceJoueur.add(grilleJoueur,BorderLayout.CENTER);
        gauche.add(interfaceJoueur);
        interfaceAdversaire.add(grilleAdversaire,BorderLayout.CENTER);
        interfaceAdversaire.add(interfaceTir, BorderLayout.SOUTH);
        droite.add(interfaceAdversaire);
        principal.add(gauche);
        principal.add(droite);
        viewManager.setContentPane(principal);

        nouveauTir.setEnabled(Application.getApp().getGameManager().canPlay());

        principal.setSize(viewManager.getWidth()-15,viewManager.getHeight()-45);
        principal.setPreferredSize(principal.getSize());
        viewManager.pack();
        viewManager.setResizable(false);
        viewManager.setVisible(true);
        viewManager.repaint();

        if(! Application.getApp().getGameManager().canPlay()){
            waiting();
        }
    }
    public void listenerTir(ActionListener action){nouveauTir.addActionListener(action);}

    public void setFire(boolean b) {
        nouveauTir.setEnabled(b);
    }
    public void waiting() {
        setFire(false);
        CompletableFuture.runAsync(() -> {
            GameManager gameManager = Application.getApp().getGameManager();

            while(!gameManager.canPlay()) {
                synchronized(this) {
                    try {
                        wait(500);
                    } catch(InterruptedException ex) { ex.printStackTrace(); }
                }
            }
            setFire(true);
        });
    }

    public void hit(JButton selected) {
        selected.removeActionListener(selected.getActionListeners()[0]);
    }
}
