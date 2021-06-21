package info1.ships;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Classe définissant une flotte de navires
 */

public class NavyFleet implements INavyFleet {

    // TODO
    private int taille;
    private List<IShip> liste_bateaux;


    /**
     * NB : LA SIGNATURE DU CONSTRUCTEUR DOIT ETRE RESPECTEE
     *
     * Construit une nouvelle flotte
     */
    public NavyFleet() {
        // TODO
        taille = 20;
        liste_bateaux = new ArrayList<>();
    }

    @Override
    public int remainingSize() {
        // TODO
        return taille;
    }

    @Override
    public boolean isComplete() {
        // TODO
        return taille==0;
    }


    @Override
    public int add(IShip IShip) {
        // TODO
        if(liste_bateaux.contains(IShip))
            return -1;
        if(taille - IShip.getCategory().getSize()< 0)
            return -2;
        liste_bateaux.add(IShip);
        taille-=IShip.getCategory().getSize();
        return 0;
    }

    @Override
    public List<IShip> getShips() {
        // TODO
        System.out.println(liste_bateaux.toString());
        liste_bateaux.sort(IShip::compareTo);
        return liste_bateaux;
    }

    @Override
    public Set<IShip> getShips(ShipCategory shipCategory) {
        // TODO
        Set<IShip> set = new HashSet<>();
        for(IShip bateau : liste_bateaux){
            if(bateau.getCategory().equals(shipCategory)){
                set.add(bateau);
            }
        }
        return set;
    }

    @Override
    public boolean isBelgianConfiguration() {
        // TODO

        return false;
    }

    @Override
    public boolean isFrenchConfiguration() {
        // TODO
        return false;
    }

    @Override
    public String toString() {
        // TODO
        StringBuilder sb = new StringBuilder();
        for(IShip bateau : liste_bateaux){
            sb.append(bateau.toString()).append("\n");
        }
        return sb.toString();
    }
}
