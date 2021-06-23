package info1.view.menus;

import info1.view.ViewManager;

import javax.swing.*;
import java.awt.*;

public class WaitingScreen {

    private final ViewManager viewManager;
    ImageIcon waiting = new ImageIcon("src/info1/utils/img/pngFiles/waiting.png");
    JLabel waitingLabel;
    JPanel main = new JPanel();

    public WaitingScreen(ViewManager frame) {
        this.viewManager = frame;

        main.setSize(1920,1080);
        main.setPreferredSize(main.getSize());

        waiting.setDescription("image");

        waitingLabel = new JLabel(waiting);
        waitingLabel.setSize(new Dimension(main.getWidth(), main.getHeight()));
        waitingLabel.setPreferredSize(waitingLabel.getSize());


        main.add(waitingLabel);


        frame.setContentPane(main);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.update();

    }
}
