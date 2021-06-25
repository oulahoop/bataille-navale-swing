package info1.view.menus;


import info1.ships.ICoord;
import info1.ships.IShip;

import info1.view.ConstantColor;
import info1.view.Menu;
import info1.view.ViewManager;
import info1.utils.GameManager;
import javax.swing.*;
import javax.swing.border.LineBorder;
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
    JLabel yourFleet = new JLabel("Votre flotte");

    JPanel interfaceAdversaire;
    JLabel oponentFleet = new JLabel("Flotte adverse");

    JLabel contour;
    //---------------------------//
    JLabel grilleLabel;
    //grille joueur//
    JPanel grilleJoueur;
    JButton boutonJoueur;
    List<JButton> boutons = new ArrayList<>();

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

        principal.setSize(viewManager.getSize());
        principal.setPreferredSize(principal.getSize());

        principal.setBackground(new Color(ConstantColor.BACKGROUND.getColor()));

        gauche = new JPanel(new FlowLayout(FlowLayout.CENTER));
        gauche.setBackground(new Color(ConstantColor.BACKGROUND.getColor()));
        interfaceJoueur = new JPanel(new BorderLayout());
        interfaceJoueur.setBackground(new Color(ConstantColor.BACKGROUND.getColor()));

        droite = new JPanel(new FlowLayout(FlowLayout.CENTER));
        droite.setBackground(new Color(ConstantColor.BACKGROUND.getColor()));

        interfaceAdversaire = new JPanel(new BorderLayout());
        interfaceAdversaire.setBackground(new Color(ConstantColor.BACKGROUND.getColor()));

        //settings grille joueur//
        grilleJoueur = new JPanel(new GridLayout(11,11));
        yourFleet.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        yourFleet.setForeground(new Color(ConstantColor.LABELTEXT.getColor()));

        //ajout des lettres en haut des colonnes//
        JLabel empty2 = new JLabel();
        empty2.setBackground(new Color(ConstantColor.BACKGROUND.getColor()));
        empty2.setOpaque(true);
        grilleJoueur.add(empty2);
        for (int i = 0; i < 10; i++) {
            contour = new JLabel(String.valueOf((char)(65+i)),SwingConstants.CENTER);

            contour.setBackground(new Color(ConstantColor.GRILLELABEL.getColor()));
            contour.setForeground(new Color(ConstantColor.GRILLELABELTEXT.getColor()));
            contour.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
            contour.setOpaque(true);

            grilleJoueur.add(contour);
        }
        for(int i = 0; i < 10; i++){
            grilleLabel = new JLabel(String.valueOf(i+1), SwingConstants.CENTER);
            //ESTHETIQUE
            grilleLabel.setBackground(new Color(ConstantColor.GRILLELABEL.getColor()));
            grilleLabel.setForeground(new Color(ConstantColor.GRILLELABELTEXT.getColor()));
            grilleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
            grilleLabel.setOpaque(true);

            grilleJoueur.add(grilleLabel);
            for(int j = 0;j<10;j++){
                boutonJoueur = new JButton();
                //ESTHETIQUE
                boutonJoueur.setBackground(new Color(ConstantColor.BASECOLOR.getColor()));
                boutonJoueur.setBorder(new LineBorder(new Color(ConstantColor.BACKGROUND.getColor())));

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
        oponentFleet.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        oponentFleet.setForeground(new Color(ConstantColor.LABELTEXT.getColor()));

        JLabel empty = new JLabel();
        empty.setBackground(new Color(ConstantColor.BACKGROUND.getColor()));
        empty.setOpaque(true);
        grilleAdversaire.add(empty);
        for (int i = 0; i < 10; i++) {
            contour = new JLabel(String.valueOf((char)(65+i)),SwingConstants.CENTER);
            //ESTHETIQUE
            contour.setBackground(new Color(ConstantColor.GRILLELABEL.getColor()));
            contour.setForeground(new Color(ConstantColor.GRILLELABELTEXT.getColor()));
            contour.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
            contour.setOpaque(true);

            grilleAdversaire.add(contour);
        }
        ListenerTir controleur = new ListenerTir(this);
        for(int i = 0; i < 10; i++){
            grilleLabel = new JLabel(String.valueOf(i+1), SwingConstants.CENTER);
            //ESTHETIQUE
            grilleLabel.setBackground(new Color(ConstantColor.GRILLELABEL.getColor()));
            grilleLabel.setForeground(new Color(ConstantColor.GRILLELABELTEXT.getColor()));
            grilleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
            grilleLabel.setOpaque(true);

            grilleAdversaire.add(grilleLabel);
            for(int j = 0;j<10;j++){
                boutonAdversaire = new JButton();
                boutonAdversaire.setBackground(new Color(ConstantColor.BASECOLOR.getColor()));
                //ESTHETIQUE
                boutonAdversaire.setBorder(new LineBorder(new Color(ConstantColor.BACKGROUND.getColor())));
                boutonAdversaire.setPreferredSize(new Dimension(50,50));
                int number = i+1;
                boutonAdversaire.setName((char)(65+j)+ "" + number);
                boutonAdversaire.addActionListener(controleur);
                grilleAdversaire.add(boutonAdversaire);

            }
        }


        //Menu de tir//
        interfaceTir = new JPanel(new GridLayout(1,3));
        interfaceTir.setBackground(new Color(ConstantColor.BACKGROUND.getColor()));
        //interfaceTir.setBorder(BorderFactory.createTitledBorder("Interface de tir"));
        rappelDernierTir = new JLabel("");
        nouveauTir = new JButton("FEU!");
        nouveauTir.setBackground(new Color(ConstantColor.FIREENABLE.getColor()));
        nouveauTir.setEnabled(false);
        nouveauTir.setName("Tirer");
        nouveauTir.addActionListener(controleur);
        nouveauTir.setPreferredSize(new Dimension(principal.getWidth()/10, principal.getHeight()/10));

        interfaceTir.add(rappelDernierTir);
        JPanel empty1 = new JPanel();
        empty1.setBackground(new Color(ConstantColor.BACKGROUND.getColor()));
        interfaceTir.add(empty1);
        interfaceTir.add(nouveauTir);

        //quitter

        exit.setPreferredSize(new Dimension(principal.getWidth()/10, principal.getHeight()/10));
        exit.addActionListener(new ExitListener(viewManager));

        //ESTHETIQUE
        exit.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        exit.setForeground(new Color(ConstantColor.BUTTONFOREGROUND.getColor()));
        exit.setBackground(new Color(ConstantColor.BUTTON.getColor()));

        exitpanel.setBackground(new Color(ConstantColor.BACKGROUND.getColor()));
        exitpanel.add(exit, BorderLayout.WEST);

        interfaceJoueur.add(exitpanel, BorderLayout.SOUTH);
        interfaceJoueur.add(yourFleet, BorderLayout.NORTH);

        interfaceAdversaire.add(oponentFleet, BorderLayout.NORTH);

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
        nouveauTir.setBackground(new Color(ConstantColor.FIREDISABLE.getColor()));

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
            nouveauTir.setBackground(new Color(ConstantColor.FIREENABLE.getColor()));
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
