package info1.ships;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Classe définissant une flotte de navires
 */

public class NavyFleet implements INavyFleet {

    private int taille;
    private List<IShip> liste_bateaux;


    /**
     * NB : LA SIGNATURE DU CONSTRUCTEUR DOIT ETRE RESPECTEE
     *
     * Construit une nouvelle flotte
     */
    public NavyFleet() {
        taille = 20;
        liste_bateaux = new ArrayList<>();
    }

    @Override
    public int remainingSize() {
        return taille;
    }

    @Override
    public boolean isComplete() {
        return taille==0;
    }


    @Override
    public int add(IShip IShip) {
        if(liste_bateaux.contains(IShip)) return -1;
        if(taille - IShip.getCategory().getSize()< 0) return -2;

        if(!liste_bateaux.isEmpty()) {
            for (IShip bateau : liste_bateaux) {
                for (ICoord coordBateau : bateau.getCoords()) {
                    for (ICoord coordNewBateau : IShip.getCoords()) {
                        if (coordBateau.equals(coordNewBateau)) {
                            return -3;
                        }
                    }
                }
            }
        }
        liste_bateaux.add(IShip);
        taille-=IShip.getCategory().getSize();
        return 0;
    }

    @Override
    public List<IShip> getShips() {
        liste_bateaux.sort(IShip::compareTo);
        return liste_bateaux;
    }

    @Override
    public Set<IShip> getShips(ShipCategory shipCategory) {
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
        List<ShipCategory> config = new ArrayList<>();
        for(IShip s : liste_bateaux) { config.add(s.getCategory()); }
        return  config.remove(ShipCategory.BATTLESHIP)
                && config.remove(ShipCategory.CRUISER)
                && config.remove(ShipCategory.CRUISER)
                && config.remove(ShipCategory.DESTROYER)
                && config.remove(ShipCategory.DESTROYER)
                && config.remove(ShipCategory.DESTROYER)
                && config.remove(ShipCategory.SUBMARINE)
                && config.remove(ShipCategory.SUBMARINE)
                && config.remove(ShipCategory.SUBMARINE)
                && config.remove(ShipCategory.SUBMARINE);
    }

    @Override
    public boolean isFrenchConfiguration() {
        List<ShipCategory> config = new ArrayList<>();
        for(IShip s : liste_bateaux) { config.add(s.getCategory()); }
        return  config.remove(ShipCategory.AIRCRAFT_CARRIER)
                && config.remove(ShipCategory.BATTLESHIP)
                && config.remove(ShipCategory.CRUISER)
                && config.remove(ShipCategory.CRUISER)
                && config.remove(ShipCategory.DESTROYER)
                && config.remove(ShipCategory.DESTROYER)
                && config.remove(ShipCategory.SUBMARINE);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(IShip bateau : liste_bateaux){
            sb.append(bateau.toString()).append("\n");
        }
        return sb.toString();
    }
}