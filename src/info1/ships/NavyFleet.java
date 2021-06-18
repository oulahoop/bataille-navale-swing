package info1.ships;

import java.util.List;
import java.util.Set;

/**
 * Classe définissant une flotte de navires
 */

public class NavyFleet implements INavyFleet {

    // TODO


    /**
     * NB : LA SIGNATURE DU CONSTRUCTEUR DOIT ETRE RESPECTEE
     *
     * Construit une nouvelle flotte
     */
    public NavyFleet() {
        // TODO
    }

    @Override
    public int remainingSize() {
        // TODO
        return -1;
    }

    @Override
    public boolean isComplete() {
        // TODO
        return false;
    }


    @Override
    public int add(IShip IShip) {
        // TODO
        return -1;
    }

    @Override
    public List<IShip> getShips() {
        // TODO
        return null;
    }

    @Override
    public Set<IShip> getShips(ShipCategory shipCategory) {
        // TODO
        return null;
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
        return null;
    }
}
