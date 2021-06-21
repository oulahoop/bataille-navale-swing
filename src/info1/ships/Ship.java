package info1.ships;

import java.util.ArrayList;
import java.util.List;

/**
 * une implémentation "abstraite" d'un bateau quelconque, de taille indéterminée
 */


public abstract class Ship implements IShip {

    private String name;
    private Coord front;
    private Coord back;
    private int identifiant;
    private static int ID = 0;
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
        this.name = name;
        this.front= new Coord(ayFront);
        this.back = new Coord(ayBack);
        double distance = Math.sqrt(Math.pow((front.getX()-back.getX()),2) + Math.pow((front.getY()- back.getY()),2));
        if(this.getCategory().getSize() != distance)
            throw new CoordsBadShipException();

    }


    @Override
    public List<ICoord> getCoords() {
        List<ICoord> coordonees = new ArrayList<>();
        coordonees.add(front);
        Coord intermediaire = front;
        //vertical
        if (back.getX() == front.getX()) {
            if (front.getY() < back.getY()) {
                for(int i = front.getY()+1; i < back.getY(); i++){
                    try {
                        StringBuilder sb =new StringBuilder();
                        sb .append(front.getAlphaX()).append(i);
                        System.out.println(sb);
                        coordonees.add(new Coord(sb.toString()));
                    } catch (BadCoordException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (front.getY() > back.getY()) {
                for(int i = back.getY(); i > front.getY(); i--){
                    try {
                        StringBuilder sb =new StringBuilder();
                        sb .append(front.getAlphaX()).append(i);
                        System.out.println(sb);
                        coordonees.add(new Coord(sb.toString()));
                    } catch (BadCoordException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        //Horizontal
        if(front.getY() == back.getY()){
            if (front.getAlphaX() < back.getAlphaX()) {
                for(int i = front.getAlphaX()+1; i < back.getAlphaX(); i++){
                    try {
                        StringBuilder sb =new StringBuilder();
                        sb .append((char) ((int)front.getAlphaX()-1)).append(front.getY());
                        System.out.println(sb);
                        coordonees.add(new Coord(sb.toString()));
                    } catch (BadCoordException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (front.getAlphaX() > back.getAlphaX()) {
                for(int i = back.getAlphaX(); i > front.getAlphaX(); i--){
                    try {
                        StringBuilder sb =new StringBuilder();
                        sb .append((char)((int)front.getAlphaX()+1)).append(front.getY());
                        System.out.println(sb);
                        coordonees.add(new Coord(sb.toString()));
                    } catch (BadCoordException e) {
                        e.printStackTrace();
                    }
                }
            }
        }


        coordonees.add(back);
        return coordonees;
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
        return (int) Math.sqrt(Math.pow((getBack().getX()-getFront().getX()),2)+Math.pow((getBack().getY()-getFront().getY()),2));
    }

    @Override
    public String toString() {
        return "nom : "+ this.getName() + " taille : " + this.getSize();
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Ship)) return false;
        if(this.getName()==((Ship) o).getName() || this.getSize() == ((Ship) o).getSize()){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {

        return 0;
    }


}