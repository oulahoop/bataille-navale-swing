package info1.view;

import info1.Application;
import info1.view.menus.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.time.Instant;
import java.util.concurrent.CompletableFuture;

public class ViewManager extends JFrame {
    private Object menu;

    public ViewManager() {
        this.setSize(new Dimension(1000, 800));
        this.setPreferredSize(this.getSize());
        this.pack();
        this.setVisible(true);
        new WelcomeMenu(this);
    }

    public void switchTo(Menu menu) {
        this.menu = switch(menu) {
            case WELCOME -> new WelcomeMenu(this);
            case SIGN_IN -> new SignInMenu(this);
            case MAIN -> new MainMenu(this);
            case GAME -> new GameMenu(this);
            case WAITING -> new WaitingScreen(this);
        };
    }
    public WelcomeMenu getWelcome() { return menu instanceof WelcomeMenu ? (WelcomeMenu) menu : null; }


    public void update() {
        this.setPreferredSize(this.getSize());
        this.pack();
        this.setVisible(true);
        repaint();
    }

    public void enableShoot(boolean enable) {
        Application app = Application.getApp();
        if(app.getGameManager().getGame() == null) return;
    }

    public void alert(String message, boolean error) {
        CompletableFuture.runAsync(() -> {
            synchronized(this) {
                try {
                    JTextField text = new JTextField();
                    text.setPreferredSize(new Dimension(this.getWidth(), 50));
                    text.setText(message);
                    text.setEditable(false);
                    text.setBackground(new Color(0xFF7869));
                    text.setHorizontalAlignment(JTextField.CENTER);
                    Border border = BorderFactory.createEmptyBorder();
                    text.setBorder(border);

                    this.getContentPane().add(text);
                    update();

                    for(int i=-50; i<0; i++) {
                        text.setLocation(0, i);
                        wait(3);
                        update();
                    }
                    wait(1500);
                    for(int i=0; i>-50; i--) {
                        text.setLocation(0, i);
                        wait(3);
                        update();
                    }
                    this.remove(text);
                    update();
                } catch(InterruptedException e) { e.printStackTrace(); }
            }
        });
    }
}
