package info1.view.menus;


import com.mashape.unirest.http.exceptions.UnirestException;
import info1.Application;
import info1.network.Game;
import info1.network.Network;
import info1.view.ViewManager;

import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainMenu {
    String url = "http://37.187.38.219/api/v0";
    List<Game> games = new ArrayList<>();

    //JComponents
    JPanel main = new JPanel(new BorderLayout());

    //Main components
    JLabel mainNorth = new JLabel("Parties en attente");
    JPanel mainCenter = new JPanel(new BorderLayout());
    JPanel mainEast = new JPanel(new BorderLayout());
    JPanel mainSouth = new JPanel(new BorderLayout());

    //mainCenter Components
    JPanel selectNorth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    ScrollPane scrollPane = new ScrollPane();

        //selectNorth Component
        JButton refresh = new JButton("refresh");
        //ScrollPane Component
        JPanel scrollContent = new JPanel(new FlowLayout(FlowLayout.CENTER));

    //MainEast Component
    JPanel debug = new JPanel(new FlowLayout(FlowLayout.CENTER));

    JTextField rechercher = new JTextField("RECHERCHER");
    JTextField gameId = new JTextField("GAMEID");
    JButton search = new JButton("search");

    //MainSouth Component
    JButton quitter = new JButton("Quitter");
    JPanel mainSCenter = new JPanel(new FlowLayout(FlowLayout.CENTER));
        //MainSCenter Component
        JButton createGame = new JButton("Creer une partie");


    public MainMenu(ViewManager frame) {
        //main
        main.setPreferredSize(new Dimension(1000,800));

        //mainNorth
        mainNorth.setPreferredSize(new Dimension(150, 50));

        //Main ADD NORTH
        main.add(mainNorth, BorderLayout.NORTH);

        //mainCenter Components
        //North
        refresh.setPreferredSize(new Dimension(150,50));
        selectNorth.add(refresh);
        //Center
        scrollPane.add(scrollContent);

        //mainCenter
        mainCenter.setPreferredSize(new Dimension(500, 0));
        mainCenter.add(selectNorth, BorderLayout.NORTH);
        mainCenter.add(scrollPane, BorderLayout.CENTER);

        //Main ADD CENTER
        main.add(mainCenter, BorderLayout.CENTER);

        //Main EAST Components
        mainEast.setPreferredSize(new Dimension(500, 0));

        rechercher.setPreferredSize(new Dimension(500,200));
        rechercher.setEditable(false);
        rechercher.setText("RECHERCHER");
        rechercher.setHorizontalAlignment(JTextField.CENTER);
        rechercher.setBorder(BorderFactory.createEmptyBorder());

        gameId.setPreferredSize(new Dimension(400,75));
        gameId.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        search.setPreferredSize(new Dimension(400,50));

        debug.add(rechercher);
        debug.add(gameId);
        debug.add(search);

        mainEast.add(debug, BorderLayout.CENTER);

        //main ADD EAST
        main.add(mainEast, BorderLayout.EAST);

        //Main SouthComponent
        quitter.setPreferredSize(new Dimension(300,100));
        createGame.setPreferredSize(new Dimension(300,100));

        mainSCenter.add(createGame);
        //Main South Add componenet
        mainSouth.add(quitter, BorderLayout.WEST);
        mainSouth.add(mainSCenter, BorderLayout.CENTER);

        //main ADD SOUTH
        main.add(mainSouth, BorderLayout.SOUTH);

        frame.add(main);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
        frame.repaint();



    }

    public void refresh(){
        try {
            games = Network.listInitializedGames(url);
        } catch (UnirestException e) { e.printStackTrace(); }

        //TODO affichage des games
    }
}
