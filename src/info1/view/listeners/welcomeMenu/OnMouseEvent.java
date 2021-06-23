package info1.view.listeners.welcomeMenu;

import info1.view.Menu;
import info1.view.ViewManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class OnMouseEvent implements MouseListener {

    private final ViewManager viewManager;

    public OnMouseEvent(ViewManager viewManager){
        this.viewManager = viewManager;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getComponent().getName().equals("WelcomeMenu")) {
            viewManager.switchTo(Menu.SIGN_IN);
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
