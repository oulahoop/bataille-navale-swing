package info1.view.menus;

import info1.ships.ICoord;
import info1.view.ConstantColor;
import info1.view.ViewManager;
import info1.view.listeners.signInMenu.OnActionEvent;
import info1.view.listeners.signInMenu.OnClicCoord;
import info1.view.listeners.signInMenu.OnPlacerAction;
import info1.view.listeners.signInMenu.SensListener;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe dans la quel est creer la vue "Sign In"
 */
public class SignInMenu {


    private final ViewManager frame;

    ImageIcon france = new ImageIcon("src/info1/utils/img/frFlag.png");
    ImageIcon belgique = new ImageIcon("src/info1/utils/img/beFlag.png");

    JPanel main = new JPanel(new BorderLayout());

    //main Components
    JPanel mainCenter = new JPanel(new BorderLayout());
    JPanel mainEast = new JPanel(new BorderLayout());

    //mainCenter Components
    JPanel mainCNorth = new JPanel(new GridLayout(1,2));
    JPanel mainCCenter = new JPanel(new FlowLayout(FlowLayout.CENTER));

    // mainCNorth Components
    JButton french = new JButton("Français");
    JButton belgium = new JButton("Belge");

    boolean isFrench = true;

    //mainCCenter Components
    JPanel plateau = new JPanel(new GridLayout(11,11));
    List<JButton> buttons = new ArrayList<>();
    JLabel contour;

    //mainEast Components
    JPanel mainENorth = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel mainECenter = new JPanel(new GridLayout(5,1));
    JPanel mainESouth = new JPanel();

    //mainENorth Components
    JLabel pseudo = new JLabel("Entrer un nom");
    JTextField playerName = new JTextField();
    JButton play = new JButton("Jouer");

    //mainESouth Components
    JPanel ship1 = new JPanel(new FlowLayout());
    JPanel ship2 = new JPanel(new FlowLayout());
    JPanel ship3 = new JPanel(new FlowLayout());
    JPanel ship4 = new JPanel(new FlowLayout());
    JPanel ship5 = new JPanel(new FlowLayout());

    JLabel aircraftImg = new JLabel(new ImageIcon("src/info1/utils/img/1.png"));
    JLabel battleShipImg = new JLabel(new ImageIcon("src/info1/utils/img/2.png"));
    JLabel cruiserImg = new JLabel(new ImageIcon("src/info1/utils/img/3.png"));
    JLabel destroyerImg = new JLabel(new ImageIcon("src/info1/utils/img/4.png"));
    JLabel submarinImg = new JLabel(new ImageIcon("src/info1/utils/img/5.png"));

    JButton aircraft = new JButton("Porte-avion");
    JButton battleShip = new JButton("Cuirassé");
    JButton cruiser = new JButton("Croiseur");
    JButton destroyer = new JButton("Torpilleur");
    JButton submarin = new JButton("Sous-marin");

    JButton aircraftSens = new JButton("Horizontal");
    boolean aSens = true;
    JButton battleShipSens = new JButton("Horizontal");
    boolean bSens = true;
    JButton cruiserSens = new JButton("Horizontal");
    boolean cSens = true;
    JButton destroyerSens = new JButton("Horizontal");
    boolean dSens = true;
    JButton submarinSens = new JButton("Horizontal");
    boolean sSens = true;


    /**
     * Constructeur de la classe dans le quel la vue est créé et ajouter au frame
     * @param frame le Framdans le quel sera affiché la vue
     */
    public SignInMenu(ViewManager frame) {

        this.frame = frame;
        //main DEFINITION
        main.setSize(frame.getSize());
        main.setPreferredSize(main.getSize());

        //mainCenter DEFINITION
        mainCenter.setSize(new Dimension((int) (main.getWidth()*0.55), main.getHeight()));
        mainCenter.setPreferredSize(mainCenter.getSize());
        mainCenter.setBackground(new Color(ConstantColor.BACKGROUND.getColor()));

        //mainCNorth DEFINITION
        mainCNorth.setSize(new Dimension(mainCenter.getWidth(), (int) (mainCenter.getHeight()*0.1)));
        mainCNorth.setPreferredSize(mainCNorth.getSize());
        mainCNorth.setBackground(new Color(ConstantColor.BACKGROUND.getColor()));

        french.setName("francais");
        belgium.setName("belge");

        Image imageF = france.getImage();
        imageF = imageF.getScaledInstance(mainCNorth.getWidth()/2, mainCNorth.getHeight(), java.awt.Image.SCALE_SMOOTH);
        france = new ImageIcon(imageF);

        Image imageB = belgique.getImage();
        imageB = imageB.getScaledInstance(mainCNorth.getWidth()/2, mainCNorth.getHeight(), java.awt.Image.SCALE_SMOOTH);
        belgique = new ImageIcon(imageB);

        french.setIcon(france);
        belgium.setIcon(belgique);

        mainCNorth.add(french);
        mainCNorth.add(belgium);

        //mainCCenter DEFINITION
        mainCCenter.setSize(new Dimension(mainCenter.getWidth(), mainCenter.getHeight()-mainCNorth.getHeight()));
        mainCCenter.setPreferredSize(mainCCenter.getSize());
        mainCCenter.setBackground(new Color(ConstantColor.BACKGROUND.getColor()));

        //Definition du plateau de jeu
        JLabel empty = new JLabel();
        empty.setBackground(new Color(ConstantColor.BACKGROUND.getColor()));
        empty.setOpaque(true);
        plateau.add(empty);
        for (int i = 0; i < 10; i++) {
            contour = new JLabel(String.valueOf((char)(65+i)),SwingConstants.CENTER);
            //ESTHETIQUE
            contour.setBackground(new Color(ConstantColor.GRILLELABEL.getColor()));
            contour.setForeground(new Color(ConstantColor.GRILLELABELTEXT.getColor()));
            contour.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
            contour.setOpaque(true);
            plateau.add(contour);
        }
        for(int i = 0; i < 10; i++){
            JLabel grilleLabel = new JLabel(String.valueOf(i+1), SwingConstants.CENTER);
            //ESTHETIQUE
            grilleLabel.setBackground(new Color(ConstantColor.GRILLELABEL.getColor()));
            grilleLabel.setForeground(new Color(ConstantColor.GRILLELABELTEXT.getColor()));
            grilleLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
            grilleLabel.setOpaque(true);
            plateau.add(grilleLabel);
            for(int j = 0;j<10;j++){
                JButton b1 = new JButton();
                b1.setPreferredSize(new Dimension(50, 50));
                //ESTHETIQUE
                b1.setBackground(new Color(ConstantColor.BASECOLOR.getColor()));
                b1.setBorder(new LineBorder(new Color(ConstantColor.BACKGROUND.getColor())));

                b1.setName(((char) (65 + j)) + String.valueOf(i + 1));
                buttons.add(b1);
                plateau.add(b1);
            }
        }
        //mainCCenter ADD
        mainCCenter.add(plateau);

        //mainCenter ADD
        mainCenter.add(mainCNorth, BorderLayout.NORTH);
        mainCenter.add(mainCCenter, BorderLayout.CENTER);

        //mainEast DEFINITION
        mainEast.setSize(new Dimension(main.getWidth()-mainCenter.getWidth(), main.getHeight()));
        mainEast.setPreferredSize(mainEast.getSize());
        mainEast.setBackground(new Color(ConstantColor.BACKGROUND.getColor()));

        //mainENorth DEFINITION
        mainENorth.setSize(new Dimension(mainEast.getWidth(), (int) (mainEast.getHeight()*0.25)));
        mainENorth.setPreferredSize(mainENorth.getSize());
        mainENorth.setBackground(new Color(ConstantColor.BACKGROUND.getColor()));

        pseudo.setSize(new Dimension(mainENorth.getWidth(), (int) (mainENorth.getHeight()*0.45)));
        pseudo.setPreferredSize(pseudo.getSize());

        //ESTHETIQUE
        pseudo.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        pseudo.setForeground(new Color(ConstantColor.LABELTEXT.getColor()));

        pseudo.setHorizontalAlignment(SwingConstants.CENTER);

        playerName.setSize(new Dimension((int) (mainENorth.getWidth()*0.6), (int) (mainENorth.getHeight()*0.45)));
        playerName.setPreferredSize(playerName.getSize());
        //ESTHETIQUE
        playerName.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        playerName.setBackground(new Color(ConstantColor.JTEXTAREA.getColor()));
        playerName.setForeground(new Color(ConstantColor.JTEXTAREATEXT.getColor()));

        playerName.setHorizontalAlignment(SwingConstants.CENTER);

        //mainENorth ADD
        mainENorth.add(pseudo);
        mainENorth.add(playerName);

        //mainESouth DEFINITION
        mainESouth.setSize(new Dimension(mainEast.getWidth(), (int) (mainEast.getHeight()*0.1)));
        mainESouth.setPreferredSize(mainESouth.getSize());
        mainESouth.setBackground(new Color(ConstantColor.BACKGROUND.getColor()));

        play.setPreferredSize(mainESouth.getSize());

        //ESTHETIQUE
        play.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        play.setForeground(new Color(ConstantColor.BUTTONFOREGROUND.getColor()));
        play.setBackground(new Color(ConstantColor.BUTTON.getColor()));

        //mainESouthADD
        mainESouth.add(play);

        //mainECenter DEFINITION
        mainECenter.setBackground(new Color(ConstantColor.BACKGROUND.getColor()));

        aircraft.setPreferredSize(new Dimension(200,50));
        battleShip.setPreferredSize(new Dimension(200,50));
        cruiser.setPreferredSize(new Dimension(200,50));
        destroyer.setPreferredSize(new Dimension(200,50));
        submarin.setPreferredSize(new Dimension(200,50));

        aircraftSens.setPreferredSize(new Dimension(150,50));
        battleShipSens.setPreferredSize(new Dimension(150,50));
        cruiserSens.setPreferredSize(new Dimension(150,50));
        destroyerSens.setPreferredSize(new Dimension(150,50));
        submarinSens.setPreferredSize(new Dimension(150,50));

        //ESTHETIQUE
        aircraft.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        aircraft.setForeground(new Color(ConstantColor.BUTTONFOREGROUND.getColor()));
        aircraft.setBackground(new Color(ConstantColor.BUTTON.getColor()));

        battleShip.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        battleShip.setForeground(new Color(ConstantColor.BUTTONFOREGROUND.getColor()));
        battleShip.setBackground(new Color(ConstantColor.BUTTON.getColor()));

        cruiser.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        cruiser.setForeground(new Color(ConstantColor.BUTTONFOREGROUND.getColor()));
        cruiser.setBackground(new Color(ConstantColor.BUTTON.getColor()));

        destroyer.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        destroyer.setForeground(new Color(ConstantColor.BUTTONFOREGROUND.getColor()));
        destroyer.setBackground(new Color(ConstantColor.BUTTON.getColor()));

        submarin.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        submarin.setForeground(new Color(ConstantColor.BUTTONFOREGROUND.getColor()));
        submarin.setBackground(new Color(ConstantColor.BUTTON.getColor()));

        aircraftSens.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        aircraftSens.setForeground(new Color(ConstantColor.BUTTONFOREGROUND.getColor()));
        aircraftSens.setBackground(new Color(ConstantColor.BUTTON.getColor()));

        battleShipSens.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        battleShipSens.setForeground(new Color(ConstantColor.BUTTONFOREGROUND.getColor()));
        battleShipSens.setBackground(new Color(ConstantColor.BUTTON.getColor()));

        cruiserSens.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        cruiserSens.setForeground(new Color(ConstantColor.BUTTONFOREGROUND.getColor()));
        cruiserSens.setBackground(new Color(ConstantColor.BUTTON.getColor()));

        destroyerSens.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        destroyerSens.setForeground(new Color(ConstantColor.BUTTONFOREGROUND.getColor()));
        destroyerSens.setBackground(new Color(ConstantColor.BUTTON.getColor()));

        submarinSens.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        submarinSens.setForeground(new Color(ConstantColor.BUTTONFOREGROUND.getColor()));
        submarinSens.setBackground(new Color(ConstantColor.BUTTON.getColor()));

        setSensText();

        aircraftSens.setName("asens");
        battleShipSens.setName("bsens");
        cruiserSens.setName("csens");
        destroyerSens.setName("dsens");
        submarinSens.setName("ssens");

        ship1.setBackground(new Color(ConstantColor.BACKGROUND.getColor()));
        ship2.setBackground(new Color(ConstantColor.BACKGROUND.getColor()));
        ship3.setBackground(new Color(ConstantColor.BACKGROUND.getColor()));
        ship4.setBackground(new Color(ConstantColor.BACKGROUND.getColor()));
        ship5.setBackground(new Color(ConstantColor.BACKGROUND.getColor()));

        ship1.add(aircraftImg);
        ship1.add(aircraft);
        ship1.add(aircraftSens);

        ship2.add(battleShipImg);
        ship2.add(battleShip);
        ship2.add(battleShipSens);

        ship3.add(cruiserImg);
        ship3.add(cruiser);
        ship3.add(cruiserSens);

        ship4.add(destroyerImg);
        ship4.add(destroyer);
        ship4.add(destroyerSens);

        ship5.add(submarinImg);
        ship5.add(submarin);
        ship5.add(submarinSens);

        setNation();

        //mainEast ADD
        mainEast.add(mainENorth, BorderLayout.NORTH);
        mainEast.add(mainECenter, BorderLayout.CENTER);
        mainEast.add(mainESouth, BorderLayout.SOUTH);

        //main ADD
        main.add(mainCenter, BorderLayout.CENTER);
        main.add(mainEast, BorderLayout.EAST);

        //Set listener (on a o
        setListener();


        frame.setContentPane(main);
        frame.update();
    }

    /**
     * Getter de l'attribut is french
     * @return boolean
     */
    public boolean getIsFrench(){return isFrench;}

    /**
     * Methode qui permet de Set la valeur de l'attribut "isFrench" et mettre a jour la vue en fonction
     * @param status l'etat voulu
     */
    public void setIsFrench(boolean status) {
        this.isFrench = status;
        setNation();
    }

    /**
     * Methode public qui permet d'update la vue
     */
    public void update(){
        setNation();
        setSensText();
        frame.update();
    }

    /**
     * Methode privé qui permet de mettre a jours les boutons "french" et "belgium"
     */
    private void setNation(){
        if(isFrench) {
            frenchFleet();
            setEnableFrench(false);
            setEnableBelgium(true);
        }
        else{
            belgianFleet();
            setEnableBelgium(false);
            setEnableFrench(true);
        }
    }

    /**
     * Methode privé qui permet l'initialisation des textes des boutons
     */
    private void setSensText(){
        if(aSens) aircraftSens.setText("Horizontal");else aircraftSens.setText("Vertical");
        if(bSens) battleShipSens.setText("Horizontal"); else battleShipSens.setText("Vertical");
        if(cSens) cruiserSens.setText("Horizontal"); else cruiserSens.setText("Vertical");
        if(dSens) destroyerSens.setText("Horizontal"); else destroyerSens.setText("Vertical");
        if(sSens) submarinSens.setText("Horizontal"); else submarinSens.setText("Vertical");
    }

    /**
     * Methode qui permet de Set l'attribut "aSens" et met a jour la vue
     * @param b la valeur que doit prendre l'attribut
     */
    public void setAircraftSens(boolean b) {
        this.aSens = b;
        if(aSens) {aircraftSens.setText("Horizontal");}else {aircraftSens.setText("Vertical");}
    }

    /**
     * Methode qui permet de Set l'attribut "bSens" et met a jour la vue
     * @param b la valeur que doit prendre l'attribut
     */
    public void setBattleShipSens(boolean b) {
        this.bSens = b;
        if(bSens) {battleShipSens.setText("Horizontal");} else {battleShipSens.setText("Vertical");}
    }

    /**
     * Methode qui permet de Set l'attribut "cSens" et met a jour la vue
     * @param b la valeur que doit prendre l'attribut
     */
    public void setCruiserSens(boolean b) {
        this.cSens = b;
        if(cSens) cruiserSens.setText("Horizontal"); else cruiserSens.setText("Vertical");
    }

    /**
     * Methode qui permet de Set l'attribut "dSens" et met a jour la vue
     * @param b la valeur que doit prendre l'attribut
     */
    public void setDestroyerSens(boolean b) {
        this.dSens = b;
        if(dSens) destroyerSens.setText("Horizontal"); else destroyerSens.setText("Vertical");
    }

    /**
     * Methode qui permet de Set l'attribut "sSens" et met a jour la vue
     * @param b la valeur que doit prendre l'attribut
     */
    public void setSubmarinSens(boolean b) {
        this.sSens = b;
        if(sSens) submarinSens.setText("Horizontal"); else submarinSens.setText("Vertical");
    }

    /**
     * Methode qui permet de reset la grile et les valeur des boutons de choix de bateau
     */
    public void reset(){
        for(JButton button : buttons){
            button.setBackground(new Color(0x78939A));
        }
        setEnableAircraft(true);
        setEnableBattleShip(true);
        setEnableCruiser(true);
        setEnableDestroyer(true);
        setEnableSubmarin(true);

        setAircraftSens(true);
        setBattleShipSens(true);
        setCruiserSens(true);
        setDestroyerSens(true);
        setSubmarinSens(true);

    }

    /**
     * Methode qui permet de changer les sur les quel le bateau est placé
     * @param coords Liste de coordonnées occupées par le bateau
     */
    public void placeShip(List<ICoord> coords){
        for (JButton button : buttons) {
            for (ICoord coord : coords){
                if(button.getName().equalsIgnoreCase(coord.toString())){
                    button.setBackground(Color.BLACK);
                }
            }

        }
    }

    /**
     * Getter de l'attribut "aSens"
     * @return la valeur de "aSens"
     */
    public boolean getAircraftSens(){return aSens;}

    /**
     * Getter de l'attribut "bSens"
     * @return la valeur de "bSens"
     */
    public boolean getBattleShipSens(){return bSens;}

    /**
     * Getter de l'attribut "cSens"
     * @return la valeur de "cSens"
     */
    public boolean getCruiserSens(){return cSens;}

    /**
     * Getter de l'attribut "dSens"
     * @return la valeur de "dSens"
     */
    public boolean getDestroyerSens(){return dSens;}

    /**
     * Getter de l'attribut "sSens"
     * @return la valeur de "sSens"
     */
    public boolean getSubmarinSens(){return sSens;}


    /**
     * Methode qui permet de Set l'etat du bouton "belgium"
     * @param b l'etat du bouton, true si activer, false sinon
     */
    public void setEnableBelgium(boolean b){ belgium.setEnabled(b); }

    /**
     * Methode qui permet de Set l'etat du bouton "french"
     * @param b l'etat du bouton, true si activer, false sinon
     */
    public void setEnableFrench(boolean b){french.setEnabled(b);}

    /**
     * Methode qui permet de Set l'etat du bouton "aircraft"
     * @param b l'etat du bouton, true si activer, false sinon
     */
    public void setEnableAircraft(boolean b){ aircraft.setEnabled(b); }

    /**
     * Methode qui permet de Set l'etat du bouton "battleship"
     * @param b l'etat du bouton, true si activer, false sinon
     */
    public void setEnableBattleShip(boolean b){battleShip.setEnabled(b);}

    /**
     * Methode qui permet de Set l'etat du bouton "cruiser"
     * @param b l'etat du bouton, true si activer, false sinon
     */
    public void setEnableCruiser(boolean b){cruiser.setEnabled(b);}

    /**
     * Methode qui permet de Set l'etat du bouton "destroyer"
     * @param b l'etat du bouton, true si activer, false sinon
     */
    public void setEnableDestroyer(boolean b){destroyer.setEnabled(b);}

    /**
     * Methode qui permet de Set l'etat du bouton "submarin"
     * @param b l'etat du bouton, true si activer, false sinon
     */
    public void setEnableSubmarin(boolean b){submarin.setEnabled(b);}

    /**
     * Méthode qui permet de récupérer le texte du "JTextArea" "playerName"
     * @return nom rentré par le joueur
     */
    public String getPlayerName(){return playerName.getText();}

    /**
     * Methode privé qui permet l'affichage de la flotte française
     */
    private void frenchFleet() {
        mainECenter.removeAll();
        mainECenter.setSize(new Dimension(mainEast.getWidth(), mainEast.getHeight()-(mainENorth.getHeight()+mainESouth.getHeight())));
        mainECenter.setPreferredSize(mainECenter.getSize());

        mainECenter.add(ship1);
        mainECenter.add(ship2);
        mainECenter.add(ship3);
        mainECenter.add(ship4);
        mainECenter.add(ship5);
    }

    /**
     * Methode privé qui permet l'affichage de la flotte belge
     */
    private void belgianFleet(){
        mainECenter.removeAll();
        mainECenter.setSize(new Dimension(mainEast.getWidth(), mainEast.getHeight()-(mainENorth.getHeight()+mainESouth.getHeight())));
        mainECenter.setPreferredSize(mainECenter.getSize());

        mainECenter.add(ship2);
        mainECenter.add(ship3);
        mainECenter.add(ship4);
        mainECenter.add(ship5);
        JPanel empty = new JPanel();
        empty.setBackground(new Color(ConstantColor.BACKGROUND.getColor()));
        mainECenter.add(empty);
    }

    /**
     * Méthode privée qui permet d'associer un listener au element en ayant besoin
     */
    private void setListener(){
        OnActionEvent oae = new OnActionEvent(this);
        belgium.addActionListener(oae);
        french.addActionListener(oae);
        play.addActionListener(oae);

        OnPlacerAction opa = new OnPlacerAction(this);
        aircraft.addActionListener(opa);
        battleShip.addActionListener(opa);
        cruiser.addActionListener(opa);
        destroyer.addActionListener(opa);
        submarin.addActionListener(opa);

        aircraftSens.addActionListener(new SensListener(this));
        battleShipSens.addActionListener(new SensListener(this));
        cruiserSens.addActionListener(new SensListener(this));
        destroyerSens.addActionListener(new SensListener(this));
        submarinSens.addActionListener(new SensListener(this));


        for(JButton button : buttons){
            button.addActionListener(new OnClicCoord(this,opa));
        }
    }
}
