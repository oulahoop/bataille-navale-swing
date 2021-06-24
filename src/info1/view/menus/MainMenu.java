package info1.view.menus;

import info1.network.Game;

import info1.utils.GameManager;

import info1.view.ConstantColor;
import info1.view.ViewManager;
import info1.view.listeners.mainMenu.GameIdListener;
import info1.view.listeners.mainMenu.MenuActionListener;
import info1.view.listeners.mainMenu.ValueChanged;

import javax.swing.*;

import java.awt.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Class qui permet de crée la vue "Selection d'une partie / Creation d'une partie" et l'ajoute au frame "ViewManager"
 */
public class MainMenu {

     private final ViewManager viewManager;

    JPanel main = new JPanel(new BorderLayout());
    //Main components
    JPanel mainNorth = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel mainCenter = new JPanel(new BorderLayout());
    JPanel mainEast = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel mainSouth = new JPanel(new GridLayout(1,4));

    //mainNorth
    JLabel waiting = new JLabel();

    //mainCenter components
    JPanel mainCNorth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JScrollPane mainCCenter = new JScrollPane();
    JButton refresh = new JButton("refresh");

    JList<Game> scrollList;

    //MainEast Components
    JLabel rechercher = new JLabel("Rechercher");
    JTextField gameSearch = new JTextField();
    JButton search = new JButton("search");

    //MainSouth Components
    JButton quitter = new JButton("retour");
    JButton createGame = new JButton("Creer une partie");

    /**
     * Constructeur de la classe MainMenu dans le quel est créé la vue
     * @param frame Le viewManager servant de frame d'affichage
     */
    public MainMenu(ViewManager frame) {

        //Définition de l'attribut ViewManager
        this.viewManager = frame;

        //main DEFINTION
        main.setSize(frame.getSize());
        main.setPreferredSize(main.getSize());
        main.setBackground(new Color(ConstantColor.BACKGROUND.getColor()));

        //mainNorth
        mainNorth.setSize(new Dimension(main.getWidth(), (int) (main.getHeight()*0.1)));
        mainNorth.setPreferredSize(mainNorth.getSize());
        mainNorth.setBackground(new Color(ConstantColor.BACKGROUND.getColor()));

        waiting.setText("Parties en attente");
        waiting.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        waiting.setHorizontalAlignment(JTextField.CENTER);
        waiting.setBorder(BorderFactory.createEmptyBorder());

        //mainNorth ADD
        mainNorth.add(waiting);

        //mainSouth DEFINITION
        mainSouth.setSize(new Dimension(main.getWidth(), (int) (main.getHeight()*0.15)));
        mainSouth.setPreferredSize(mainSouth.getSize());
        mainSouth.setBackground(new Color(ConstantColor.BACKGROUND.getColor()));

        quitter.setSize(new Dimension(0, mainSouth.getHeight()));
        quitter.setName("quitter");
        createGame.setSize(new Dimension(0, mainSouth.getHeight()));
        createGame.setName("createGame");

        //mainSouth ADD
        mainSouth.add(quitter);
        JPanel empty1 = new JPanel();
        empty1.setBackground(new Color(ConstantColor.BACKGROUND.getColor()));
        mainSouth.add(empty1);

        JPanel empty2 = new JPanel();
        empty2.setBackground(new Color(ConstantColor.BACKGROUND.getColor()));
        mainSouth.add(createGame);
        mainSouth.add(empty2);

        //mainCenter DEFINTION
        mainCenter.setSize(new Dimension((int) (main.getWidth()*0.70),main.getHeight()-mainNorth.getHeight()-mainSouth.getHeight()));
        mainCenter.setPreferredSize(mainCenter.getSize());
        mainCCenter.setBackground(new Color(ConstantColor.BACKGROUND.getColor()));

        //mainCNorth DEFINITION
        mainCNorth.setSize(new Dimension(mainCenter.getWidth(), (int) (mainCenter.getHeight()*0.1)));
        mainCNorth.setPreferredSize(mainCNorth.getSize());
        mainCNorth.setBackground(new Color(ConstantColor.BACKGROUND.getColor()));

        refresh.setName("refresh");
        refresh.setSize(new Dimension((int) (mainCNorth.getWidth()*0.2), (int) (mainCNorth.getHeight()*0.8)));
        refresh.setPreferredSize(refresh.getSize());

        //mainCNorth ADD
        mainCNorth.add(refresh);

        //mainCCenter DEFINITION
        mainCCenter.setSize(new Dimension(mainCenter.getWidth(), mainCenter.getHeight()-mainCNorth.getHeight()));
        mainCCenter.setPreferredSize(mainCNorth.getSize());
        mainCCenter.setBackground(new Color(ConstantColor.BACKGROUND.getColor()));

        //mise a jours de la Jlist
        refresh();

        //mainCenter ADD
        mainCenter.add(mainCNorth, BorderLayout.NORTH);
        mainCenter.add(mainCCenter, BorderLayout.CENTER);

        //mainEast DEFINITION
        mainEast.setSize(new Dimension((main.getWidth()-mainCenter.getWidth()),main.getHeight()-mainNorth.getHeight()-mainSouth.getHeight()));
        mainEast.setPreferredSize(mainEast.getSize());
        mainEast.setBackground(new Color(ConstantColor.BACKGROUND.getColor()));

        rechercher.setSize(new Dimension(mainEast.getWidth(), (int) (mainEast.getHeight()*0.3)));
        rechercher.setPreferredSize(rechercher.getSize());

        rechercher.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        rechercher.setHorizontalAlignment(JTextField.CENTER);
        rechercher.setBorder(BorderFactory.createEmptyBorder());

        gameSearch.setSize(new Dimension(mainEast.getWidth(), (int) (mainEast.getHeight()*0.2)));
        gameSearch.setName("gameId");
        gameSearch.setPreferredSize(gameSearch.getSize());
        gameSearch.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        gameSearch.setHorizontalAlignment(SwingConstants.CENTER);

        search.setName("search");
        search.setSize(new Dimension((int) (mainEast.getWidth()*0.5), (int) (mainEast.getHeight()*0.2)));
        search.setPreferredSize(search.getSize());

        //mainEast ADD
        mainEast.add(rechercher);
        mainEast.add(gameSearch);
        mainEast.add(search);

        //Main ADD
        main.add(mainNorth, BorderLayout.NORTH);
        main.add(mainSouth, BorderLayout.SOUTH);
        main.add(mainCenter, BorderLayout.CENTER);
        main.add(mainEast, BorderLayout.EAST);

        frame.setContentPane(main);
        frame.update();

        //Setting des differents listeners
        setListeners();
    }

    /**
     * Méthode qui permet de mettre a jour la Jlist Affichant les parties disponible sur le serveur
     */
    public void refresh(){
        List<Game> games = GameManager.GetGames();
        if(games == null) return;

        //reverse afin d'afficher les dernieres parties créé en haut de liste
        Collections.reverse(games);

        //Ajout des elements dans la Jlist et redefinition (attribut + Listener)
        scrollList = new JList<>(games.toArray(new Game[0]));
        mainCCenter.setViewportView(scrollList);
        scrollList.setLayoutOrientation(JList.VERTICAL);
        scrollList.addListSelectionListener(new ValueChanged(this, viewManager));

    }

    /**
     * Méthode qui permet de mettre a jour la Jlist Affichant les parties disponible sur le serveur
     * Prend en compte la recherche pour selectionné les elements à afficher
     * @param recherche String Argument de trie
     */
    public void research(String recherche){
        List<Game> result = new ArrayList<>();

        List<Game> games = GameManager.GetGames();
        if(games == null) return;

        //reverse afin d'afficher les dernieres parties créé en haut de liste
        Collections.reverse(games);

        //recuperation des elements de la list correspondant a la recherche
        for(Game game : games) {
            if(game.toString().toLowerCase().contains(recherche.toLowerCase())) result.add(game);
        }
        //Ajout des elements dans la Jlist et redefinition (attribut + Listener)
        scrollList = new JList<>(result.toArray(new Game[0]));
        mainCCenter.setViewportView(scrollList);
        scrollList.setLayoutOrientation(JList.VERTICAL);
        scrollList.addListSelectionListener(new ValueChanged(this, viewManager));
    }

    /**
     * Méthode qui permet de récupérer la valeur de l'element selectionné dans l'objet Jlist "scrollList"
     * @return Game, la partie selectionnée
     */
    public Game getSelectedGame(){ return scrollList.getSelectedValue(); }

    /**
     * Méthode qui permet de récupérer le contenue du JTextField "gameSearch"
     * @return String, le text de "gameSearch"
     */
    public String getGameSearch(){ return gameSearch.getText(); }

    /**
     * Méthode privée qui permet d'associer un listener au element en ayant besoin
     */
    private void setListeners(){
        gameSearch.addKeyListener(new GameIdListener(this));

        refresh.addActionListener(new MenuActionListener(this, viewManager));
        search.addActionListener(new MenuActionListener(this, viewManager));
        quitter.addActionListener(new MenuActionListener(this, viewManager));
        createGame.addActionListener(new MenuActionListener(this, viewManager));
    }


}
