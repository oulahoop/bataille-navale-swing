package info1.view.menus;

import info1.utils.GameManager;

import info1.utils.ImagePanel;
import info1.view.Menu;
import info1.view.ViewManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;

/**
 * Class qui permet de crée la vue "en attente d'un adversaire" et l'ajoute au frame "ViewManager"
 */
public class WaitingScreen {

    private final ViewManager viewManager;

    ImagePanel main;
    Image background;

    ImageIcon waitingIcon = new ImageIcon("src/info1/utils/img/waiting.png");
    JLabel waitingLabel;

    /**
     * Constructeur de la classe WaitingScreen dans le quel est créé la vue
     * @param frame Le viewManager servant de frame d'affichage
     */
    public WaitingScreen(ViewManager frame) {
        //definition de l'attribut viewManager
        this.viewManager = frame;

        //image DEFINITION
        try {
            background = ImageIO.read(new File("src/info1/utils/img/background.png"));
            background = background.getScaledInstance(1280, 720, Image.SCALE_SMOOTH);
        } catch(IOException e) {
            e.printStackTrace();
        }
        //Gestion du Jpanel "main"
        main = new ImagePanel(background);
        main.setLayout(new GridBagLayout());
        main.setSize(frame.getSize());
        main.setPreferredSize(main.getSize());
        main.setBackground(new Color(0x18384f));

        //Gestion de l'imageIcon (redimentionement de l'image)
        waitingIcon.setDescription("image");
        Image image = waitingIcon.getImage();
        Image newimg = image.getScaledInstance(main.getWidth() / 2, main.getHeight() / 4, java.awt.Image.SCALE_SMOOTH);
        waitingIcon = new ImageIcon(newimg);

        //Definition et gestion du Jlabel affichant l'image
        waitingLabel = new JLabel(waitingIcon);
        waitingLabel.setVerticalAlignment(JLabel.CENTER);

        //Ajout du Jlabel dans le Jpanel
        main.add(waitingLabel);

        //Gestion du frame
        frame.setContentPane(main);
        frame.update();

        //Uttilisation de la methode privée Waiting()
        waiting();
    }

    /**
     * Méthode qui permet de géré le passage au prochain affichage
     * Déclenche le changement d'affichage des qu'un joueur rejoind la partie courante
     */
    private void waiting(){
        CompletableFuture.runAsync(() -> {
            while(GameManager.hasGuest()) {
                synchronized(this) {
                    try {
                        wait(500);
                    } catch(InterruptedException ex) { ex.printStackTrace(); }
                }
            }
            viewManager.switchTo(Menu.GAME);
        });
    }
}
