package info1.ships;

/**
 * une implementation de l'interface ICoord manipulant des coordonnées alphanumériques comme "A1", "B6", "J3", etc.
 */

public class Coord implements ICoord {
    String xy;

    /**
     * constructeur d'un objet Coord
     * @param xy la coordonnée aphanumérique sous la forme d'une chaine de caractères
     * @throws BadCoordException si la chaine de caractère ne permet pas de définir une coordonnée alphanumérique
     */
    public Coord(String xy) throws BadCoordException {
        if(xy.length() > 3 || xy.length()<2) throw new BadCoordException();
        xy = xy.toUpperCase();

        char letter = xy.charAt(0);
        if((int)letter<65 || (int)letter >74) throw new BadCoordException();

        boolean length = (xy.length() == 3);
        int number = length ? Integer.parseInt(xy.charAt(1) +""+ xy.charAt(2)) : Integer.parseInt(String.valueOf(xy.charAt(1)));
        if(number < 1 || number > 10) throw new BadCoordException();

        this.xy = xy;
    }

    public char getAlphaX() {
        return xy.charAt(0);
    }

    public int getX() { return (int)xy.charAt(0) - 64; }

    public int getY() {
        boolean length = (xy.length() == 3);
        return length ? Integer.parseInt(xy.charAt(1) +""+ xy.charAt(2)) : Integer.parseInt(String.valueOf(xy.charAt(1)));
    }

    public String toString() { return xy; }

    public boolean equals(Object o) { return o instanceof Coord && o.hashCode() == this.hashCode(); }

    public int hashCode() {
        boolean length = (xy.length() == 3);
        return (int)xy.charAt(0) + (length ? Integer.parseInt(xy.charAt(1) +""+ xy.charAt(2)) : Integer.parseInt(String.valueOf(xy.charAt(1))));
    }
}
