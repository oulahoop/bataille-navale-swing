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
        if(!isComplete()){return false;}
        try {
            AircraftCarrier monPorteAvion = new AircraftCarrier("un porteavion", "E5", "E9");
            Battleship monCuirasse = new Battleship("un cuirassé", "B2", "E2");
            Submarine monSousMarin = new Submarine("un sous-marin", "G10");
            Cruiser monCroiseur = new Cruiser("un croiseur", "B8", "B6");
            Destroyer monTorpilleur = new Destroyer("un torpilleur", "H3", "H4");
            Destroyer autreTorpilleur = new Destroyer("un autre torpilleur", "D9", "C9");
            Cruiser autreCroiseur = new Cruiser("un autre croiseur", "J8", "H8");
            List<IShip> belgiumConfig = new ArrayList<>();
            belgiumConfig.add(monCuirasse);
            belgiumConfig.add(monCroiseur);
            belgiumConfig.add(autreCroiseur);
            belgiumConfig.add(monTorpilleur);
            if(liste_bateaux.containsAll(belgiumConfig));
                return true;
        } catch (BadCoordException | CoordsBadShipException e) {
            e.printStackTrace();
        }
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
