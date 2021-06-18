package info1.ships;

import java.util.List;

/**
 * une implémentation "abstraite" d'un bateau quelconque, de taille indéterminée
 */


public abstract class Ship implements IShip {

    // TODO

    /**
     * NB : LA SIGNATURE DU CONSTRUCTEUR DOIT ETRE RESPECTEE
     *
     * construit un bateau quelconque
     * @param name le nom du bateau
     * @param ayFront la coordonnée de la proue du bateau
     * @param ayBack la coordonnée de la poupe du bateau
     * @throws BadCoordException si l'une des coordonnées données ne définit pas une coordonnée alphanumérique correcte
     * @throws CoordsBadShipException si les coordonnées données ne permettent pas de définir un bateau correct :
     *  une ligne, une colonne, de la bonne taille, etc.
     */
    public Ship(String name, String ayFront, String ayBack)
            throws BadCoordException, CoordsBadShipException {
        // TODO
    }


    @Override
    public List<ICoord> getCoords() {
        // TODO
        return null;
    }

    @Override
    public ICoord getFront() {
        //TODO
        return null;
    }

    @Override
    public ICoord getBack() {
        // TODO
        return null;
    }

    @Override
    public String getName() {
        // TODO
        return null;
    }

    @Override
    public int getSize() {
        // TODO
        return -1;
    }

    @Override
    public String toString() {
        // TODO
        return "";
    }

    @Override
    public boolean equals(Object o) {
        // TODO
        return false;
    }

    @Override
    public int hashCode() {
        // TODO
        return -1;
    }


}
