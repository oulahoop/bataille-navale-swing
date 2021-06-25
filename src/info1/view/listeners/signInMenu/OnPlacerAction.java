package info1.view.listeners.signInMenu;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OnPlacerAction implements ActionListener {

    private String bateau_a_placer;

    public OnPlacerAction(){
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        bateau_a_placer = ((JButton) e.getSource()).getText();
    }

    /**
     * Methode permettant de get le nom du bateau à placer
     * @return String du nom de la classe du bateau à placer
     */
    public String getBateau_a_placer() {
        return bateau_a_placer;
    }
}
