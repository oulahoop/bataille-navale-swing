package info1.view.menus;


import info1.view.ViewManager;

import javax.swing.*;
import java.awt.*;


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

    //état de la flotte//
    JPanel etatFlotte;

    //état porte-avions//
    JLabel PA1;
    JLabel PA2;
    JLabel PA3;
    JLabel PA4;
    JLabel PA5;

    //état cuirassé//
    JLabel cuira1;
    JLabel cuira2;
    JLabel cuira3;
    JLabel cuira4;

    //état croiseur//
    JLabel crois1;
    JLabel crois2;
    JLabel crois3;

    //état torpilleur//
    JLabel torpi1;
    JLabel torpi2;

    //état sous-marin//
    JLabel submarine;
    //-------------------------------//

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
                boutonJoueur.setName((char)(65+i)+ "" + j+1);
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

        for(int i = 0; i < 10; i++){
            grilleAdversaire.add(new JLabel("--" +String.valueOf(i+1) + "--", SwingConstants.CENTER));
            for(int j = 0;j<10;j++){
                boutonAdversaire = new JButton();
                boutonAdversaire.setBackground(new Color(0x78939A));
                boutonAdversaire.setPreferredSize(new Dimension(50,50));
                boutonAdversaire.setName((char)(65+i)+ "" + j+1);
                grilleAdversaire.add(boutonAdversaire);
            }
        }

        //settings menu etat de la flotte//
        etatFlotte = new JPanel(new GridLayout(2,9));
        etatFlotte.setBorder(BorderFactory.createTitledBorder("Etat de votre flotte"));
        //porte-avions//
        PA1 = new JLabel("P1",SwingConstants.CENTER);
        PA1.setBackground(new Color(0x888888));
        PA1.setOpaque(true);
        PA2 = new JLabel("P2",SwingConstants.CENTER);
        PA2.setBackground(new Color(0x888888));
        PA2.setOpaque(true);
        PA3 = new JLabel("P3",SwingConstants.CENTER);
        PA3.setBackground(new Color(0x888888));
        PA3.setOpaque(true);
        PA4 = new JLabel("P4",SwingConstants.CENTER);
        PA4.setBackground(new Color(0x888888));
        PA4.setOpaque(true);
        PA5 = new JLabel("P5",SwingConstants.CENTER);
        PA5.setBackground(new Color(0x888888));
        PA5.setOpaque(true);

        etatFlotte.add(PA1);
        etatFlotte.add(PA2);
        etatFlotte.add(PA3);
        etatFlotte.add(PA4);
        etatFlotte.add(PA5);
        etatFlotte.add(new JLabel(" "));

        //croiseur//
        crois1 = new JLabel("C1",SwingConstants.CENTER);
        crois1.setPreferredSize(new Dimension(20,20));
        crois1.setBackground(new Color(0x888888));
        crois1.setOpaque(true);
        crois2 = new JLabel("C2",SwingConstants.CENTER);
        crois2.setPreferredSize(new Dimension(20,20));
        crois2.setBackground(new Color(0x888888));
        crois2.setOpaque(true);
        crois3 = new JLabel("C3",SwingConstants.CENTER);
        crois3.setBackground(new Color(0x888888));
        crois3.setOpaque(true);

        etatFlotte.add(crois1);
        etatFlotte.add(crois2);
        etatFlotte.add(crois3);
        etatFlotte.add(new JLabel(" "));

        //cuirrassé//
        cuira1 = new JLabel("C1",SwingConstants.CENTER);
        cuira1.setBackground(new Color(0x888888));
        cuira1.setOpaque(true);
        cuira2 = new JLabel("C2",SwingConstants.CENTER);
        cuira2.setBackground(new Color(0x888888));
        cuira2.setOpaque(true);
        cuira3 = new JLabel("C3",SwingConstants.CENTER);
        cuira3.setBackground(new Color(0x888888));
        cuira3.setOpaque(true);
        cuira4 = new JLabel("C4",SwingConstants.CENTER);
        cuira4.setBackground(new Color(0x888888));
        cuira4.setOpaque(true);

        etatFlotte.add(cuira1);
        etatFlotte.add(cuira2);
        etatFlotte.add(cuira3);
        etatFlotte.add(cuira4);
        etatFlotte.add(new JLabel(" "));

        //torpilleur//
        torpi1 = new JLabel("T1",SwingConstants.CENTER);
        torpi1.setBackground(new Color(0x888888));
        torpi1.setOpaque(true);
        torpi2 = new JLabel("T2",SwingConstants.CENTER);
        torpi2.setBackground(new Color(0x888888));
        torpi2.setOpaque(true);

        etatFlotte.add(torpi1);
        etatFlotte.add(torpi2);
        etatFlotte.add(new JLabel(" "));

        //sous-marin//
        submarine = new JLabel("S1",SwingConstants.CENTER);
        submarine.setBackground(new Color(0x888888));
        submarine.setOpaque(true);

        etatFlotte.add(submarine);

        //Menu de tir//
        interfaceTir = new JPanel(new GridLayout(3,1));
        interfaceTir.setBorder(BorderFactory.createTitledBorder("Interface de tir"));
        rappelDernierTir = new JLabel("dernier tir : ");
        nouveauTir = new JButton("FEU!");
        interfaceTir.add(rappelDernierTir);
        interfaceTir.add(new JLabel());
        interfaceTir.add(nouveauTir);

        //mise en place de la vue//
        interfaceJoueur.add(grilleJoueur,BorderLayout.CENTER);
        interfaceJoueur.add(etatFlotte,BorderLayout.SOUTH);
        gauche.add(interfaceJoueur);
        interfaceAdversaire.add(grilleAdversaire,BorderLayout.CENTER);
        interfaceAdversaire.add(interfaceTir, BorderLayout.SOUTH);
        droite.add(interfaceAdversaire);
        principal.add(gauche);
        principal.add(droite);
        viewManager.setContentPane(principal);
        principal.setPreferredSize(new Dimension(1900,1080));
        viewManager.pack();
        viewManager.setResizable(false);
        viewManager.setVisible(true);
    }


}
