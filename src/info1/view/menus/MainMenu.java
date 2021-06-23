package info1.view.menus;


import com.mashape.unirest.http.exceptions.UnirestException;

import info1.network.Game;
import info1.network.Network;
import info1.view.ViewManager;

import javax.swing.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainMenu {
    String url = "http://37.187.38.219/api/v0";
    List<Game> games = new ArrayList<>();


    JPanel main = new JPanel(new BorderLayout());
    //Main
    JPanel mainNorth = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel mainCenter = new JPanel(new BorderLayout());
    JPanel mainEast = new JPanel(new FlowLayout(FlowLayout.CENTER));
    JPanel mainSouth = new JPanel(new GridLayout(1,4));

    //mainNorth
    JLabel waiting = new JLabel();

    //mainCenter
    JPanel mainCNorth = new JPanel(new FlowLayout(FlowLayout.RIGHT));
    JScrollPane mainCCenter = new JScrollPane();
    JButton refresh = new JButton("refresh");

    List<String> scrollList = new ArrayList<>();
    JList<Game> scrollContent;

    //MainEast Component
    JPanel debug = new JPanel(new FlowLayout(FlowLayout.CENTER));

    JLabel rechercher = new JLabel("Rechercher");
    JTextField gameId = new JTextField();
    JButton search = new JButton("search");

    //MainSouth Component
    JButton quitter = new JButton("Quitter");
    JButton createGame = new JButton("Creer une partie");


    public MainMenu(ViewManager frame) {
        //main
        main.setSize(frame.getWidth()-15,frame.getHeight()-45);
        main.setPreferredSize(main.getSize());

        //mainNorth
        mainNorth.setSize(new Dimension(main.getWidth(), (int) (main.getHeight()*0.1)));
        mainNorth.setPreferredSize(mainNorth.getSize());

        waiting.setText("Parties en attente");
        waiting.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        waiting.setHorizontalAlignment(JTextField.CENTER);
        waiting.setBorder(BorderFactory.createEmptyBorder());

        mainNorth.add(waiting);

        //mainSouth
        mainSouth.setSize(new Dimension(main.getWidth(), (int) (main.getHeight()*0.15)));
        mainSouth.setPreferredSize(mainSouth.getSize());

        quitter.setSize(new Dimension(0, mainSouth.getHeight()));
        createGame.setSize(new Dimension(0, mainSouth.getHeight()));

        mainSouth.add(quitter);
        mainSouth.add(new JPanel());
        mainSouth.add(createGame);
        mainSouth.add(new JPanel());

        //mainCenter
        mainCenter.setSize(new Dimension((int) (main.getWidth()*0.70),main.getHeight()-mainNorth.getHeight()-mainSouth.getHeight()));
        mainCenter.setPreferredSize(mainCenter.getSize());
            //mainCNorth
        mainCNorth.setSize(new Dimension(mainCenter.getWidth(), (int) (mainCenter.getHeight()*0.1)));
        mainCNorth.setPreferredSize(mainCNorth.getSize());

        refresh.setSize(new Dimension((int) (mainCNorth.getWidth()*0.2), (int) (mainCNorth.getHeight()*0.8)));
        refresh.setPreferredSize(refresh.getSize());
        mainCNorth.add(refresh);
            //mainCCenter
        mainCCenter.setSize(new Dimension(mainCenter.getWidth(), mainCenter.getHeight()-mainCNorth.getHeight()));
        mainCCenter.setPreferredSize(mainCNorth.getSize());

        refresh();

        mainCCenter.setViewportView(scrollContent);
        scrollContent.setLayoutOrientation(JList.VERTICAL);

        mainCenter.add(mainCNorth, BorderLayout.NORTH);
        mainCenter.add(mainCCenter, BorderLayout.CENTER);

        //mainEast
        mainEast.setSize(new Dimension((main.getWidth()-mainCenter.getWidth()),main.getHeight()-mainNorth.getHeight()-mainSouth.getHeight()));
        mainEast.setPreferredSize(mainEast.getSize());

        rechercher.setSize(new Dimension(mainEast.getWidth(), (int) (mainEast.getHeight()*0.3)));
        rechercher.setPreferredSize(rechercher.getSize());

        rechercher.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        rechercher.setHorizontalAlignment(JTextField.CENTER);
        rechercher.setBorder(BorderFactory.createEmptyBorder());

        gameId.setSize(new Dimension(mainEast.getWidth(), (int) (mainEast.getHeight()*0.2)));
        gameId.setPreferredSize(gameId.getSize());
        gameId.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
        gameId.setHorizontalAlignment(SwingConstants.CENTER);

        search.setSize(new Dimension((int) (mainEast.getWidth()*0.5), (int) (mainEast.getHeight()*0.2)));
        search.setPreferredSize(search.getSize());

        mainEast.add(rechercher);
        mainEast.add(gameId);
        mainEast.add(search);

        main.add(mainNorth, BorderLayout.NORTH);
        main.add(mainSouth, BorderLayout.SOUTH);
        main.add(mainCenter, BorderLayout.CENTER);
        main.add(mainEast, BorderLayout.EAST);

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
        } catch (UnirestException e) { System.out.println(e.getMessage()); }
        Collections.reverse(games);
        scrollContent = new JList<Game>(games.toArray(new Game[games.size()]));
    }

    public void research(String recherche){
        List<Game> result = new ArrayList<>();
        try {
            games = Network.listInitializedGames(url);
        } catch (UnirestException e) { System.out.println(e.getMessage()); }
        Collections.reverse(games);

        for(Game game : games) {
            if(game.toString().contains(recherche)) result.add(game);
        }
        scrollContent = new JList<Game>(result.toArray(new Game[result.size()]));
    }

    public Game getSelectedGame(){ return scrollContent.getSelectedValue(); }

    public String getGameId(){ return gameId.getText(); }

    private void setlisteners(){

    }


}
