package info1.view.menus;

import info1.utils.GameManager;

import info1.view.Menu;
import info1.view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CompletableFuture;

/**
 * Class qui permet de crée la vue "en attente d'un adversaire" et l'ajoute au frame "ViewManager"
 */
public class WaitingScreen {

    private final ViewManager viewManager;

    JPanel main = new JPanel(new GridBagLayout());

    ImageIcon waitingIcon = new ImageIcon("src/info1/utils/img/waiting.png");
    JLabel waitingLabel;

    /**
     * Constructeur de la classe WaitingScreen dans le quel est créé la vue
     * @param frame Le viewManager servant de frame d'affichage
     */
    public WaitingScreen(ViewManager frame) {
        //definition de l'attribut viewManager
        this.viewManager = frame;

        //Gestion du Jpanel "main"
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
