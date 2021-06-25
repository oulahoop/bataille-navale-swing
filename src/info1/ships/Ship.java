package info1.ships;

import java.util.ArrayList;
import java.util.List;

/**
 * une implémentation "abstraite" d'un bateau quelconque, de taille indéterminée
 */


public abstract class Ship implements IShip {

    private final String name;
    private final Coord front;
    private final Coord back;
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
    public Ship(String name, String ayFront, String ayBack) throws BadCoordException, CoordsBadShipException {
        this.name = name;
        this.front = new Coord(ayFront);
        this.back = new Coord(ayBack);

        if(back.getX() != front.getX() && back.getY() != front.getY()) throw new CoordsBadShipException();
    }


    @Override
    public List<ICoord> getCoords() {
        List<ICoord> coords = new ArrayList<>();
        if(getSize() == 1) { coords.add(front); return coords; }
        boolean reverse = false, vertical = front.getX()%back.getX()==0;
        int start = vertical ? front.getY() : front.getX(), end = vertical ? back.getY() : back.getX();
        if(start < end) { reverse = true; start+=end; end=start-end; start-=end; }
        for(int i=start; i>=end; i--) {
            try { coords.add(reverse ? 0 : coords.size(), vertical ? new Coord(String.valueOf(front.getAlphaX()) + i) : new Coord(String.valueOf((char) (i + 96)) + front.getY()));
            } catch (BadCoordException e) { e.printStackTrace(); }
        } return coords;
    }

    @Override
    public ICoord getFront() {
        return this.front;
    }

    @Override
    public ICoord getBack() {
        return this.back;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getSize() {
        return front.getX()!=back.getX() ? Math.abs(front.getX()-back.getX())+1 : Math.abs(front.getY()-back.getY())+1;
    }

    @Override
    public String toString() {
        return "nom : "+ this.getName() + " taille : " + this.getSize();
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Ship && o.hashCode() == this.hashCode() && this.getName().equals(((Ship) o).getName());
    }

    @Override
    public int hashCode() {
        boolean vertical = front.getX()%back.getX()==0;
        StringBuilder sb = new StringBuilder();
        sb.append(vertical ? 0 : 1).append(vertical ? front.getX() : front.getY());
        for(ICoord co : getCoords()) {sb.append(vertical ? co.getY() : co.getX());}
        return Integer.parseInt(sb.toString());
    }


}
