package info1.ships;


/**
 * une implementation de l'interface ICoord manipulant des coordonnées alphanumériques comme "A1", "B6", "J3", etc.
 */

public class Coord implements ICoord {

    // TODO

    /**
     * NB : LA SIGNATURE DU CONSTRUCTEUR DOIT ETRE RESPECTEE
     *
     * constructeur d'un objet Coord
     * @param xy la coordonnée aphanumérique sous la forme d'une chaine de caractères
     * @throws BadCoordException si la chaine de caractère ne permet pas de définir une coordonnée alphanumérique
     */
    public Coord(String xy) throws BadCoordException {
        // TODO
    }

    @Override
    public char getAlphaX() {
        // TODO
        return 'x';
    }

    @Override
    public int getX() {
        // TODO
        return -1;
    }

    @Override
    public int getY() {
        // TODO
        return -1;
    }

    @Override
    public String toString() {
        // TODO
        return "" ;
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