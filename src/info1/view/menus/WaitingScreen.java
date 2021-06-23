package info1.view.menus;

import info1.view.ViewManager;

import javax.swing.*;
import java.awt.*;

public class WaitingScreen {

    private final ViewManager viewManager;
    ImageIcon waiting = new ImageIcon("src/info1/utils/img/pngFiles/waiting.png");
    JLabel waitingLabel;
    JPanel main = new JPanel(new GridBagLayout());

    public WaitingScreen(ViewManager frame) {
        this.viewManager = frame;

        main.setSize(1920,1080);
        main.setPreferredSize(main.getSize());
        main.setBackground(new Color(0x18384f));

        waiting.setDescription("image");
        Image image = waiting.getImage();
        Image newimg = image.getScaledInstance(main.getWidth()/2, main.getHeight()/4,  java.awt.Image.SCALE_SMOOTH);
        waiting = new ImageIcon(newimg);

        waitingLabel = new JLabel(waiting);

        waitingLabel.setVerticalAlignment(JLabel.CENTER);


        main.add(waitingLabel);

        frame.setContentPane(main);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.update();

    }
}
