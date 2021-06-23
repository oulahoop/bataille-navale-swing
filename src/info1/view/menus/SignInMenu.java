package info1.view.menus;


import info1.view.ViewManager;
import info1.view.listeners.OnActionEvent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class SignInMenu{


    private JPanel bateaux;

    private JTextField name;

    private ArrayList<JButton> buttons;

    private JButton jouer;

    private JButton frenchComp;
    private JButton belgiumComp;


    public SignInMenu(ViewManager frame) {
        JPanel principal = new JPanel();
        principal.setLayout(new GridLayout(1, 2));

        OnActionEvent controleur = new OnActionEvent(this);

        JPanel droite = new JPanel();
        droite.setLayout(new GridLayout(2,1));

        //LIGNE POUR PUT LE NOM
        JPanel inscription = new JPanel(new BorderLayout());
        JPanel centerInscription = new JPanel(new GridLayout(10,1));
        JPanel insideCenterInscription = new JPanel();
        name = new JTextField();
        name.setPreferredSize(new Dimension(250,50));
        name.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
        name.setBackground(new Color(0x9293FF));
        for(int i = 0;i<2;i++){
            JButton void1 = new JButton();
            void1.setVisible(false);
            centerInscription.add(void1);
        }
        JPanel insideNom = new JPanel();
        JLabel nom = new JLabel("Inscrivez votre nom de G4m3ur");
        nom.setFont(new Font(Font.SANS_SERIF,Font.BOLD,20));
        insideNom.add(nom);
        centerInscription.add(insideNom);
        insideCenterInscription.add(name);
        centerInscription.add(insideCenterInscription);
        inscription.add(centerInscription,BorderLayout.NORTH);


        //BOUTTON JOUER
        JPanel clickTo = new JPanel(new GridLayout(5,1));
        for(int i = 0; i<3;i++){
            JButton b1 = new JButton();
            b1.setVisible(false);
            clickTo.add(b1);
        }
        JPanel clickToPlay = new JPanel(new BorderLayout());
        JPanel clickToPlayCenter = new JPanel();
        jouer = new JButton("Jouer !");
        jouer.setName("Jouer");
        jouer.addActionListener(controleur);
        jouer.setPreferredSize(new Dimension(250,100));
        /*
        JButton vide1 = new JButton();
        JButton vide2 = new JButton();
        vide1.setVisible(false);
        vide2.setVisible(false);
        clickToPlay.add(vide1);
        clickToPlay.add(vide2);
        */
        clickToPlayCenter.add(jouer);
        clickToPlay.add(clickToPlayCenter,BorderLayout.CENTER);
        clickTo.add(clickToPlay);

        droite.add(inscription);
        droite.add(clickToPlay);

        JPanel gauche = new JPanel();
        gauche.setLayout(new FlowLayout(FlowLayout.CENTER));



        JPanel gaugauche = new JPanel(new BorderLayout());

        JPanel plateau = new JPanel();
        plateau.setLayout(new GridLayout(10,10));
        buttons = new ArrayList<>();
        for(int j = 0; j<10; j++) {
            for (int i = 0; i < 10; i++) {
                JButton b1 = new JButton();
                b1.setPreferredSize(new Dimension(50, 50));
                b1.setBackground(new Color(0x78939A));
                b1.setName(((char) (65 + j)) + String.valueOf(i + 1));
                b1.setTransferHandler(new TransferHandler("icon"));
                buttons.add(b1);
                plateau.add(b1);
            }
        }
        bateaux = new JPanel(new GridLayout(5,1));

        MouseListener ml = new DragMouseAdaper();
        String[] tab = new String[]{"AircraftCarrier", "BattleShip     ", "Cruiser         ", "Destroyer      ", "Submarine      "};
        for (int i = 0; i < 5; i++) {
            ImageIcon ship = new ImageIcon("lib/ship.png");
            JLabel jl = new JLabel(ship);
            jl.setText(tab[i]);
            jl.setTransferHandler(new TransferHandler("icon"));
            jl.addMouseListener(ml);
            jl.setBackground(new Color(50,50,40*i));
            bateaux.add(jl);
        }


        JPanel composition = new JPanel();
        JPanel compositionGauche = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel compositionDroite = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        frenchComp = new JButton("Compostion Française");
        frenchComp.setPreferredSize(new Dimension(250,50));
        frenchComp.setName("Français");
        frenchComp.setEnabled(false);
        frenchComp.addActionListener(controleur);


        belgiumComp = new JButton("Composition Belge");
        belgiumComp.setPreferredSize(new Dimension(250,50));
        belgiumComp.setName("Belge");
        belgiumComp.addActionListener(controleur);


        compositionGauche.add(frenchComp);
        compositionDroite.add(belgiumComp);

        composition.add(compositionGauche);
        composition.add(belgiumComp);

        gauche.add(composition, BorderLayout.NORTH);

        JPanel centragegauche = new JPanel(new FlowLayout(FlowLayout.LEFT));
        centragegauche.add(bateaux);
        gaugauche.add(composition,BorderLayout.NORTH);
        gaugauche.add(plateau,BorderLayout.CENTER);
        gaugauche.add(centragegauche, BorderLayout.SOUTH);
        gauche.add(gaugauche);


        principal.add(gauche);
        principal.add(droite);


        principal.setPreferredSize(new Dimension(1920,1080));
        frame.setContentPane(principal);
        frame.setPreferredSize(new Dimension(1920,1080));
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class DragMouseAdaper extends MouseAdapter{
        public void mousePressed(MouseEvent e){
            JComponent c = (JComponent) e.getSource();
            TransferHandler handler = c.getTransferHandler();
            handler.exportAsDrag(c,e,TransferHandler.COPY);
        }
    }

    public JTextField getName() {
        return name;
    }

    public JButton getJouer() {
        return jouer;
    }

    public void enableFrench(boolean b){
        frenchComp.setEnabled(b);
    }

    public void enableBelgium(boolean b){
        belgiumComp.setEnabled(b);
    }

    public JPanel getBateauxPanel(){
        return bateaux;
    }
}
