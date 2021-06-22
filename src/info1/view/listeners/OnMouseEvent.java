package info1.view.listeners;

import info1.Application;
import info1.view.Menu;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class OnMouseEvent implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        Application app = Application.getApp();
        if(e.getComponent().getName().equals("WelcomeMenu")) {
            System.out.println("alerte !");
            app.getViewManager().alert("Ceci est une alerte !", true);
            //app.getViewManager().switchTo(Menu.SIGN_IN);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
