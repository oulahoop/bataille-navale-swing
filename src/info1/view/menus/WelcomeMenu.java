package info1.view.menus;

import info1.utils.ImagePanel;
import info1.view.ViewManager;
import info1.view.listeners.welcomeMenu.OnMouseEvent;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

import java.io.File;
import java.io.IOException;

/**
 * Classe qui permet la creation de la vue "Welcome", et l'affiche dans le frame "ViewManager"
 */
public class WelcomeMenu {

    Image image;
    ImagePanel principal ;

    ImageIcon welcomeIcon = new ImageIcon("src/info1/utils/img/welcome.png");
    JLabel welcomeLabel;

    /**
     * Constructeur de la classe, dans le quel est créé la vue
     * @param frame le frame dans le quel s'afficher la vue
     */
    public WelcomeMenu(ViewManager frame) {
        //image DEFINITION
        try {
            image = ImageIO.read(new File("src/info1/utils/img/background.png"));
            image = image.getScaledInstance(1280, 720, Image.SCALE_SMOOTH);
        } catch(IOException e) {
            e.printStackTrace();
        }
        //principal DEFINITION
        principal = new ImagePanel(image);
        principal.setSize(frame.getSize());
        principal.setPreferredSize(principal.getSize());
        principal.setVisible(true);
        principal.setName("WelcomeMenu");
        principal.setLayout(new GridBagLayout());

        //welcomeIcon DEFINITION
        welcomeIcon.setDescription("image");
        Image image = welcomeIcon.getImage();
        Image newimg = image.getScaledInstance(principal.getWidth() / 2, principal.getHeight() / 4, java.awt.Image.SCALE_SMOOTH);
        welcomeIcon = new ImageIcon(newimg);

        //welcomeLabel DEFINTION
        welcomeLabel = new JLabel(welcomeIcon);
        welcomeLabel.setVerticalAlignment(JLabel.CENTER);

        //principal ADD
        principal.add(welcomeLabel);
        principal.addMouseListener(new OnMouseEvent(frame));

        //frame REDEFINTIION
        frame.setContentPane(principal);
        frame.update();
    }
}
