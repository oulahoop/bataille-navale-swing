package info1.view.menus;

import info1.ships.Coord;
import info1.ships.ICoord;
import info1.view.ViewManager;
import info1.view.listeners.signInMenu.OnActionEvent;
import info1.view.listeners.signInMenu.OnClicCoord;
import info1.view.listeners.signInMenu.OnPlacerAction;
import info1.view.listeners.signInMenu.SensListener;
import jdk.swing.interop.SwingInterOpUtils;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class SignInMenu {


    private final ViewManager frame;
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



    public SignInMenu(ViewManager frame) {

        this.frame = frame;
        //main DEFINITION
        main.setSize(frame.getSize());
        main.setPreferredSize(main.getSize());

        //mainCenter DEFINITION
        mainCenter.setSize(new Dimension((int) (main.getWidth()*0.55), main.getHeight()));
        mainCenter.setPreferredSize(mainCenter.getSize());

        //mainCNorth DEFINITION
        mainCNorth.setSize(new Dimension(mainCenter.getWidth(), (int) (mainCenter.getHeight()*0.1)));
        mainCNorth.setPreferredSize(mainCNorth.getSize());

        french.setName("francais");
        belgium.setName("belge");

        mainCNorth.add(french);
        mainCNorth.add(belgium);

        //mainCCenter DEFINITION
        mainCCenter.setSize(new Dimension(mainCenter.getWidth(), mainCenter.getHeight()-mainCNorth.getHeight()));
        mainCCenter.setPreferredSize(mainCCenter.getSize());

        //Definition du plateau de jeu
        plateau.add(new JLabel());
        for (int i = 0; i < 10; i++) {
            contour = new JLabel(String.valueOf((char)(65+i)),SwingConstants.CENTER);
            contour.setOpaque(true);
            plateau.add(contour);
        }
        for(int i = 0; i < 10; i++){
            plateau.add(new JLabel(String.valueOf(i+1), SwingConstants.CENTER));
            for(int j = 0;j<10;j++){
                JButton b1 = new JButton();
                b1.setPreferredSize(new Dimension(50, 50));
                b1.setBackground(new Color(0x78939A));
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

        //mainENorth DEFINITION
        mainENorth.setSize(new Dimension(mainEast.getWidth(), (int) (mainEast.getHeight()*0.25)));
        mainENorth.setPreferredSize(mainENorth.getSize());

        pseudo.setSize(new Dimension(mainENorth.getWidth(), (int) (mainENorth.getHeight()*0.45)));
        pseudo.setPreferredSize(pseudo.getSize());
        pseudo.setHorizontalAlignment(SwingConstants.CENTER);

        playerName.setSize(new Dimension((int) (mainENorth.getWidth()*0.6), (int) (mainENorth.getHeight()*0.45)));
        playerName.setPreferredSize(playerName.getSize());
        playerName.setHorizontalAlignment(SwingConstants.CENTER);

        //mainENorth ADD
        mainENorth.add(pseudo);
        mainENorth.add(playerName);

        //mainESouth DEFINITION
        mainESouth.setSize(new Dimension(mainEast.getWidth(), (int) (mainEast.getHeight()*0.1)));
        mainESouth.setPreferredSize(mainESouth.getSize());

        play.setPreferredSize(mainESouth.getSize());

        //mainESouthADD
        mainESouth.add(play);

        //mainECenter DEFINITION

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

        setSensText();

        aircraftSens.setName("asens");
        battleShipSens.setName("bsens");
        cruiserSens.setName("csens");
        destroyerSens.setName("dsens");
        submarinSens.setName("ssens");

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

    public boolean getIsFrench(){return isFrench;}

    public void setIsFrench(boolean status) {
        this.isFrench = status;
        setNation();
    }

    public void update(){
        setNation();
        frame.update();
    }

    private void setNation(){
        if(isFrench) frenchFleet();
        else belgianFleet();
    }

    private void setSensText(){
        if(aSens) aircraftSens.setText("Horizontal");else aircraftSens.setText("Vertical");
        if(bSens) battleShipSens.setText("Horizontal"); else battleShipSens.setText("Vertical");
        if(cSens) cruiserSens.setText("Horizontal"); else cruiserSens.setText("Vertical");
        if(dSens) destroyerSens.setText("Horizontal"); else destroyerSens.setText("Vertical");
        if(sSens) submarinSens.setText("Horizontal"); else submarinSens.setText("Vertical");
    }

    public void setAircraftSens(boolean b) {
        this.aSens = b;
        System.out.println(aSens);
        if(aSens) {aircraftSens.setText("Horizontal");}else {aircraftSens.setText("Vertical");}
    }

    public void setBattleShipSens(boolean b) {
        this.bSens = b;
        System.out.println(bSens);
        if(bSens) {battleShipSens.setText("Horizontal");} else {battleShipSens.setText("Vertical");}
    }

    public void setCruiserSens(boolean b) {
        this.cSens = b;
        System.out.println(cSens);
        if(cSens) cruiserSens.setText("Horizontal"); else cruiserSens.setText("Vertical");
    }

    public void setDestroyerSens(boolean b) {
        this.dSens = b;
        System.out.println(dSens);
        if(dSens) destroyerSens.setText("Horizontal"); else destroyerSens.setText("Vertical");
    }

    public void setSubmarinSens(boolean b) {
        this.sSens = b;
        System.out.println(sSens);
        if(sSens) submarinSens.setText("Horizontal"); else submarinSens.setText("Vertical");
    }

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

    public void placeShip(List<ICoord> coords){
        for (JButton button : buttons) {
            for (ICoord coord : coords){
                if(button.getName().equalsIgnoreCase(coord.toString())){
                    button.setBackground(Color.BLACK);
                }
            }

        }
    }

    public boolean getAircraftSens(){return aSens;}

    public boolean getBattleShipSens(){return bSens;}

    public boolean getCruiserSens(){return cSens;}

    public boolean getDestroyerSens(){return dSens;}

    public boolean getSubmarinSens(){return sSens;}

    public void setEnableBelgium(boolean b){belgium.setEnabled(b);}

    public void setEnableFrench(boolean b){french.setEnabled(b);}

    public void setEnableAircraft(boolean b){aircraft.setEnabled(b);}

    public void setEnableBattleShip(boolean b){battleShip.setEnabled(b);}

    public void setEnableCruiser(boolean b){cruiser.setEnabled(b);}

    public void setEnableDestroyer(boolean b){destroyer.setEnabled(b);}

    public void setEnableSubmarin(boolean b){submarin.setEnabled(b);}

    public String getPlayerName(){return playerName.getText();}

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

    private void belgianFleet(){
        mainECenter.removeAll();
        mainECenter.setSize(new Dimension(mainEast.getWidth(), mainEast.getHeight()-(mainENorth.getHeight()+mainESouth.getHeight())));
        mainECenter.setPreferredSize(mainECenter.getSize());

        mainECenter.add(ship2);
        mainECenter.add(ship3);
        mainECenter.add(ship4);
        mainECenter.add(ship5);
        mainECenter.add(new JPanel());
    }

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
