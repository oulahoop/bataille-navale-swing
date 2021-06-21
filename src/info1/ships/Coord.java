package info1.ships;


import java.util.Locale;

/**
 * une implementation de l'interface ICoord manipulant des coordonnées alphanumériques comme "A1", "B6", "J3", etc.
 */

public class Coord implements ICoord {
    String xy;

    /**
     * NB : LA SIGNATURE DU CONSTRUCTEUR DOIT ETRE RESPECTEE
     *
     * constructeur d'un objet Coord
     * @param xy la coordonnée aphanumérique sous la forme d'une chaine de caractères
     * @throws BadCoordException si la chaine de caractère ne permet pas de définir une coordonnée alphanumérique
     */
    public Coord(String xy) throws BadCoordException {

        if(xy.length() > 3 || xy.length()<2) throw new BadCoordException();

        char letter = xy.toLowerCase(Locale.ROOT).charAt(0);
        boolean valid = false;

        for (int i = 97; i < 107; i++) {
            if(letter == (char)i ) valid = true;
        }
        if (!valid) throw new BadCoordException();

        if(xy.length() ==3){
            if( Integer.parseInt(String.valueOf(xy.charAt(1)))*10 + Integer.parseInt(String.valueOf(xy.charAt(2))) != 10) throw new BadCoordException();
        }

        if(xy.length() == 2){
            valid =false;
            for (int i = 1; i < 10; i++) {
                if(Integer.parseInt(String.valueOf(xy.charAt(1))) == i) valid = true;
            }
            if (!valid) throw new BadCoordException();
        }
        this.xy = xy;


    }

    public char getAlphaX() {
        return xy.charAt(0);
    }

    public int getX() {
        int compt = 1;
        char letter = xy.toLowerCase(Locale.ROOT).charAt(0);
        for (int i = 97; i < 107; i++) {
            if(letter == (char)i ) return compt;
            compt++;
        }
        return -1;
    }

    public int getY() {
        if(xy.length() == 3){
            return Integer.parseInt(String.valueOf(xy.charAt(1)))*10 + Integer.parseInt(String.valueOf(xy.charAt(2)));
        }
        return Integer.parseInt(String.valueOf(xy.charAt(1)));
    }

    public String toString() {

        return xy.toUpperCase(Locale.ROOT);
    }

    public boolean equals(Object o) {

        return o instanceof Coord && ((Coord) o).getX() == this.getX() && ((Coord) o).getY() == this.getY();
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        if(xy.length()>2)sb.append((int)xy.charAt(0)).append(Integer.parseInt(String.valueOf(xy.charAt(1)))).append(Integer.parseInt(String.valueOf(xy.charAt(2))));
        else sb.append((int)xy.charAt(0)).append(Integer.parseInt(String.valueOf(xy.charAt(1))));
        int hashcode = Integer.parseInt(sb.toString());
        return hashcode ;
    }
}
