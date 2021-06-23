package info1.view.menus;

import com.mashape.unirest.http.exceptions.UnirestException;
import info1.Application;
import info1.network.BadIdException;
import info1.network.Game;
import info1.network.Network;
import info1.utils.GameManager;
import info1.view.Menu;
import info1.view.ViewManager;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.CompletableFuture;

public class WaitingScreen {

    private final ViewManager viewManager;
    ImageIcon waiting = new ImageIcon("src/info1/utils/img/pngFiles/waiting.png");
    JLabel waitingLabel;
    JPanel main = new JPanel(new GridBagLayout());

    public WaitingScreen(ViewManager frame) {
        this.viewManager = frame;

        main.setSize(frame.getSize());
        main.setPreferredSize(main.getSize());
        main.setBackground(new Color(0x18384f));

        waiting.setDescription("image");
        Image image = waiting.getImage();
        Image newimg = image.getScaledInstance(main.getWidth() / 2, main.getHeight() / 4, java.awt.Image.SCALE_SMOOTH);
        waiting = new ImageIcon(newimg);

        waitingLabel = new JLabel(waiting);
        waitingLabel.setVerticalAlignment(JLabel.CENTER);

        main.add(waitingLabel);

        frame.setContentPane(main);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.update();

        CompletableFuture.runAsync(() -> {
            try {
                while(!(Math.abs(Network.getInfo(GameManager.getUrl(), GameManager.getGame(), GameManager.getPlayer())) == 10)) {
                    synchronized(this) {
                        try {
                            wait(500);
                        } catch(InterruptedException ex) { ex.printStackTrace(); }
                    }
                }
                viewManager.switchTo(Menu.GAME);
            } catch(UnirestException | BadIdException e) {
                e.printStackTrace();
            }
        });

    }
}
